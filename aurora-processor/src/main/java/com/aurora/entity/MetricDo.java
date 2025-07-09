package com.aurora.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author yangjunwei
 * @date 2025/7/8 18:22
 */
@Data
public class MetricDo extends BaseMetric implements Serializable {

    @Serial
    private static final long serialVersionUID = 1251213123L;

    private Integer placeId;

    private String ip;

    private Long time;

    private Integer tid;

    private Integer pid;

    private Double value;

}
