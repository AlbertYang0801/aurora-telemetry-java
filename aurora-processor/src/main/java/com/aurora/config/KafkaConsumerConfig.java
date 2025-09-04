package com.aurora.config;


import com.aurora.properties.PropertiesConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * Kafka消费端配置
 *
 * @author liujiang
 */
@Configuration
public class KafkaConsumerConfig {

    @Autowired
    private PropertiesConfig propertiesConfig;

    ///**
    // * 配置监听，将消费工厂信息配置进去
    // */
    //@Bean
    //public ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
    //    ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    //    factory.setConsumerFactory(consumerFactory());
    //    // 多线程消费
    //    factory.setConcurrency(propertiesConfig.getKafkaConsumerConcurrency());
    //    return factory;
    //}
    //
    ///**
    // * 消费消费工厂
    // */
    //@Bean
    //public ConsumerFactory<Integer, String> consumerFactory() {
    //    return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    //}

    @Bean
    public Properties kafkaConsumerProperties() {
        Properties properties = new Properties();
        properties.putAll(consumerConfigs());
        return properties;
    }

    /**
     * 消费配置
     */
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>(15);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, propertiesConfig.getKafkaBootstrapServer());
        // Kafka 消息的序列化方式
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class.getName());

        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, propertiesConfig.getKafkaConsumerAutoCommit());
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, propertiesConfig.getKafkaConsumerAutoCommitInterval());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, propertiesConfig.getKafkaConsumerMaxPollRecords());
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, propertiesConfig.getKafkaConsumerMaxPartitionFetchBytes());
        props.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, propertiesConfig.getKafkaConsumerFetchMaxBytes());
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, propertiesConfig.getKafkaConsumerSessionTimeout());

        //开启认证
        if (Objects.nonNull(propertiesConfig.getAuthSwitch()) && propertiesConfig.getAuthSwitch()) {
            // ssl 认证
            KafkaSaslConfig.kafkaSaslConfig(props, propertiesConfig);
            //hostname校验改成空
            props.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, propertiesConfig.getSslEndpointIdentificationAlgorithm());
        }

        return props;
    }


}
