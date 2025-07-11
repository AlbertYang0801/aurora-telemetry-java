package com.aurora.service;

import com.aurora.grpc.ProcessMetricAck;
import com.aurora.grpc.ProcessMetricMessage;
import com.aurora.grpc.ProcessMetricServiceGrpc;
import com.aurora.kafka.KafkaHelper;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 接收Metric指标数据
 *
 * @author AlertYang
 * @date 2025/7/4 15:41
 */
@GrpcService
@Slf4j
public class ProcessMetricService extends ProcessMetricServiceGrpc.ProcessMetricServiceImplBase {

    @Autowired
    private KafkaHelper kafkaHelper;

    @Override
    public StreamObserver<ProcessMetricMessage> report(StreamObserver<ProcessMetricAck> responseObserver) {
        return new StreamObserver<>() {
            private int messageCount = 0;

            @Override
            public void onNext(ProcessMetricMessage processMetricMessage) {
                System.out.println("receiver msg:\n" + processMetricMessage.toString());
                //直接发送给kafka
                kafkaHelper.sendProcessMetric(processMetricMessage);
                messageCount++;
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                String format = String.format("Processed %d messages", messageCount);
                ProcessMetricAck processMetricAck = ProcessMetricAck.newBuilder()
                        .setCode(200)
                        .setMessage(format)
                        .build();
                responseObserver.onNext(processMetricAck);
                responseObserver.onCompleted();
            }
        };
    }


}