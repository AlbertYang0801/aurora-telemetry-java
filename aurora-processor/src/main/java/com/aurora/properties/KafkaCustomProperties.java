package com.aurora.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author AlbertYang
 * @date 2025/7/7 15:39
 */
@Configuration
@ConfigurationProperties(prefix = "kafka.custom")
@Data
public class KafkaCustomProperties {


    private List<CustomConsumerProperties> consumers;

    /**
     * topic配置
     */
    @Data
    public static class CustomConsumerProperties {

        private String name;

        private Integer threadPoolSize;

        private String consumerGroup;

        private List<CustomTopic> topics;

    }

    @Data
    public static class CustomTopic {

        private String name;

        private String topic;

    }


}
