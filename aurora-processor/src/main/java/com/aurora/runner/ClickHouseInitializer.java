package com.aurora.runner;

import com.aurora.clickhouse.handler.ClickHouseTableRegisterHandler;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


/**
 * @author AlbertYang
 * @date 2025/7/9 16:21
 */
@Component
public class ClickHouseInitializer implements ApplicationRunner {

    @Resource
    ClickHouseTableRegisterHandler clickHouseTableRegisterHandler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        clickHouseTableRegisterHandler.registerTables();
    }


}
