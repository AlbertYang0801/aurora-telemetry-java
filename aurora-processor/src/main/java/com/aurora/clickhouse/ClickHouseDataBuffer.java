package com.aurora.clickhouse;


import com.aurora.clickhouse.task.BaseFlushTask;
import com.aurora.clickhouse.task.MetricFlushTask;
import com.aurora.entity.BaseMetric;
import com.aurora.enums.ClickHouseDataType;

import java.util.HashMap;
import java.util.Map;

/**
 * ck数据buffer
 *
 * @author AlbertYang
 */
public class ClickHouseDataBuffer {

    private final Map<String, DataBuffer<BaseMetric>> bufferMap = new HashMap<>();

    public void insertData(BaseMetric metric, ClickHouseDataType type) {
        getDataBuffer(type).add(metric);
    }

    private BaseFlushTask<BaseMetric> getFlushTask(ClickHouseDataType type) {
        switch (type) {
            case METRIC:
                return new MetricFlushTask();
            default:
                break;
        }
        return null;
    }

    private DataBuffer<BaseMetric> getDataBuffer(ClickHouseDataType clickHouseDataType) {
        if (!bufferMap.containsKey(clickHouseDataType.getTableName())) {
            DataBuffer<BaseMetric> baseMetricDataBuffer = new DataBuffer<>(getFlushTask(clickHouseDataType), clickHouseDataType.getFlushSize(), clickHouseDataType.getFlushTimeSeconds());
            bufferMap.put(clickHouseDataType.getTableName(), baseMetricDataBuffer);
            return baseMetricDataBuffer;
        }
        return bufferMap.get(clickHouseDataType.getTableName());
    }


}
