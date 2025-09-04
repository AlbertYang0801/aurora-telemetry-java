package com.aurora.entity;

import com.aurora.clickhouse.anno.ClickHouseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 事件数据实体类
 * 对应 ClickHouse event_data 表
 * 
 * @author yangjunwei
 * @date 2025/9/3 23:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ClickHouseTable(value = "event_data")
public class EventDataDo extends BaseClickhouseData implements Serializable {

    @Serial
    private static final long serialVersionUID = 1251213125L;

    /**
     * 事件时间戳（毫秒）
     */
    private Long timestamp;

    /**
     * 事件类型（4字节无符号整数）
     */
    private Long eventType;

    /**
     * 跟踪上下文ID（16字节转32位十六进制）
     */
    private String traceId;

    /**
     * 上报来源标识
     */
    private String sourceId;

    /**
     * 事件数据内容（JSON格式存储map<string,string>）
     */
    private String eventDataJson;


}