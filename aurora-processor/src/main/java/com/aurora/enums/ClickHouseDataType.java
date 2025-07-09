package com.aurora.enums;

import com.aurora.entity.MetricDo;
import lombok.Getter;

/**
 * @author AlbertYang
 */
@Getter
public enum ClickHouseDataType {

    METRIC("metric", MetricDo.class, 5000, 10);

    /**
     * 表名
     */
    private final String tableName;
    /**
     * 表对应的实体类
     */
    private final Class<?> clazz;

    /**
     * 数据分批写入ck的大小
     */
    private final int flushSize;

    /**
     * 数据分批写入ck的间隔时间
     */
    private final int flushTimeSeconds;

    ClickHouseDataType(String tableName, Class<?> clazz, int flushSize, int flushTimeSeconds) {
        this.tableName = tableName;
        this.clazz = clazz;
        this.flushSize = flushSize;
        this.flushTimeSeconds = flushTimeSeconds;
    }


}
