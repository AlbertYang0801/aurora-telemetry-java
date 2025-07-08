package com.aurora.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author yangjunwei
 * @date 2025/7/8 18:21
 */
@Configuration
public class ClickHouseConfig {

    @Bean(name = "clickHouseDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.clickhouse")
    public DataSource clickHouseDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();    }

    @Bean(name = "clickHouseJdbcTemplate")
    public JdbcTemplate clickHouseJdbcTemplate(@Qualifier("clickHouseDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


}
