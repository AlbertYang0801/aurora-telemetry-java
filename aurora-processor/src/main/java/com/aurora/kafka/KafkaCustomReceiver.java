package com.aurora.kafka;

import cn.hutool.json.JSONUtil;
import com.aurora.config.KafkaCustomProperties;
import com.aurora.processor.DataProcessor;
import com.aurora.processor.KafkaProcessorFactory;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author AlbertYang
 * @date 2025/7/7 18:11
 */
@Component
public class KafkaCustomReceiver {

    private static final Logger logger = LogManager.getLogger(KafkaCustomReceiver.class.getName());

    public static volatile boolean working = true;

    private static MonitoredKafkaConsumer monitoredKafkaConsumer;

    private static long count = 0;

    @Resource
    KafkaCustomProperties kafkaCustomProperties;
    @Resource
    KafkaProperties kafkaProperties;
    @Resource
    KafkaProcessorFactory kafkaProcessorFactory;

    public void init() {
        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaProperties.getBootstrapServers());
        props.put("group.id", kafkaProperties.getConsumer().getGroupId());
        props.put("enable.auto.commit", kafkaProperties.getConsumer().getEnableAutoCommit());
        props.put("auto.commit.interval.ms", (int) kafkaProperties.getConsumer().getAutoCommitInterval().getSeconds() * 1000);
        props.put("max.poll.records", kafkaProperties.getConsumer().getMaxPollRecords());
        props.put("max.poll.interval.ms", Integer.MAX_VALUE);
        props.put("key.deserializer", kafkaProperties.getConsumer().getKeyDeserializer());
        props.put("value.deserializer", kafkaProperties.getConsumer().getValueDeserializer());
        if (kafkaCustomProperties.isAuthSwitch()) {
            //安全协议
            props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
            props.put(SaslConfigs.SASL_MECHANISM, kafkaCustomProperties.getSaslMechanism());
            props.put(SaslConfigs.SASL_JAAS_CONFIG, kafkaCustomProperties.getSaslJaasConfig());
        }
        KafkaConsumer<String, byte[]> consumer = new KafkaConsumer<>(props);
        //metric-topic
        List<String> topics = new ArrayList<>();
        topics.add(kafkaCustomProperties.getDeviceMetricTopic());
        topics.add(kafkaCustomProperties.getProcessMetricTopic());
        //支持更多topic订阅

        consumer.subscribe(topics);
        logger.info("Subscribe kafka topics: {}", JSONUtil.toJsonStr(topics));
        monitoredKafkaConsumer = new MonitoredKafkaConsumer(consumer, new LoggingKafkaPollMonitor());
    }

    public final void receive() {
        try {
            logger.info("start to receive kafka message");
            while (working) {
                try {
                    if (Thread.currentThread().isInterrupted()) {
                        logger.error("kafka receiver interruption");
                        return;
                    }
                    //控制kafka消费速度
                    //processor线程池剩余容量 < 单次拉取消息的一半
                    if (processorRemainingCapacity() < kafkaProperties.getConsumer().getMaxPollRecords() / 2) {
                        continue;
                    }
                    ConsumerRecords<String, byte[]> records = monitoredKafkaConsumer.poll();
                    for (ConsumerRecord<String, byte[]> record : records) {
                        //根据topic选择processor
                        DataProcessor processor = kafkaProcessorFactory.getProcessor(record.topic());
                        processor.process(record.value());
                        count++;
                    }
                } catch (Throwable exception) {
                    logger.error("kafka receiver error !", exception);
                }
            }
        } finally {
            monitoredKafkaConsumer.close();
        }
    }

    private int processorRemainingCapacity() {
        //线程池剩余容量
        return DataProcessor.remainingCapacity();
    }

    private void countMessage() {
        logger.info("start to count message");
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Counter(), 1, 1, TimeUnit.MINUTES);
    }


    /**
     * 统计每分钟消费消息数量
     */
    static class Counter implements Runnable {
        @Override
        public void run() {
            try {
                logger.info("totally processed " + count);
                count = 0;
            } catch (Throwable ex) {
                logger.error("counter error:", ex);
            }
        }
    }

    public void start() {
        //初始化kafka消费者
        init();
        //异步统计消息数量
        countMessage();
        //接收kafka消息
        receive();
    }

    @PreDestroy
    public void shutdown() {
        working = false;
        monitoredKafkaConsumer.close();
        logger.info("kafka receiver shutdown");
    }


}
