package com.aurora.kafka;

import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author AlbertYang
 * @date 2025/7/7 15:17
 */
@Component
public class KafkaCustomProducer {

    private static final Logger logger = LogManager.getLogger(KafkaCustomProducer.class);

    @Resource
    private KafkaTemplate<String, byte[]> kafkaTemplate;

    /**
     * 发送消息到Kafka
     * @param topic
     * @param key
     * @param value
     */
    public void send(String topic, String key, byte[] value) {
        CompletableFuture<SendResult<String, byte[]>> future = kafkaTemplate.send(topic, key, value);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                logger.info("发送成功 -> topic:{}, partition:{}, offset:{}",
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            } else {
                logger.error("Kafka发送失败: {}", ex.getMessage());
            }
        });
    }

    /**
     * 发送消息到Kafka（带重试机制）
     */
    @Retryable(
            value = {Exception.class}, // 重试的异常类型
            maxAttempts = 3,           // 最大重试次数
            backoff = @Backoff(delay = 1000, multiplier = 2) // 退避策略：初始1秒，倍数增长
    )
    public void sendWithRetry(String topic, String key, byte[] value) throws Exception {
        try {
            SendResult<String, byte[]> result = kafkaTemplate.send(topic, key, value).get();
            logger.info("发送成功 -> topic:{}, partition:{}, offset:{}",
                    result.getRecordMetadata().topic(),
                    result.getRecordMetadata().partition(),
                    result.getRecordMetadata().offset());
        } catch (Exception e) {
            logger.error("Kafka发送失败 - topic:{}, key:{}, 重试中... error:{}",
                    topic, key, e.getMessage());
            throw new RuntimeException("发送失败，触发重试", e);
        }
    }


}
