package com.aurora.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author AlbertYang
 * @date 2025/7/7 15:39
 */
@Configuration
@ConfigurationProperties(prefix = "kafka.custom")
@Data
public class KafkaCustomConfig {

    private String metricTopic;

    private String eventTopic;


}
