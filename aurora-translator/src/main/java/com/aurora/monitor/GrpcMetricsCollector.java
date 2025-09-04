package com.aurora.monitor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * gRPC 服务性能监控组件
 * 用于收集和统计服务性能指标
 * 
 * @author AlbertYang
 * @date 2025/9/3 21:35
 */
@Component
@Slf4j
public class GrpcMetricsCollector {
    
    // 指标服务统计
    private final LongAdder metricRequestCount = new LongAdder();
    private final LongAdder metricSuccessCount = new LongAdder();
    private final LongAdder metricFailCount = new LongAdder();
    private final LongAdder metricProcessedItems = new LongAdder();
    
    // 事件服务统计
    private final LongAdder eventRequestCount = new LongAdder();
    private final LongAdder eventSuccessCount = new LongAdder();
    private final LongAdder eventFailCount = new LongAdder();
    private final LongAdder eventProcessedItems = new LongAdder();
    
    // 响应时间统计
    private final AtomicLong totalResponseTime = new AtomicLong(0);
    private final AtomicLong requestStartTime = new AtomicLong(0);
    
    /**
     * 记录指标请求开始
     */
    public void recordMetricRequestStart() {
        metricRequestCount.increment();
        requestStartTime.set(System.currentTimeMillis());
    }
    
    /**
     * 记录指标请求成功
     */
    public void recordMetricSuccess(int processedCount) {
        metricSuccessCount.increment();
        metricProcessedItems.add(processedCount);
        recordResponseTime();
    }
    
    /**
     * 记录指标请求失败
     */
    public void recordMetricFail() {
        metricFailCount.increment();
        recordResponseTime();
    }
    
    /**
     * 记录事件请求开始
     */
    public void recordEventRequestStart() {
        eventRequestCount.increment();
        requestStartTime.set(System.currentTimeMillis());
    }
    
    /**
     * 记录事件请求成功
     */
    public void recordEventSuccess(int processedCount) {
        eventSuccessCount.increment();
        eventProcessedItems.add(processedCount);
        recordResponseTime();
    }
    
    /**
     * 记录事件请求失败
     */
    public void recordEventFail() {
        eventFailCount.increment();
        recordResponseTime();
    }
    
    /**
     * 记录响应时间
     */
    private void recordResponseTime() {
        long startTime = requestStartTime.get();
        if (startTime > 0) {
            long duration = System.currentTimeMillis() - startTime;
            totalResponseTime.addAndGet(duration);
        }
    }
    
    /**
     * 获取指标服务统计信息
     */
    public MetricServiceStats getMetricServiceStats() {
        long totalRequests = metricRequestCount.sum();
        long avgResponseTime = totalRequests > 0 ? totalResponseTime.get() / totalRequests : 0;
        
        return new MetricServiceStats(
                totalRequests,
                metricSuccessCount.sum(),
                metricFailCount.sum(),
                metricProcessedItems.sum(),
                avgResponseTime
        );
    }
    
    /**
     * 获取事件服务统计信息
     */
    public EventServiceStats getEventServiceStats() {
        long totalRequests = eventRequestCount.sum();
        long avgResponseTime = totalRequests > 0 ? totalResponseTime.get() / totalRequests : 0;
        
        return new EventServiceStats(
                totalRequests,
                eventSuccessCount.sum(),
                eventFailCount.sum(),
                eventProcessedItems.sum(),
                avgResponseTime
        );
    }
    
    /**
     * 打印统计信息
     */
    public void printStats() {
        MetricServiceStats metricStats = getMetricServiceStats();
        EventServiceStats eventStats = getEventServiceStats();
        
        log.info("=== gRPC 服务性能统计 ===");
        log.info("指标服务 - 总请求: {}, 成功: {}, 失败: {}, 处理项目: {}, 平均响应时间: {}ms",
                metricStats.totalRequests, metricStats.successCount, metricStats.failCount,
                metricStats.processedItems, metricStats.avgResponseTime);
        log.info("事件服务 - 总请求: {}, 成功: {}, 失败: {}, 处理项目: {}, 平均响应时间: {}ms",
                eventStats.totalRequests, eventStats.successCount, eventStats.failCount,
                eventStats.processedItems, eventStats.avgResponseTime);
    }
    
    /**
     * 重置统计信息
     */
    public void reset() {
        metricRequestCount.reset();
        metricSuccessCount.reset();
        metricFailCount.reset();
        metricProcessedItems.reset();
        
        eventRequestCount.reset();
        eventSuccessCount.reset();
        eventFailCount.reset();
        eventProcessedItems.reset();
        
        totalResponseTime.set(0);
    }
    
    /**
     * 指标服务统计数据
     */
    public static class MetricServiceStats {
        public final long totalRequests;
        public final long successCount;
        public final long failCount;
        public final long processedItems;
        public final long avgResponseTime;
        
        public MetricServiceStats(long totalRequests, long successCount, long failCount, 
                                 long processedItems, long avgResponseTime) {
            this.totalRequests = totalRequests;
            this.successCount = successCount;
            this.failCount = failCount;
            this.processedItems = processedItems;
            this.avgResponseTime = avgResponseTime;
        }
    }
    
    /**
     * 事件服务统计数据
     */
    public static class EventServiceStats {
        public final long totalRequests;
        public final long successCount;
        public final long failCount;
        public final long processedItems;
        public final long avgResponseTime;
        
        public EventServiceStats(long totalRequests, long successCount, long failCount, 
                                long processedItems, long avgResponseTime) {
            this.totalRequests = totalRequests;
            this.successCount = successCount;
            this.failCount = failCount;
            this.processedItems = processedItems;
            this.avgResponseTime = avgResponseTime;
        }
    }
}