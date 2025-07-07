package com.aurora.kafka;

import com.aurora.config.KafkaTopicConfig;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

/**
 * @author yangjunwei
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


}
