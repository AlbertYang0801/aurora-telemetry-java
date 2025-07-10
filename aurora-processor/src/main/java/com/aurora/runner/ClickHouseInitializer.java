package com.aurora.runner;

import com.aurora.clickhouse.ClickHouseTable;
import com.aurora.entity.BaseClickhouseData;
import com.aurora.enums.ClickHouseDataType;
import com.clickhouse.client.api.Client;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        Map<Class<?>, String> clickHouseTables = scanClickHouseTables("com.aurora.entity");

        clickHouseTables.forEach((clazz, tableName) -> {
            //实体类和表结构映射
            clickHouseClient.register(clazz, clickHouseClient.getTableSchema(tableName));
        });
    }

    public Map<Class<?>, String> scanClickHouseTables(String basePackage) {
        Reflections reflections = new Reflections(basePackage);
        Map<Class<?>, String> result = new HashMap<>();

        // 获取所有带有ClickHouseTable注解的类
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(ClickHouseTable.class);

        for (Class<?> clazz : annotatedClasses) {
            ClickHouseTable annotation = clazz.getAnnotation(ClickHouseTable.class);
            result.put(clazz, annotation.value());
        }

        return result;
    }


}
