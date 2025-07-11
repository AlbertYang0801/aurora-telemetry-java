package com.aurora;

import com.aurora.grpc.DeviceMetricAck;
import com.aurora.grpc.DeviceMetricItem;
import com.aurora.grpc.DeviceMetricMessage;
import com.aurora.grpc.DeviceMetricServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author AlbertYang
 * @date 2025/7/7 13:49
 */
public class DeviceMetricGrpcClient {
    private final ManagedChannel channel;

    //流式
    private final DeviceMetricServiceGrpc.DeviceMetricServiceStub asyncStub;

    // 初始化客户端，连接到gRPC服务器
    public DeviceMetricGrpcClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                // 这里可以配置通道参数
                .usePlaintext() // 仅用于测试，生产环境应该使用TLS
                .build());
    }

    // 使用已有的通道
    public DeviceMetricGrpcClient(ManagedChannel channel) {
        this.channel = channel;
        asyncStub = DeviceMetricServiceGrpc.newStub(channel);
    }

    // 关闭连接
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    // 调用服务方法
    public void sendClientStream(List<DeviceMetricMessage> metricMessages) throws InterruptedException {

        // 用于等待异步调用完成的计数器
        final CountDownLatch finishLatch = new CountDownLatch(1);

        // 创建响应观察者
        StreamObserver<DeviceMetricAck> responseObserver = new StreamObserver<DeviceMetricAck>() {
            @Override
            public void onNext(DeviceMetricAck reply) {
                System.out.println("收到服务端响应: " + reply.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("调用失败: " + t.getMessage());
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                System.out.println("调用完成");
                finishLatch.countDown();
            }
        };

        // 创建请求观察者
        StreamObserver<DeviceMetricMessage> requestObserver = asyncStub.report(responseObserver);

        try {
            // 发送多个请求
            for (DeviceMetricMessage metricMessage : metricMessages) {
                System.out.println("发送请求: \n" + metricMessage.toString());
                requestObserver.onNext(metricMessage);

                // 模拟延迟
                Thread.sleep(500);
            }
        } catch (RuntimeException | InterruptedException e) {
            // 取消RPC
            requestObserver.onError(e);
            throw e;
        }

        // 标记请求完成
        requestObserver.onCompleted();

        // 等待响应完成
        finishLatch.await(1, TimeUnit.MINUTES);
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建客户端，连接到服务器
        DeviceMetricGrpcClient client = new DeviceMetricGrpcClient("localhost", 19090);
        try {
            List<DeviceMetricMessage> rootMessages = new ArrayList<>();

            rootMessages.add(DeviceMetricMessage.newBuilder()
                    .setIp("192.168.1.1")
                    .setPlaceId(1)
                    .setTime(System.currentTimeMillis())
                    .addMetrics(DeviceMetricItem.newBuilder()
                            .setTid(1)
                            .setValue(0.5)
                            .build())
                    .addMetrics(DeviceMetricItem.newBuilder()
                            .setTid(2)
                            .setValue(1)
                            .build())
                    .build());
            client.sendClientStream(rootMessages);
        } finally {
            // 关闭连接
            client.shutdown();
        }
    }


}