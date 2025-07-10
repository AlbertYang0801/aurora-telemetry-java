package com.aurora.clickhouse;

import com.aurora.entity.BaseClickhouseData;
import com.aurora.enums.ClickHouseDataType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yangjunwei
 * @date 2025/7/9 18:53
 */
@Component
public class ClickHouseDataExporter {

    private final ClickHouseDataBuffer buffer;

    /**
     * Spring注入保证buffer单例
     * @param clickHouseDataBuffer
     */
    public ClickHouseDataExporter(ClickHouseDataBuffer clickHouseDataBuffer) {
        this.buffer = clickHouseDataBuffer;
    }

    public void export(BaseClickhouseData metric, ClickHouseDataType type) {
        buffer.insertData(metric, type);
    }

    public void exportBatch(List<? extends BaseClickhouseData> metrics, ClickHouseDataType type) {
        metrics.forEach(metric -> buffer.insertData(metric, type));
    }


}
