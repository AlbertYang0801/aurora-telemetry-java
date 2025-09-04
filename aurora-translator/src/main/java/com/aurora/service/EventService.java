package com.aurora.service;

import cn.hutool.core.util.StrUtil;
import com.aurora.enums.GrpcCodeEnum;
import com.aurora.kafka.KafkaHelper;
import com.aurora.monitor.GrpcMetricsCollector;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Event Service gRPC服务实现
 * 提供事件数据上报功能，支持单条、流式和批量上报模式
 *
 * @author yangjunwei
 * @date 2025/9/3 20:48
 */
@GrpcService
@Slf4j
public class EventService extends EventServiceGrpc.EventServiceImplBase {

    @Autowired
    private KafkaHelper kafkaHelper;
    
    @Autowired
    private GrpcMetricsCollector metricsCollector;

    /**
     * 单条事件上报
     * 适用于少量事件数据的实时上报
     *
     * @param request          事件数据消息
     * @param responseObserver 响应观察者
     */
    @Override
    public void report(EventDataMessage request, StreamObserver<EventAck> responseObserver) {
        try {
            // 输入验证
            if (request == null || StrUtil.isEmpty(request.getSourceId())) {
                log.warn("Invalid event data: sourceId is required");
                EventAck errorAck = EventAck.newBuilder()
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

            log.debug("Processing single event from sourceId: {}, eventType: {}", request.getSourceId(), request.getEventType());

            kafkaHelper.sendEvent(request);

            EventAck eventAck = EventAck.newBuilder()
                    .setCode(GrpcCodeEnum.SUCCESS.getCode())
                    .setMessage(GrpcCodeEnum.SUCCESS.getMessage())
                    .setProcessedCount(1)
                    .setFailedCount(0)
                    .setProcessedAt(System.currentTimeMillis())
                    .build();
            responseObserver.onNext(eventAck);
            responseObserver.onCompleted();

            log.debug("Successfully processed single event from sourceId: {}", request.getSourceId());

        } catch (Exception e) {
            log.error("Failed to process single event from sourceId: {}, eventType: {}", request.getSourceId(), request.getEventType(), e);

            EventAck errorAck = EventAck.newBuilder()
                    .setCode(GrpcCodeEnum.FAIL.getCode())
                    .setMessage("Internal server error: " + e.getMessage())
                    .setProcessedCount(0)
                    .setFailedCount(1)
                    .setProcessedAt(System.currentTimeMillis())
                    .build();
            responseObserver.onNext(errorAck);
            responseObserver.onCompleted();
        }
    }

    /**
     * 流式事件上报
     * 适用于高频次、连续的事件数据上报
     * 客户端可以连续发送多个事件，服务端在流结束时返回统一的统计信息
     *
     * @param responseObserver 响应观察者
     * @return 请求流观察者
     */
    @Override
    public StreamObserver<EventDataMessage> reportStream(StreamObserver<EventAck> responseObserver) {
        return new StreamObserver<>() {
            private int messageCount = 0;
            private int failMessageCount = 0;
            private final long startTime = System.currentTimeMillis();
            private String lastSourceId = "";

            @Override
            public void onNext(EventDataMessage eventDataMessage) {
                try {
                    // 输入验证
                    if (StrUtil.isEmpty(eventDataMessage.getSourceId())) {
                        log.warn("Invalid event in stream: sourceId is required, eventType: {}", eventDataMessage.getEventType());
                        failMessageCount++;
                        return;
                    }

                    kafkaHelper.sendEvent(eventDataMessage);
                    messageCount++;
                    lastSourceId = eventDataMessage.getSourceId();

                    // 每处理100条数据记录一次日志
                    if (messageCount % 100 == 0) {
                        log.info("Stream processing progress: {} events processed from sourceId: {}",
                                messageCount, lastSourceId);
                    }

                } catch (Exception e) {
                    log.error("Failed to send event in stream, eventType: {}, sourceId: {}", eventDataMessage.getEventType(), eventDataMessage.getSourceId(), e);
                    failMessageCount++;
                }
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("Stream error occurred after processing {} events, last sourceId: {}", messageCount, lastSourceId, throwable);

                // 发送错误响应
                EventAck errorAck = EventAck.newBuilder()
                        .setCode(GrpcCodeEnum.SUCCESS.getCode())
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

                EventAck eventAck;
                if (failMessageCount > 0) {
                    eventAck = EventAck.newBuilder()
                            .setCode(GrpcCodeEnum.SUCCESS.getCode())
                            .setMessage("Stream completed with some failures")
                            .setProcessedCount(messageCount)
                            .setFailedCount(failMessageCount)
                            .setProcessedAt(System.currentTimeMillis())
                            .build();
                    log.warn("Stream processing completed with failures. Processed: {}, Failed: {}, Duration: {}ms, Throughput: {} events/sec",
                            messageCount, failMessageCount, duration, throughput);
                } else {
                    eventAck = EventAck.newBuilder()
                            .setCode(GrpcCodeEnum.SUCCESS.getCode())
                            .setMessage(GrpcCodeEnum.SUCCESS.getMessage())
                            .setProcessedCount(messageCount)
                            .setFailedCount(0)
                            .setProcessedAt(System.currentTimeMillis())
                            .build();
                    log.info("Stream processing completed successfully. Processed: {}, Duration: {}ms, Throughput: {} events/sec",
                            messageCount, duration, throughput);
                }

                responseObserver.onNext(eventAck);
                responseObserver.onCompleted();
            }
        };
    }

    /**
     * 批量事件上报
     * 适用于生产环境的批量数据上报，提供最佳性能
     * 支持部分失败的容错处理
     *
     * @param request          批量事件请求
     * @param responseObserver 响应观察者
     */
    @Override
    public void reportBatch(BatchEventDataRequest request, StreamObserver<EventAck> responseObserver) {
        int processedCount = 0;
        int failedCount = 0;

        try {
            // 输入验证
            if (request == null || request.getEventsList().isEmpty()) {
                log.warn("Invalid batch request: events list is empty");
                EventAck errorAck = EventAck.newBuilder()
                        .setCode(GrpcCodeEnum.INVALID_PARAM.getCode())
                        .setMessage("Invalid request: events list cannot be empty")
                        .setProcessedCount(0)
                        .setFailedCount(0)
                        .setProcessedAt(System.currentTimeMillis())
                        .build();
                responseObserver.onNext(errorAck);
                responseObserver.onCompleted();
                return;
            }

            // 记录批次信息
            log.info("Processing batch events, batchId: {}, events count: {}", request.getBatchId(), request.getEventsCount());

            // 批量处理事件
            for (EventDataMessage event : request.getEventsList()) {
                try {
                    kafkaHelper.sendEvent(event);
                    processedCount++;
                } catch (Exception e) {
                    log.error("Failed to send event to kafka, eventType: {}, sourceId: {}", event.getEventType(), event.getSourceId(), e);
                    failedCount++;
                }
            }

            // 构建响应
            EventAck eventAck;
            if (failedCount > 0) {
                eventAck = EventAck.newBuilder()
                        .setCode(GrpcCodeEnum.PARTIAL_SUCCESS.getCode())
                        .setMessage("Batch processed with some failures")
                        .setProcessedCount(processedCount)
                        .setFailedCount(failedCount)
                        .setProcessedAt(System.currentTimeMillis())
                        .build();
                log.warn("Batch processing completed with failures, batchId: {}, processed: {}, failed: {}",
                        request.getBatchId(), processedCount, failedCount);
            } else {
                eventAck = EventAck.newBuilder()
                        .setCode(GrpcCodeEnum.SUCCESS.getCode())
                        .setMessage(GrpcCodeEnum.SUCCESS.getMessage())
                        .setProcessedCount(processedCount)
                        .setFailedCount(0)
                        .setProcessedAt(System.currentTimeMillis())
                        .build();
                log.info("Batch processing completed successfully, batchId: {}, processed: {}",
                        request.getBatchId(), processedCount);
            }

            responseObserver.onNext(eventAck);
            responseObserver.onCompleted();

        } catch (Exception e) {
            log.error("Failed to process batch events, batchId: {}", request.getBatchId(), e);

            EventAck errorAck = EventAck.newBuilder()
                    .setCode(GrpcCodeEnum.FAIL.getCode())
                    .setMessage("Internal server error: " + e.getMessage())
                    .setProcessedCount(processedCount)
                    .setFailedCount(request.getEventsCount() - processedCount)
                    .setProcessedAt(System.currentTimeMillis())
                    .build();

            responseObserver.onNext(errorAck);
            responseObserver.onCompleted();
        }
    }


}
