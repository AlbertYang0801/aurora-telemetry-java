package com.aurora.kafka;

import cn.hutool.json.JSONUtil;
import com.aurora.config.KafkaCustomConfig;
import com.aurora.processor.KafkaDataProcessor;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
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

    private KafkaConsumer<String, byte[]> consumer;
    private static long count = 0;
    public static volatile boolean working = true;

    @Autowired
    KafkaCustomConfig kafkaCustomConfig;
    @Autowired
    KafkaProperties kafkaProperties;


    public KafkaCustomReceiver() {
        List<String> topics = new ArrayList<>();
        topics.add(kafkaCustomConfig.getMetricTopic());
        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaProperties.getBootstrapServers());
        props.put("group.id", kafkaProperties.getConsumer().getGroupId());
        props.put("enable.auto.commit", kafkaProperties.getConsumer().getEnableAutoCommit());
        props.put("auto.commit.interval.ms", kafkaProperties.getConsumer().getAutoCommitInterval());
        props.put("max.poll.records", kafkaProperties.getConsumer().getMaxPollRecords());
        props.put("max.poll.interval.ms", Integer.MAX_VALUE);
        props.put("key.deserializer", kafkaProperties.getConsumer().getKeyDeserializer());
        props.put("value.deserializer", kafkaProperties.getConsumer().getValueDeserializer());
        if (kafkaCustomConfig.isAuthSwitch()) {
            //安全协议
            props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
            props.put(SaslConfigs.SASL_MECHANISM, kafkaCustomConfig.getSaslMechanism());
            props.put(SaslConfigs.SASL_JAAS_CONFIG, kafkaCustomConfig.getSaslJaasConfig());
        }
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(topics);
        logger.info("Subscribe kafka topics: {}", JSONUtil.toJsonStr(topics));
    }


    public final void receive() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Counter(), 1, 1, TimeUnit.MINUTES);

        KafkaDataProcessor processor = new KafkaDataProcessor();

        while (working) {
            try {
                if (Thread.currentThread().isInterrupted()) {
                    logger.error("kafka receiver interruption");
                    return;
                }
                //// 根据 queuesize 的情况减慢消费速度
                //if ((processor.remainingCapacity() + NewDBTypeHandler.remainingCapacity()) < KafkaConfig.KAFKA_FETCH_MAX_SIZE / 2) {
                //    continue;
                //}
                //long pollInterval = intervalCalculator.getIntervalBetweenPolls();
                ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(100));
                //long onePollCostTime = intervalCalculator.getIntervalOnePollCost();
                //if (pollInterval > 1000 && pollInterval < 30000) {
                //    logger.info("The interval between two kafka polls is {}ms", pollInterval);
                //} else if (pollInterval >= 30000) {
                //    logger.warn("The interval between two kafka polls is {}ms, which is greater than 30 seconds.", pollInterval);
                //}
                //if (onePollCostTime >= 1000) {
                //    logger.info("One poll costs time is {}ms", onePollCostTime);
                //}
                for (ConsumerRecord<String, byte[]> record : records) {
                    byte[] data = record.value();
                    processor.process(data);
                    count++;
                }
            } catch (Throwable exception) {
                logger.error("kafka receiver error !", exception);
            }
        }
    }

    static class Counter implements Runnable {

        @Override
        public void run() {
            //try {
            //    logger.info("totally processed " + count + ", notify submitted: " + NotifySender.taskCount);
            //    count = 0;
            //    NotifySender.taskCount = 0;
            //    logger.info("totally handled {} jvm messages", RealtimeReceiver.count);
            //    RealtimeReceiver.count = 0;
            //    MessageProcessorFactory.printAndClearCounter();
            //} catch (Throwable ex) {
            //    logger.error("error happens whening counting message", ex);
            //}
        }
    }

    /**
     * close kafka consumer
     */
    public final void close() {
        if (this.consumer != null) {
            this.consumer.close();
        }
    }


}
