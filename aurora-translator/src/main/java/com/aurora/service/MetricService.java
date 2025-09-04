package com.aurora.service;

import cn.hutool.core.util.StrUtil;
import com.aurora.enums.GrpcCodeEnum;
import com.aurora.grpc.*;
import com.aurora.kafka.KafkaHelper;
import com.aurora.monitor.GrpcMetricsCollector;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Metric Service gRPC服务实现
 * 提供指标数据上报功能，支持单条、流式和批量上报模式
 * 
 * @author AlbertYang
 * @date 2025/9/3 20:50
 */
@GrpcService
@Slf4j
public class MetricService extends MetricServiceGrpc.MetricServiceImplBase {

    @Autowired
    private KafkaHelper kafkaHelper;
    
    @Autowired
    private GrpcMetricsCollector metricsCollector;

    /**
     * 单条指标上报
     * 适用于少量指标数据的实时上报
     *
     * @param request 指标数据消息
     * @param responseObserver 响应观察者
     */
    @Override
    public void report(MetricDataMessage request, StreamObserver<MetricAck> responseObserver) {
        metricsCollector.recordMetricRequestStart();
        try {
            // 输入验证
            if (request == null || StrUtil.isEmpty(request.getSourceId())) {
                log.warn("Invalid metric data: sourceId is required");
                MetricAck errorAck = MetricAck.newBuilder()
                        .setCode(GrpcCodeEnum.INVALID_PARAM.getCode())
                        .setMessage("Invalid request: sourceId is required")
                        .setProcessedCount(0)
                        .setFailedCount(1)
                        .setProcessedAt(System.currentTimeMillis())
                        .build();
                responseObserver.onNext(errorAck);
                responseObserver.onCompleted();
                return;
            }
            
            // 验证指标数据列表
            if (request.getMetricsList().isEmpty()) {
                log.warn("Invalid metric data: metrics list is empty for sourceId: {}", request.getSourceId());
                MetricAck errorAck = MetricAck.newBuilder()
                        .setCode(GrpcCodeEnum.INVALID_PARAM.getCode())
                        .setMessage("Invalid request: metrics list cannot be empty")
                        .setProcessedCount(0)
                        .setFailedCount(1)
                        .setProcessedAt(System.currentTimeMillis())
                        .build();
                responseObserver.onNext(errorAck);
                responseObserver.onCompleted();
                return;
            }
            
            log.debug("Processing single metric from sourceId: {}, metrics count: {}", 
                    request.getSourceId(), request.getMetricsCount());
            
            kafkaHelper.sendMetric(request);
            
            MetricAck metricAck = MetricAck.newBuilder()
                    .setCode(GrpcCodeEnum.SUCCESS.getCode())
                    .setMessage(GrpcCodeEnum.SUCCESS.getMessage())
                    .setProcessedCount(1)
                    .setFailedCount(0)
                    .setProcessedAt(System.currentTimeMillis())
                    .build();
            responseObserver.onNext(metricAck);
            responseObserver.onCompleted();
            
            metricsCollector.recordMetricSuccess(1);
            log.debug("Successfully processed single metric from sourceId: {}", request.getSourceId());
            
        } catch (Exception e) {
            log.error("Failed to process single metric from sourceId: {}, metrics count: {}", request.getSourceId(), request.getMetricsCount(), e);
            
            MetricAck errorAck = MetricAck.newBuilder()
                    .setCode(GrpcCodeEnum.FAIL.getCode())
                    .setMessage("Internal server error: " + e.getMessage())
                    .setProcessedCount(0)
                    .setFailedCount(1)
                    .setProcessedAt(System.currentTimeMillis())
                    .build();
            responseObserver.onNext(errorAck);
            responseObserver.onCompleted();
            metricsCollector.recordMetricFail();
        }
    }

