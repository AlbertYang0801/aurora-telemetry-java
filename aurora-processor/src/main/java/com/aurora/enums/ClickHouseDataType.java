package com.aurora.enums;

import lombok.Getter;

/**
 * @author AlbertYang
 */
@Getter
public enum ClickHouseDataType {

    METRIC("metric_data", 5000, 10);

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

    ClickHouseDataType(String tableName, int flushSize, int flushTimeSeconds) {
        this.tableName = tableName;
        this.flushSize = flushSize;
        this.flushTimeSeconds = flushTimeSeconds;
    }


}
