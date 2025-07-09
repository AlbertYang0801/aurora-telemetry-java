package com.aurora.clickhouse;

import com.aurora.entity.BaseMetric;
import com.aurora.enums.ClickHouseDataType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yangjunwei
 * @date 2025/7/9 18:53
 */
@Component
public class ClickHouseDataExporter {

    private ClickHouseDataBuffer clickHouseDataBuffer;

    public ClickHouseDataExporter() {
        this.clickHouseDataBuffer = new ClickHouseDataBuffer();
    }

    public void export(BaseMetric metric, ClickHouseDataType type) {
        clickHouseDataBuffer.insertData(metric, type);
    }

    public void exportBatch(List<? extends BaseMetric> metrics, ClickHouseDataType type) {
        metrics.forEach(metric -> clickHouseDataBuffer.insertData(metric, type));
    }


}