    /**
     * 流式指标上报
     * 适用于高频次、连续的指标数据上报
     * 客户端可以连续发送多个指标，服务端在流结束时返回统一的统计信息
     *
     * @param responseObserver 响应观察者
     * @return 请求流观察者
     */
    @Override
    public StreamObserver<MetricDataMessage> reportStream(StreamObserver<MetricAck> responseObserver) {
        return new StreamObserver<>() {
            private int messageCount = 0;
            private int failMessageCount = 0;
            private int totalMetricItems = 0;
            private final long startTime = System.currentTimeMillis();
            private String lastSourceId = "";

            @Override
            public void onNext(MetricDataMessage metricDataMessage) {
                try {
                    // 输入验证
                    if (metricDataMessage.getSourceId().isEmpty()) {
                        log.warn("Invalid metric in stream: sourceId is required, metrics count: {}", 
                                metricDataMessage.getMetricsCount());
                        failMessageCount++;
                        return;
                    }
                    
                    if (metricDataMessage.getMetricsList().isEmpty()) {
                        log.warn("Invalid metric in stream: metrics list is empty for sourceId: {}", 
                                metricDataMessage.getSourceId());
                        failMessageCount++;
                        return;
                    }
                    
                    kafkaHelper.sendMetric(metricDataMessage);
                    messageCount++;
                    totalMetricItems += metricDataMessage.getMetricsCount();
                    lastSourceId = metricDataMessage.getSourceId();
                    
                    // 每处理100条数据记录一次日志
                    if (messageCount % 100 == 0) {
                        log.info("Stream processing progress: {} messages processed, {} total metrics from sourceId: {}", 
                                messageCount, totalMetricItems, lastSourceId);
                    }
                    
                } catch (Exception e) {
                    log.error("Failed to send metric in stream, metrics count: {}, sourceId: {}", 
                            metricDataMessage.getMetricsCount(), metricDataMessage.getSourceId(), e);
                    failMessageCount++;
                }
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("Stream error occurred after processing {} messages, {} total metrics, last sourceId: {}", 
                        messageCount, totalMetricItems, lastSourceId, throwable);
                
                // 发送错误响应
                MetricAck errorAck = MetricAck.newBuilder()
                        .setCode(GrpcCodeEnum.FAIL.getCode())
                        .setMessage("Stream error: " + throwable.getMessage())
                        .setProcessedCount(messageCount)
                        .setFailedCount(failMessageCount)
                        .setProcessedAt(System.currentTimeMillis())
                        .build();
                responseObserver.onNext(errorAck);
                responseObserver.onCompleted();
            }

            @Override
            public void onCompleted() {
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                double throughput = messageCount > 0 ? (double) messageCount / (duration / 1000.0) : 0;
                double metricThroughput = totalMetricItems > 0 ? (double) totalMetricItems / (duration / 1000.0) : 0;
                
                MetricAck metricAck;
                if (failMessageCount > 0) {
                    metricAck = MetricAck.newBuilder()
                            .setCode(GrpcCodeEnum.PARTIAL_SUCCESS.getCode()) // 部分成功
                            .setMessage("Stream completed with some failures")
                            .setProcessedCount(messageCount)
                            .setFailedCount(failMessageCount)
                            .setProcessedAt(System.currentTimeMillis())
                            .build();
                    log.warn("Stream processing completed with failures. Messages: {}, Metrics: {}, Failed: {}, Duration: {}ms, Throughput: {} msg/sec, {} metrics/sec",
                            messageCount, totalMetricItems, failMessageCount, duration, throughput, metricThroughput);
                } else {
                    metricAck = MetricAck.newBuilder()
                            .setCode(GrpcCodeEnum.SUCCESS.getCode())
                            .setMessage(GrpcCodeEnum.SUCCESS.getMessage())
                            .setProcessedCount(messageCount)
                            .setFailedCount(0)
                            .setProcessedAt(System.currentTimeMillis())
                            .build();
                    log.info("Stream processing completed successfully. Messages: {}, Metrics: {}, Duration: {}ms, Throughput: {} msg/sec, {} metrics/sec",
                            messageCount, totalMetricItems, duration, throughput, metricThroughput);
                }
                
                responseObserver.onNext(metricAck);
                responseObserver.onCompleted();
            }
        };
    }

