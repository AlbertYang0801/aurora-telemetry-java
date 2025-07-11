package com.aurora.clickhouse;

import com.aurora.config.ClickHouseProperties;
import com.clickhouse.client.api.Client;
import com.clickhouse.client.api.ClientFaultCause;
import org.apache.hc.core5.concurrent.DefaultThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author AlbertYang
 * @date 2025/7/8 18:21
 */
@Configuration
public class ClickHouseCustomConfig {

    @Autowired
    ClickHouseProperties clickHouseProperties;

    @Bean(destroyMethod = "close")
    public Client clickHouseClient() {
        return new Client.Builder()
                .addEndpoint(clickHouseProperties.getUrl())
                .setUsername(clickHouseProperties.getUsername())
                .setPassword(clickHouseProperties.getPassword())
                .setDefaultDatabase(clickHouseProperties.getDatabase())
                //开启连接池
                .enableConnectionPool(true)
                .setConnectTimeout(clickHouseProperties.getConnectionTimeout())
                .setSocketTimeout(clickHouseProperties.getSocketTimeout())
                //客户端是否应压缩其请求
                .compressClientRequest(true)
                .build();
    }


}
