package com.aurora.runner;

import com.aurora.kafka.KafkaCustomReceiver;
import com.aurora.processor.MetricDataProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 自定义接收Kafka消息
 *
 * @author AlbertYang
 * @date 2025/7/7 18:11
 */
@Component
public class KafkaReceiverRunner implements ApplicationRunner {

    private static final Logger logger = LogManager.getLogger(KafkaReceiverRunner.class);


    @Autowired
    KafkaCustomReceiver kafkaCustomReceiver;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("KafkaReceiverRunner start ...");
        kafkaCustomReceiver.start();
    }


}
