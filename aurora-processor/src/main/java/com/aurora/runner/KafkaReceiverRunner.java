package com.aurora.runner;

import com.aurora.properties.KafkaCustomProperties;
import com.aurora.kafka.KafkaCustomReceiverTask;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 自定义接收Kafka消息
 *
 * @author AlbertYang
 * @date 2025/7/7 18:11
 */
@Component
public class KafkaReceiverRunner implements ApplicationRunner {

    private static final Logger logger = LogManager.getLogger(KafkaReceiverRunner.class);

    @Resource
    KafkaCustomProperties kafkaCustomProperties;

    private ExecutorService executor;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("KafkaReceiverRunner start ...");
        List<KafkaCustomProperties.CustomConsumerProperties> consumers = kafkaCustomProperties.getConsumers();
        //线程隔离消费者组
        executor = Executors.newFixedThreadPool(consumers.size());
        for (KafkaCustomProperties.CustomConsumerProperties customConsumerProperties : consumers) {
            executor.submit(new KafkaCustomReceiverTask(customConsumerProperties));
        }
    }

    @PreDestroy
    public void shutdownExecutor() {
        if (executor != null) {
            executor.shutdown();
            try {
                // 等待指定时间让线程池关闭，超时则强制关闭
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
            logger.info("ExecutorService已关闭");
        }
    }


}
