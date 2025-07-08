package com.aurora.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author yangjunwei
 * @date 2025/7/8 18:22
 */
@Data
public class Metric {

    private Integer placeId;
    private String ip;
    private LocalDateTime time;
    private Integer tid;
    private Integer pid;
    private Integer value;

}
