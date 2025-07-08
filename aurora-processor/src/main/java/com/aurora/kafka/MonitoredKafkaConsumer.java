package com.aurora.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 监听KafkaConsumer
 *
 * @author yangjunwei
 * @date 2025/7/8 13:35
 */
public class MonitoredKafkaConsumer {

    private final KafkaConsumer<String, byte[]> consumer;

    private final KafkaPollMonitor monitor;

    // 上一次 poll 结束时间，用于计算间隔
    private final AtomicLong lastPollEndTime;

    public MonitoredKafkaConsumer(KafkaConsumer<String, byte[]> consumer, KafkaPollMonitor monitor) {
        this.consumer = consumer;
        this.monitor = monitor;
        this.lastPollEndTime = new AtomicLong(System.currentTimeMillis());
    }

    /**
     * 执行一次 Kafka poll操作
     * 计算两次 poll 的时间间隔
     * 计算本次 poll 耗时
     */
    public ConsumerRecords<String, byte[]> poll() {
        long start = System.currentTimeMillis();
        //上一次 poll 结束到现在的时间间隔
        long interval = start - lastPollEndTime.get();
        //记录poll间隔
        monitor.onPollInterval(interval);

        // 执行 Kafka 拉取操作，最多等待 100ms
        ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(100));

        long end = System.currentTimeMillis();
        //本次 poll 总耗时
        long duration = end - start;
        // 更新最后结束时间，用于下一次间隔计算
        lastPollEndTime.set(end);
        // 记录本次 poll 花费时间
        monitor.onPollDuration(duration);
        return records;
    }

    /**
     * 关闭 Kafka 消费者资源
     */
    public void close() {
        if(consumer!=null){
            consumer.close();
        }
    }


}
