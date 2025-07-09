package com.aurora.clickhouse.task;

import com.aurora.entity.BaseMetric;
import com.aurora.enums.ClickHouseDataType;

/**
 * @author yangjunwei
 * @date 2025/7/9 17:42
 */
public class MetricFlushTask extends BaseFlushTask<BaseMetric> {

    @Override
    protected ClickHouseDataType getClickHouseDataType() {
        return ClickHouseDataType.METRIC;
    }


}
