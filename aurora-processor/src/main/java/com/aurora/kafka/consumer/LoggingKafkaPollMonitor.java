package com.aurora.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认的日志型 Kafka Poll 监控监听器。
 * 将 poll 间隔和耗时信息打印为日志，便于观察消息堆积情况。
 *
 * @author AlbertYang
 * @date 2025/7/8 13:24
 */
public class LoggingKafkaPollMonitor implements KafkaPollMonitor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingKafkaPollMonitor.class);

    @Override
    public void onPollInterval(long intervalMs) {
        if (intervalMs > 1000 && intervalMs < 30000) {
            logger.info("The interval between two kafka polls is {}ms", intervalMs);
        } else if (intervalMs >= 30000) {
            logger.warn("The interval between two kafka polls is {}ms, which is greater than 30 seconds.", intervalMs);
        }
    }

    @Override
    public void onPollDuration(long durationMs) {
        if (durationMs >= 1000) {
            logger.info("Kafka poll costs time is {}ms", durationMs);
        }
    }


}
