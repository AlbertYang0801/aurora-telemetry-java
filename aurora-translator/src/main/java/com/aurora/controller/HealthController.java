package com.aurora.controller;

import com.aurora.monitor.GrpcMetricsCollector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 健康检查和监控端点
 * 提供服务状态、性能指标等信息
 *
 * @author yangjunwei
 * @date 2025/9/3 21:45
 */
@RestController
@RequestMapping("/api/health")
@Slf4j
public class HealthController {

    @Autowired
    private GrpcMetricsCollector metricsCollector;

    /**
     * 基础健康检查
     */
    @GetMapping
    public Map<String, Object> health() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("timestamp", System.currentTimeMillis());
        health.put("service", "aurora-translator");
        health.put("version", "1.0.0");
        return health;
    }

    /**
     * 性能指标
     */
    @GetMapping("/metrics")
    public Map<String, Object> metrics() {
        Map<String, Object> metrics = new HashMap<>();

        // 获取指标服务统计
        GrpcMetricsCollector.MetricServiceStats metricStats = metricsCollector.getMetricServiceStats();
        Map<String, Object> metricService = new HashMap<>();
        metricService.put("totalRequests", metricStats.totalRequests);
        metricService.put("successCount", metricStats.successCount);
        metricService.put("failCount", metricStats.failCount);
        metricService.put("processedItems", metricStats.processedItems);
        metricService.put("avgResponseTime", metricStats.avgResponseTime);
        metricService.put("successRate", metricStats.totalRequests > 0 ? (double) metricStats.successCount / metricStats.totalRequests * 100 : 0);

        // 获取事件服务统计
        GrpcMetricsCollector.EventServiceStats eventStats = metricsCollector.getEventServiceStats();
        Map<String, Object> eventService = new HashMap<>();
        eventService.put("totalRequests", eventStats.totalRequests);
        eventService.put("successCount", eventStats.successCount);
        eventService.put("failCount", eventStats.failCount);
        eventService.put("processedItems", eventStats.processedItems);
        eventService.put("avgResponseTime", eventStats.avgResponseTime);
        eventService.put("successRate", eventStats.totalRequests > 0 ? (double) eventStats.successCount / eventStats.totalRequests * 100 : 0);
        metrics.put("metricService", metricService);
        metrics.put("eventService", eventService);
        metrics.put("timestamp", System.currentTimeMillis());
        return metrics;
    }

    /**
     * 重置性能指标
     */
    @GetMapping("/metrics/reset")
    public Map<String, Object> resetMetrics() {
        metricsCollector.reset();
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Metrics reset successfully");
        result.put("timestamp", System.currentTimeMillis());
        log.info("Performance metrics reset by health check endpoint");
        return result;
    }





}