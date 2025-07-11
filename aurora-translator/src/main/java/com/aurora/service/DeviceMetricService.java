package com.aurora.service;

import com.aurora.grpc.DeviceMetricAck;
import com.aurora.grpc.DeviceMetricMessage;
import com.aurora.grpc.DeviceMetricServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * 接收Metric指标数据
 *
 * @author AlertYang
 * @date 2025/7/4 15:41
 */
@GrpcService
@Slf4j
public class DeviceMetricService extends DeviceMetricServiceGrpc.DeviceMetricServiceImplBase {

    @Override
    public StreamObserver<DeviceMetricMessage> report(StreamObserver<DeviceMetricAck> responseObserver) {
        return new StreamObserver<>() {
            private int messageCount = 0;

            @Override
            public void onNext(DeviceMetricMessage metricMessage) {
                System.out.println("receiver msg:\n" + metricMessage.toString());
                messageCount++;
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                String format = String.format("Processed %d messages", messageCount);
                DeviceMetricAck metricAck = DeviceMetricAck.newBuilder()
                        .setCode(200)
                        .setMessage(format)
                        .build();
                responseObserver.onNext(metricAck);
                responseObserver.onCompleted();
            }
        };
    }


}