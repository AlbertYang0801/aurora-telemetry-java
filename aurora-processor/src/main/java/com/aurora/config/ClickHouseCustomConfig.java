package com.aurora.config;

import com.clickhouse.client.api.Client;
import org.apache.hc.core5.concurrent.DefaultThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yangjunwei
 * @date 2025/7/8 18:21
 */
@Configuration
public class ClickHouseCustomConfig {

    @Autowired
    ClickHouseProperties clickHouseProperties;

    @Bean(destroyMethod = "close")
    public Client clickHouseClient() {
        //clickhouse写入的线程池
        ExecutorService executorService = new ThreadPoolExecutor(clickHouseProperties.getCoreThreadSize(),
                clickHouseProperties.getMaxThreadSize(),
                60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(clickHouseProperties.getMaxQueueSize())
                , new DefaultThreadFactory("clickHouse-operation"));

        return new Client.Builder()
                .addEndpoint(clickHouseProperties.getUrl())
                .setUsername(clickHouseProperties.getUsername())
                .setPassword(clickHouseProperties.getPassword())
                .setDefaultDatabase(clickHouseProperties.getDatabase())
                //开启连接池
                .enableConnectionPool(true)
                .setConnectTimeout(clickHouseProperties.getConnectionTimeout())
                .setSocketTimeout(clickHouseProperties.getSocketTimeout())
                //重试次数
                .setMaxRetries(3)
                //客户端是否应压缩其请求
                .compressClientRequest(true)
                //开启异步请求
                .useAsyncRequests(true)
                .setSharedOperationExecutor(executorService)
                .build();
    }


}
