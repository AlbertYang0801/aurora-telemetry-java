package com.aurora.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author liujiang
 */
@Configuration
@Data
public class PropertiesConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaBootstrapServer;

    /**
     * kafka认证开关
     */
    @Value("${spring.kafka.auth-switch}")
    private Boolean authSwitch;

    @Value("${spring.kafka.config.security.protocol}")
    private String securityProtocolConfig;
    @Value("${spring.kafka.config.sasl.mechanism}")
    private String saslMechanism;
    @Value("${spring.kafka.config.sasl.username}")
    private String username;
    @Value("${spring.kafka.config.sasl.password}")
    private String password;
    @Value("${spring.kafka.config.sasl.truststore_password}")
    private String truststorePassword;
    @Value("${spring.kafka.config.ssl.endpoint.identification.algorithm}")
    private String sslEndpointIdentificationAlgorithm;
    @Value("${spring.kafka.config.ssl.truststore-location}")
    private String sslTruststoreLocation;

    /**
     * 生产者配置
     */
    @Value("${spring.kafka.producer.max-block-ms}")
    private String kafkaProducerMaxBlockMs;
    @Value("${spring.kafka.producer.retries}")
    private String kafkaProducerRetries;
    @Value("${spring.kafka.producer.reconnect-backoff-ms}")
    private String kafkaProducerReconnectBackoffMs;
    @Value("${spring.kafka.producer.enable-idempotence}")
    private Boolean kafkaEnableIdempotence;




}
