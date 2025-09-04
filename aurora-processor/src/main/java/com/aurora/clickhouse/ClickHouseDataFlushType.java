package com.aurora.clickhouse;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AlbertYang
 */
@Getter
public enum ClickHouseDataFlushType {

    METRIC("metric", 100000, 60),

    EVENT("event", 10000, 30);

    /**
     * 表名
     */
    private final String tableName;

    /**
     * 数据分批写入ck的大小
     */
    private final int flushSize;

    /**
     * 数据分批写入ck的间隔时间
     */
    private final int flushTimeSeconds;

    ClickHouseDataFlushType(String tableName, int flushSize, int flushTimeSeconds) {
        this.tableName = tableName;
        this.flushSize = flushSize;
        this.flushTimeSeconds = flushTimeSeconds;
    }

    public static List<String> getAllTableNames() {
        return Arrays.stream(ClickHouseDataFlushType.values()).map(ClickHouseDataFlushType::getTableName).collect(Collectors.toList());
    }


}
