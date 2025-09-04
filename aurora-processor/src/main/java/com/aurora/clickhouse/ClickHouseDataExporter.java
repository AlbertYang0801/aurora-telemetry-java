package com.aurora.clickhouse;

import com.aurora.clickhouse.buffer.ClickHouseDataBuffer;
import com.aurora.entity.BaseClickhouseData;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author AlbertYang
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

    public void export(BaseClickhouseData metric, ClickHouseDataFlushType type) {
        buffer.insertData(metric, type);
    }

    public void exportBatchToBuffer(List<? extends BaseClickhouseData> metrics, ClickHouseDataFlushType type) {
        metrics.forEach(metric -> buffer.insertData(metric, type));
    }


}
