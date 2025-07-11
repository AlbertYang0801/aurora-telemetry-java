package com.aurora.clickhouse.handler;

import com.aurora.clickhouse.anno.ClickHouseTable;
import com.clickhouse.client.api.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author AlbertYang
 * @date 2025/7/11 16:03
 */
@Component
public class ClickHouseTableRegisterHandler {

    private static final Logger logger = LogManager.getLogger(ClickHouseInsertHandler.class);

    @Resource
    Client clickHouseClient;

    public void registerTables() {
        Map<Class<?>, String> clickHouseTables = scanClickHouseTables("com.aurora.entity");

        clickHouseTables.forEach((clazz, tableName) -> {
            //实体类和表结构映射
            clickHouseClient.register(clazz, clickHouseClient.getTableSchema(tableName));
            logger.info("Registered ClickHouse table: {}", tableName);
        });
    }

    public Map<Class<?>, String> scanClickHouseTables(String basePackage) {
        Reflections reflections = new Reflections(basePackage);
        Map<Class<?>, String> result = new HashMap<>();

        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(ClickHouseTable.class);

        for (Class<?> clazz : annotatedClasses) {
            ClickHouseTable annotation = clazz.getAnnotation(ClickHouseTable.class);
            result.put(clazz, annotation.value());
        }
        return result;
    }


}
