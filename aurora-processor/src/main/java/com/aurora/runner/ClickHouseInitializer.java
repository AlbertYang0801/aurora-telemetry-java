package com.aurora.runner;

import com.aurora.enums.ClickHouseDataType;
import com.clickhouse.client.api.Client;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yangjunwei
 * @date 2025/7/9 16:21
 */
@Component
public class ClickHouseInitializer implements ApplicationRunner {

    @Resource
    Client clickHouseClient;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (ClickHouseDataType clickHouseDataType : ClickHouseDataType.values()) {
            //实体类和表结构映射
            clickHouseClient.register(clickHouseDataType.getClazz(), clickHouseClient.getTableSchema(clickHouseDataType.getTableName()));
        }
    }


}
