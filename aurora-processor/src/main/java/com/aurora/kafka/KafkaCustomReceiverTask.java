package com.aurora.kafka;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.thread.NamedThreadFactory;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.aurora.properties.KafkaCustomProperties;
import com.aurora.handler.BlockReceiverRejectionHandler;
import com.aurora.kafka.consumer.LoggingKafkaPollMonitor;
import com.aurora.kafka.consumer.MonitoredKafkaConsumer;
import com.aurora.processor.TopicDataProcessorFactory;
import com.aurora.processor.topic.TopicDataProcessor;
import jakarta.annotation.PreDestroy;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * 按条异步消费kafka数据
 *
 * @author AlbertYang
 * @date 2025/7/7 18:11
 */
public class KafkaCustomReceiverTask implements Runnable {

    private static final Logger logger = LogManager.getLogger(KafkaCustomReceiverTask.class.getName());

    protected volatile boolean working = true;

    protected final AtomicLong logCount = new AtomicLong(0);

    protected MonitoredKafkaConsumer monitoredKafkaConsumer;
    protected ThreadPoolExecutor executor;

    protected ScheduledExecutorService scheduler;

    private final KafkaCustomProperties.CustomConsumerProperties customConsumerProperties;
    private final String consumerName;

    private final Map<String, String> topicMap;

    public KafkaCustomReceiverTask(KafkaCustomProperties.CustomConsumerProperties customConsumerProperties) {
        this.customConsumerProperties = customConsumerProperties;
        this.consumerName = customConsumerProperties.getName();
        this.topicMap = CollUtil.emptyIfNull(customConsumerProperties.getTopics()).stream()
                .collect(Collectors.toMap(KafkaCustomProperties.CustomTopic::getTopic, KafkaCustomProperties.CustomTopic::getName));
    }

    @Override
    public void run() {
        //初始化线程池
        initExecutor();
        //初始化kafka消费者
        initKafkaConsumer();
        //异步统计消息数量
        countMessage();
        //接收kafka消息
        receive();
    }

    /**
     * 消费线程隔离，为不同消费者创建不同线程池
     */
    protected void initExecutor() {
        executor = new ThreadPoolExecutor(customConsumerProperties.getThreadPoolSize(),
                customConsumerProperties.getThreadPoolSize(),
                0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(getExecutorQueueSize()),
                new NamedThreadFactory(consumerName, true),
                new BlockReceiverRejectionHandler(500, consumerName));
    }

    private void initKafkaConsumer() {
        Properties props = SpringUtil.getBean("kafkaConsumerProperties");
        //消费者组隔离
        props.put(ConsumerConfig.GROUP_ID_CONFIG, customConsumerProperties.getConsumerGroup());

        KafkaConsumer<String, byte[]> consumer = new KafkaConsumer<>(props);
        //订阅的Topic列表
        Set<String> topics = topicMap.keySet();
        consumer.subscribe(topics);
        logger.info("[{}] Subscribe kafka topics: {}", consumerName, JSONUtil.toJsonStr(topics));
        monitoredKafkaConsumer = new MonitoredKafkaConsumer(consumer, new LoggingKafkaPollMonitor());
    }

    /**
     * 消费kafka消息
     */
    protected void receive() {
        try {
            logger.info("[{}] start to receive kafka message", consumerName);
            while (working) {
                try {
                    if (Thread.currentThread().isInterrupted()) {
                        logger.error("{} kafka receiver interruption", consumerName);
                        return;
                    }
                    //控制kafka消费速度
                    if (processorRemainingCapacity() < getExecutorQueueSize() / 2) {
                        continue;
                    }
                    ConsumerRecords<String, byte[]> records = monitoredKafkaConsumer.poll();
                    if (records.isEmpty()) {
                        continue;
                    }
                    //单线程拉取，异步处理消息，自动提交offset，允许丢数据
                    executor.execute(() -> processConsumerRecords(records));
                } catch (Throwable exception) {
                    logger.error("{} receiver error !", consumerName, exception);
                }
            }
        } finally {
            monitoredKafkaConsumer.close();
        }
    }

    private void processConsumerRecords(ConsumerRecords<String, byte[]> records) {
        for (ConsumerRecord<String, byte[]> record : records) {
            String topicName = topicMap.get(record.topic());
            if (topicName == null || !TopicDataProcessorFactory.contains(topicName)) {
                logger.warn("{} topic not config, topic: {}", consumerName, record.topic());
                continue;
            }
            logger.debug("{} receive message, topic: {}, partition: {}, offset: {}", consumerName, record.topic(), record.partition(), record.offset());
            //根据topic分发消息
            TopicDataProcessor topicDataProcessor = TopicDataProcessorFactory.getProcessor(topicName);
            if (Objects.nonNull(topicDataProcessor)) {
                topicDataProcessor.process(record);
            }
        }
        //日志记录处理数量
        logCount.addAndGet(records.count());
    }

    /**
     * 线程池队列大小
     * 控制消费速率
     * 统一设置为消费者单次拉取消息数量的2倍
     *
     * @return
     */
    public int getExecutorQueueSize() {
        return SpringUtil.getBean(KafkaProperties.class).getConsumer().getMaxPollRecords() * 2;
    }

    /**
     * 线程池队列剩余容量
     *
     * @return
     */
    protected int processorRemainingCapacity() {
        return executor.getQueue().remainingCapacity();
    }

    /**
     * 统计消息数量
     */
    private void countMessage() {
        logger.info("{} start count message", consumerName);
        scheduler = Executors.newScheduledThreadPool(1, new NamedThreadFactory(consumerName + "-scheduler", true));
        //1分钟打印一次日志
        scheduler.scheduleAtFixedRate(this::logThroughput, 1, 1, TimeUnit.MINUTES);
    }

    private void logThroughput() {
        long currentCount = logCount.getAndSet(0);
        if (currentCount > 0) {
            logger.info("{} processed {} messages", consumerName, currentCount);
        }
    }

    @PreDestroy
    public void shutdown() {
        working = false;
        monitoredKafkaConsumer.close();
        //等待线程池任务完成
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                logger.warn("{} receiver thread pool did not terminate", consumerName);
            }
        } catch (InterruptedException e) {
            logger.error("{} receiver thread pool interrupted", consumerName, e);
        }
        //等待定时任务完成
        try {
            if (!scheduler.awaitTermination(3, TimeUnit.SECONDS)) {
                logger.warn("{} receiver scheduler did not terminate", consumerName);
            }
        } catch (InterruptedException e) {
            logger.error("{} receiver scheduler interrupted", consumerName, e);
        }
        logger.info("{} receiver shutdown", consumerName);
    }


}
