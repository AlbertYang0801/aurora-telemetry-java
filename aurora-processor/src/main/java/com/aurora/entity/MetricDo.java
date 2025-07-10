package com.aurora.entity;

import com.aurora.clickhouse.ClickHouseTable;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yangjunwei
 * @date 2025/7/8 18:22
 */
@Data
@ClickHouseTable(value = "metric_data")
public class MetricDo extends BaseClickhouseData implements Serializable {

    @Serial
    private static final long serialVersionUID = 1251213123L;

    private Integer placeId;

    private String ip;

    private Long time;

    private Integer tid;

    private Integer pid;

    private Double value;



}
