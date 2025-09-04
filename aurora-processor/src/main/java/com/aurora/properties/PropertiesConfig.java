package com.aurora.properties;

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
    @Value("${spring.kafka.config.ssl.enable}")
    private Boolean sslEnable;
    @Value("${spring.kafka.config.ssl.truststore-location}")
    private String sslTruststoreLocation;

    /*
     * 消费者配置
     */
    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private Boolean kafkaConsumerAutoCommit;
    @Value("${spring.kafka.consumer.auto-commit-interval}")
    private Integer kafkaConsumerAutoCommitInterval;
    @Value("${spring.kafka.consumer.max-poll-records}")
    private Integer kafkaConsumerMaxPollRecords;
    @Value("${spring.kafka.consumer.max-partition-fetch-bytes}")
    private Integer kafkaConsumerMaxPartitionFetchBytes;
    @Value("${spring.kafka.consumer.fetch-max-bytes}")
    private Integer kafkaConsumerFetchMaxBytes;
    @Value("${spring.kafka.consumer.session-timeout}")
    private String kafkaConsumerSessionTimeout;


}
