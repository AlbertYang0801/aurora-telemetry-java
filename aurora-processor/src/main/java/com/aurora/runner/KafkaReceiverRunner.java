package com.aurora.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 自定义接收Kafka消息
 * @author AlbertYang
 * @date 2025/7/7 18:11
 */
@Component
public class KafkaReceiverRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
