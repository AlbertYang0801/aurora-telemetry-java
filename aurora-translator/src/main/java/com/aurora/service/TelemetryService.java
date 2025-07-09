package com.aurora.service;

import com.aurora.grpc.Ack;
import com.aurora.grpc.BatchTelemetryRequest;
import com.aurora.grpc.TelemetryRequest;
import com.aurora.grpc.TelemetryServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * 测试指标接收
 * @author AlbertYang
 * @date 2025/7/4 15:41
 */
@GrpcService
@Slf4j
public class TelemetryService extends TelemetryServiceGrpc.TelemetryServiceImplBase {

    @Override
    public void reportBatch(BatchTelemetryRequest request, StreamObserver<Ack> responseObserver) {
        log.info("Received batch of {} messages", request.getMetricsCount());
        // 4. 发送成功响应
        Ack response = Ack.newBuilder()
                .setCode(200)
                .setMessage("Processed " + request.getMetricsCount() + " metrics")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<TelemetryRequest> report(StreamObserver<Ack> responseObserver) {
        return new StreamObserver<>() {
            private int messageCount = 0;
            private long startTime = System.currentTimeMillis();

            @Override
            public void onNext(TelemetryRequest request) {
                messageCount++;

                // 打印消息内容（每100条打印一次完整内容）
                if (messageCount % 100 == 0) {
                    printFullMessage(request);
                } else if (messageCount % 10 == 0) {
                    printSummary(request);
                }

                // 每1000条响应一次进度
                if (messageCount % 1000 == 0) {
                    sendProgressResponse(responseObserver);
                }
            }

            @Override
            public void onError(Throwable t) {
                log.error("gRPC stream error after {} messages: {}", messageCount, t.getMessage());
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                long duration = System.currentTimeMillis() - startTime;
                double rate = messageCount / (duration / 1000.0);

                log.info("Stream completed. Total messages: {}, Duration: {} ms, Rate: {}/sec",
                        messageCount, duration, String.format("%.2f", rate));

                responseObserver.onNext(Ack.newBuilder()
                        .setCode(200)
                        .setMessage(String.format("Processed %d messages at %.2f msg/sec",
                                messageCount, rate))
                        .build());

                responseObserver.onCompleted();
            }

            private void printFullMessage(TelemetryRequest request) {
                StringBuilder sb = new StringBuilder("\n===== Full Message #")
                        .append(messageCount).append(" =====\n");

                sb.append("Metric: ").append(request.getMetric()).append("\n");
                sb.append("Value: ").append(request.getValue()).append("\n");
                sb.append("Timestamp: ").append(request.getTimestamp()).append("\n");

                if (!request.getTagsMap().isEmpty()) {
                    sb.append("Tags:\n");
                    request.getTagsMap().forEach((k, v) ->
                            sb.append("  ").append(k).append(": ").append(v).append("\n"));
                }

                log.info(sb.toString());
            }

            private void printSummary(TelemetryRequest request) {
                log.debug("Message #{}: {}={} [{} tags]",
                        messageCount,
                        request.getMetric(),
                        request.getValue(),
                        request.getTagsMap().size());
            }

            private void sendProgressResponse(StreamObserver<Ack> responseObserver) {
                responseObserver.onNext(Ack.newBuilder()
                        .setCode(200)
                        .setMessage("Received " + messageCount + " messages")
                        .build());
            }
        };
    }


}