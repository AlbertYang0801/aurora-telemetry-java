package com.aurora.entity;

import com.aurora.clickhouse.anno.ClickHouseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * 指标数据实体类
 * 对应 ClickHouse metric_data 表
 * 
 * @author yangjunwei
 * @date 2025/9/3 23:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ClickHouseTable(value = "metric_data")
public class MetricDataDo extends BaseClickhouseData implements Serializable {

    @Serial
    private static final long serialVersionUID = 1251213126L;

    /**
     * 指标时间戳（毫秒）
     */
    private Long timestamp;

    /**
     * 跟踪上下文ID（16字节转32位十六进制）
     */
    private String traceId;

    /**
     * 上报来源标识
     */
    private String sourceId;

    /**
     * 指标ID
     */
    private Integer metricId;

    /**
     * 指标值
     */
    private Double metricValue;


}