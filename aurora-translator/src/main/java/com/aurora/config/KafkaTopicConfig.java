package com.aurora.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangjunwei
 * @date 2025/7/7 15:39
 */
@Configuration
@ConfigurationProperties(prefix = "kafka.topics")
@Data
public class KafkaTopicConfig {

    private String metricTopic;

}
