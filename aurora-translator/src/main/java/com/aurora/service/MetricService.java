package com.aurora.service;

import com.aurora.grpc.*;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * 打印消息内容
 *
 * @author yangjunwei
 * @date 2025/7/4 15:41
 */
@GrpcService
@Slf4j
public class MetricService extends MetricServiceGrpc.MetricServiceImplBase {

    @Override
    public StreamObserver<RootMessage> report(StreamObserver<MetricAck> responseObserver) {
        return new StreamObserver<>() {
            private int messageCount = 0;

            @Override
            public void onNext(RootMessage rootMessage) {
                messageCount++;
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                String format = String.format("Processed %d messages", messageCount);
                MetricAck metricAck = MetricAck.newBuilder()
                        .setCode(200)
                        .setMessage(format)
                        .build();
                responseObserver.onNext(metricAck);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public void reportBatch(BatchMetricRequest request, StreamObserver<MetricAck> responseObserver) {
        log.info("Received batch of {} messages", request.getMetricsCount());
        // 4. 发送成功响应
        MetricAck response = MetricAck.newBuilder()
                .setCode(200)
                .setMessage("Processed " + request.getMetricsCount() + " metrics")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


}