    /**
     * 批量指标上报
     * 适用于生产环境的批量数据上报，提供最佳性能
     * 支持部分失败的容错处理
     *
     * @param request 批量指标请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void reportBatch(BatchMetricDataRequest request, StreamObserver<MetricAck> responseObserver) {
        int processedCount = 0;
        int failedCount = 0;
        int totalMetricItems = 0;
        
        try {
            // 输入验证
            if (request == null || request.getMetricsList().isEmpty()) {
                log.warn("Invalid batch request: metrics list is empty");
                MetricAck errorAck = MetricAck.newBuilder()
                        .setCode(GrpcCodeEnum.INVALID_PARAM.getCode())
                        .setMessage("Invalid request: metrics list cannot be empty")
                        .setProcessedCount(0)
                        .setFailedCount(0)
                        .setProcessedAt(System.currentTimeMillis())
                        .build();
                responseObserver.onNext(errorAck);
                responseObserver.onCompleted();
                return;
            }
            
            // 记录批次信息
            log.info("Processing batch metrics, metrics count: {}", request.getMetricsCount());
            
            // 批量处理指标
            for (MetricDataMessage metric : request.getMetricsList()) {
                try {
                    // 验证单个指标数据
                    if (StrUtil.isEmpty(metric.getSourceId())) {
                        log.warn("Skipping metric with empty sourceId, traceId: {}", metric.getTraceId());
                        failedCount++;
                        continue;
                    }
                    
                    if (metric.getMetricsList().isEmpty()) {
                        log.warn("Skipping metric with empty metrics list, sourceId: {} , traceId: {}", metric.getSourceId(), metric.getTraceId());
                        failedCount++;
                        continue;
                    }
                    
                    kafkaHelper.sendMetric(metric);
                    processedCount++;
                    totalMetricItems += metric.getMetricsCount();
                } catch (Exception e) {
                    log.error("Failed to send metric to kafka, sourceId: {}, metrics count: {}, traceId: {}", metric.getSourceId(), metric.getMetricsCount(), metric.getTraceId(), e);
                    failedCount++;
                }
            }
            
            // 构建响应
            MetricAck metricAck;
            if (failedCount > 0) {
                metricAck = MetricAck.newBuilder()
                        .setCode(GrpcCodeEnum.PARTIAL_SUCCESS.getCode())
                        .setMessage("Batch processed with some failures")
                        .setProcessedCount(processedCount)
                        .setFailedCount(failedCount)
                        .setProcessedAt(System.currentTimeMillis())
                        .build();
                log.warn("Batch processing completed with failures, processed: {}, failed: {}, total metrics: {}", processedCount, failedCount, totalMetricItems);
            } else {
                metricAck = MetricAck.newBuilder()
                        .setCode(GrpcCodeEnum.SUCCESS.getCode())
                        .setMessage(GrpcCodeEnum.SUCCESS.getMessage())
                        .setProcessedCount(processedCount)
                        .setFailedCount(0)
                        .setProcessedAt(System.currentTimeMillis())
                        .build();
                log.info("Batch processing completed successfully, processed: {}, total metrics: {}", processedCount, totalMetricItems);
            }
            
            responseObserver.onNext(metricAck);
            responseObserver.onCompleted();
            
        } catch (Exception e) {
            log.error("Failed to process batch metrics ",  e);
            
            MetricAck errorAck = MetricAck.newBuilder()
                    .setCode(GrpcCodeEnum.FAIL.getCode())
                    .setMessage("Internal server error: " + e.getMessage())
                    .setProcessedCount(processedCount)
                    .setFailedCount(request.getMetricsCount() - processedCount)
                    .setProcessedAt(System.currentTimeMillis())
                    .build();
            
            responseObserver.onNext(errorAck);
            responseObserver.onCompleted();
        }
    }


}
