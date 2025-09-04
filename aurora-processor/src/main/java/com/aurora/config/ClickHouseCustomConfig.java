package com.aurora.config;

import com.aurora.properties.ClickHouseProperties;
import com.clickhouse.client.api.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
