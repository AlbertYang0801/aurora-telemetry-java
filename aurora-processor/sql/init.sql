CREATE TABLE IF NOT EXISTS aurora.device_metric
(
    `place_id` UInt32,
    `ip` String,
    `time` DateTime('Asia/Shanghai'),
    `tid` UInt32,
    `value` Float64,
    `date` Date MATERIALIZED toDate(time) -- 物化日期列
)
    ENGINE = MergeTree() -- 使用MergeTree引擎
    PARTITION BY date  -- 按天分区(直接使用物化日期列)
ORDER BY (placeId,ip, tid, time)  -- 保持原有排序键
TTL time + INTERVAL 7 DAY  -- 设置7天自动过期
SETTINGS index_granularity = 8192;



CREATE TABLE IF NOT EXISTS aurora.process_metric
(
    `place_id` UInt32,
    `ip` String,
    `pid` UInt32,
    `time` DateTime('Asia/Shanghai')
    `tid` UInt32,
    `value` Float64,
    `date` Date MATERIALIZED toDate(time) -- 物化日期列
)
    ENGINE = MergeTree() -- 使用MergeTree引擎
    PARTITION BY date  -- 按天分区(直接使用物化日期列)
ORDER BY (placeId,ip,pid, tid, time)  -- 保持原有排序键
TTL time + INTERVAL 7 DAY  -- 设置7天自动过期
SETTINGS index_granularity = 8192;