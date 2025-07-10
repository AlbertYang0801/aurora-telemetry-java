CREATE TABLE IF NOT EXISTS metric_data
(
    `placeId` UInt32,
    `ip` String,
    `pid` UInt32,
    `time` DateTime64(3, 'Asia/Shanghai'),
    `tid` UInt32,
    `value` Float64,
    `date` Date MATERIALIZED toDate(time)  -- 物化日期列
)
    ENGINE = MergeTree()
    PARTITION BY date  -- 按天分区(直接使用物化日期列)
ORDER BY (placeId, tid, time, pid)  -- 保持原有排序键
TTL time + INTERVAL 7 DAY  -- 设置7天自动过期
SETTINGS index_granularity = 8192;