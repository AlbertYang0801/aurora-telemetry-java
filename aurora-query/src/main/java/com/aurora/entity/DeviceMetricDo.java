package com.aurora.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author AlbertYang
 * @date 2025/7/8 18:22
 */
@Data
@TableName(value = "device_metric")
public class DeviceMetricDo {

    @TableField("place_id")
    private Integer placeId;

    @TableField("ip")
    private String ip;

    @TableField("time")
    private LocalDateTime time;

    @TableField("tid")
    private Integer tid;

    @TableField("value")
    private Double value;


}
