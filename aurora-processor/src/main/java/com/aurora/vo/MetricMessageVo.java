package com.aurora.vo;

import lombok.Data;

/**
 * @author AlbertYang
 * @date 2025/7/7 14:56
 */
@Data
public class MetricMessageVo {

    private Integer placeId;

    private String ip;

    private Long time;

    private Integer tid;

    private Integer pid;

    private Double value;

}
