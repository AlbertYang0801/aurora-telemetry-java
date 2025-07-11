package com.aurora.entity;

import com.aurora.clickhouse.anno.ClickHouseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author AlbertYang
 * @date 2025/7/8 18:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ClickHouseTable(value = "device_metric")
public class DeviceMetricDo extends BaseClickhouseData implements Serializable {

    @Serial
    private static final long serialVersionUID = 1251213123L;

    private Integer placeId;

    private String ip;

    private LocalDateTime time;

    private Integer tid;

    private Double value;



}
