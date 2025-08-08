CREATE DATABASE IF NOT EXISTS aurora;

CREATE TABLE IF NOT EXISTS aurora.device_metric
(
    `place_id` UInt32,
    `ip` String,
    `time` DateTime64(3, 'Asia/Shanghai'),
    `c_flag` String,
    `tid` UInt32,
    `value` Float64,
    `date` Date MATERIALIZED toDate(time) -- 物化日期列
)
    ENGINE = MergeTree() PARTITION BY date  -- 按天分区(直接使用物化日期列)
ORDER BY (place_id,ip, tid, time)  -- 保持原有排序键
TTL time + INTERVAL 7 DAY  -- 设置7天自动过期
SETTINGS index_granularity = 8192;


CREATE TABLE IF NOT EXISTS aurora.process_metric
(
    `place_id` UInt32,
    `ip` String,
    `time` DateTime64(3, 'Asia/Shanghai'),
    `c_flag` String,
    `xid` UInt64,
    `tid` UInt32,
    `value` Float64,
    `date` Date MATERIALIZED toDate(time) -- 物化日期列
)
    ENGINE = MergeTree() PARTITION BY date  -- 按天分区(直接使用物化日期列)
ORDER BY (place_id,ip,xid, tid, time)  -- 保持原有排序键
TTL time + INTERVAL 7 DAY  -- 设置7天自动过期
SETTINGS index_granularity = 8192;


CREATE TABLE IF NOT EXISTS aurora.process_event
(
    `place_id` UInt32,
    `ip` String,
    `c_flag` String,
    `time` DateTime64(3, 'Asia/Shanghai'),
    `event_type` UInt32,
    `xid` UInt64,
    `info` Map(String, String),
    `date` Date MATERIALIZED toDate(time) -- 物化日期列
)
    ENGINE = MergeTree() PARTITION BY date  -- 按天分区(直接使用物化日期列)
ORDER BY (place_id,ip,xid, time, event_type);



-- 分布式表
create table aurora.device_metric_cluster as aurora.device_metric
    ENGINE = Distributed(default, aurora, device_metric, xxHash64(place_id, ip));

create table aurora.process_metric_cluster as aurora.process_metric
    ENGINE = Distributed(default, aurora, process_metric, xxHash64(place_id, ip, xid));

create table aurora.process_event_cluster as aurora.process_event
    ENGINE = Distributed(default, aurora, process_event, xxHash64(place_id, ip, xid));