package com.aurora.runner;

import com.aurora.kafka.KafkaCustomReceiver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 自定义接收Kafka消息
 *
 * @author AlbertYang
 * @date 2025/7/7 18:11
 */
@Component
public class ClickHouseRunner implements ApplicationRunner {

    private static final Logger logger = LogManager.getLogger(ClickHouseRunner.class);


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS metric");
    }


}
