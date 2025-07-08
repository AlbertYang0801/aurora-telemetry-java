package com.aurora.service;

import cn.hutool.json.JSONUtil;
import com.aurora.grpc.MetricAck;
import com.aurora.grpc.MetricMessage;
import com.aurora.grpc.MetricServiceGrpc;
import com.aurora.kafka.KafkaHelper;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 接收Metric指标数据
 *
 * @author AlertYang
 * @date 2025/7/4 15:41
 */
@GrpcService
@Slf4j
public class MetricService extends MetricServiceGrpc.MetricServiceImplBase {

    @Autowired
    private KafkaHelper kafkaHelper;

    @Override
    public StreamObserver<MetricMessage> report(StreamObserver<MetricAck> responseObserver) {
        return new StreamObserver<>() {
            private int messageCount = 0;

            @Override
            public void onNext(MetricMessage metricMessage) {
                System.out.println("receiver msg:\n" + metricMessage.toString());
                //直接发送给kafka
                kafkaHelper.sendMetric(metricMessage);
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


}