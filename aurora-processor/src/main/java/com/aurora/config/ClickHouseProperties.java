package com.aurora.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangjunwei
 * @date 2025/7/8 18:21
 */
@Configuration
@ConfigurationProperties(prefix = "clickhouse")
@Data
public class ClickHouseProperties {

    private String url;

    private String username;

    private String password;

    private String database;

    private int socketTimeout;

    private int connectionTimeout;

    private int coreThreadSize;

    private int maxThreadSize;

    private int maxQueueSize;


}
