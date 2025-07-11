package com.aurora.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author AlbertYang
 * @date 2025/7/7 15:39
 */
@Configuration
@ConfigurationProperties(prefix = "kafka.custom")
@Data
public class KafkaCustomProperties {

    private boolean authSwitch;

    private String saslMechanism;

    private String saslJaasConfig;

    private Integer consumerThreadPoolSize;

    private String deviceMetricTopic;

    private String processMetricTopic;

}
