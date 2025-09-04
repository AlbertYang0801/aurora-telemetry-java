CREATE DATABASE IF NOT EXISTS aurora;

-- 事件数据表
CREATE TABLE IF NOT EXISTS aurora.event_data (
    timestamp UInt64 COMMENT '事件时间戳（毫秒）',
    event_time DateTime64(3) MATERIALIZED fromUnixTimestamp64Milli(timestamp) COMMENT '事件时间（用于查询分区）',
    event_type UInt32 COMMENT '事件类型（4字节无符号整数）',
    trace_id FixedString(32) COMMENT '跟踪上下文ID（16字节转32位十六进制）',
    source_id String COMMENT '上报来源标识',
    event_data_json String COMMENT '事件数据内容（JSON格式存储map<string,string>）'
) ENGINE = MergeTree()
PARTITION BY (toYYYYMM(event_time), event_type % 100)  -- 按月和事件类型分区
PRIMARY KEY (event_time, event_type, source_id)  -- 主键优化查询
ORDER BY (event_time, event_type, source_id, timestamp)  -- 排序键
TTL event_time + INTERVAL 30 DAY  -- 数据保留30天
SETTINGS index_granularity = 8192;


-- 指标数据表
CREATE TABLE IF NOT EXISTS aurora.metric_data (
    timestamp UInt64 COMMENT '指标时间戳（毫秒）',
    metric_time DateTime64(3) MATERIALIZED fromUnixTimestamp64Milli(timestamp) COMMENT '指标时间（用于查询分区）',
    trace_id FixedString(32) COMMENT '跟踪上下文ID（16字节转32位十六进制）',
    source_id String COMMENT '上报来源标识',
    metric_id Int32 COMMENT '指标id',
    metric_value Float64 COMMENT '指标值'
) ENGINE = MergeTree()
PARTITION BY (toYYYYMM(metric_time), metric_id % 100)  -- 按日期和指标ID分区
PRIMARY KEY (metric_time, metric_id, source_id)  -- 主键优化查询
ORDER BY (metric_time, metric_id, source_id, timestamp)  -- 排序键
TTL metric_time + INTERVAL 90 DAY  -- 数据保留90天
SETTINGS index_granularity = 8192;


-- 事件数据分布式表
CREATE TABLE IF NOT EXISTS aurora.event_data_cluster AS aurora.event_data
ENGINE = Distributed(
    default,                    -- 集群名称
    aurora,                     -- 数据库名
    event_data,                 -- 本地表名
    xxHash64(source_id, event_type)  -- 分片键
);

-- 指标数据分布式表  
CREATE TABLE IF NOT EXISTS aurora.metric_data_cluster AS aurora.metric_data
ENGINE = Distributed(
    default,                    -- 集群名称
    aurora,                     -- 数据库名
    metric_data,                -- 本地表名
    xxHash64(source_id, metric_id)   -- 分片键
);