package com.aurora.kafka;

/**
 * @author AlbertYang
 * @date 2025/7/8 13:20
 */
public interface KafkaPollMonitor {

    /**
     * 两次 poll 的间隔时间
     *
     * @param intervalMs
     */
    void onPollInterval(long intervalMs);

    /**
     * 本次poll的耗时
     *
     * @param durationMs
     */
    void onPollDuration(long durationMs);


}
