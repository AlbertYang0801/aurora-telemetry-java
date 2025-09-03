package com.aurora;

import com.aurora.grpc.*;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Event Service gRPC 测试客户端
 * 用于测试事件上报功能
 * 
 * @author yangjunwei
 * @date 2025/9/3 21:30
 */
public class EventGrpcClient {
    private final ManagedChannel channel;
    private final EventServiceGrpc.EventServiceStub asyncStub;
    private final EventServiceGrpc.EventServiceBlockingStub blockingStub;

    public EventGrpcClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext() // 仅用于测试，生产环境应该使用TLS
                .build());
    }

    public EventGrpcClient(ManagedChannel channel) {
        this.channel = channel;
        asyncStub = EventServiceGrpc.newStub(channel);
        blockingStub = EventServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * 单条事件上报测试
     */
    public void testSingleReport() {
        try {
            EventDataMessage request = createSampleEvent();
            EventAck response = blockingStub.report(request);
            System.out.println("单条事件上报响应: " + response.getMessage() + ", 码: " + response.getCode());
        } catch (Exception e) {
            System.err.println("单条事件上报失败: " + e.getMessage());
        }
    }

    /**
     * 批量事件上报测试
     */
    public void testBatchReport() {
        try {
            List<EventDataMessage> events = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                events.add(createSampleEvent());
            }
            
            BatchEventDataRequest batchRequest = BatchEventDataRequest.newBuilder()
                    .addAllEvents(events)
                    .setBatchId("test-event-batch-" + System.currentTimeMillis())
                    .build();
                    
            EventAck response = blockingStub.reportBatch(batchRequest);
            System.out.println("批量事件上报响应: " + response.getMessage() + ", 码: " + response.getCode() 
                    + ", 处理数量: " + response.getProcessedCount());
        } catch (Exception e) {
            System.err.println("批量事件上报失败: " + e.getMessage());
        }
    }

    /**
     * 流式事件上报测试
     */
    public void testStreamReport(List<EventDataMessage> events) throws InterruptedException {
        final CountDownLatch finishLatch = new CountDownLatch(1);

        StreamObserver<EventAck> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(EventAck reply) {
                System.out.println("流式事件上报响应: " + reply.getMessage() + ", 处理数量: " + reply.getProcessedCount());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("流式事件上报失败: " + t.getMessage());
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                System.out.println("流式事件上报完成");
                finishLatch.countDown();
            }
        };

        StreamObserver<EventDataMessage> requestObserver = asyncStub.reportStream(responseObserver);

        try {
            for (EventDataMessage event : events) {
                System.out.println("发送事件: " + event.getEventType());
                requestObserver.onNext(event);
                Thread.sleep(200);
            }
        } catch (RuntimeException | InterruptedException e) {
            requestObserver.onError(e);
            throw e;
        }

        requestObserver.onCompleted();
        finishLatch.await(1, TimeUnit.MINUTES);
    }

    /**
     * 创建样本事件数据
     */
    private EventDataMessage createSampleEvent() {
        return EventDataMessage.newBuilder()
                .setTimestamp(System.currentTimeMillis())
                .setEventType(1001) // 系统启动事件
                .setSourceId("test-device-001")
                .setTraceId(ByteString.copyFromUtf8("test-trace-001"))
                .putEventData("level", "INFO")
                .putEventData("message", "System started successfully")
                .putEventData("service", "test-service")
                .putEventData("version", "1.0.0")
                .build();
    }

    public static void main(String[] args) throws InterruptedException {
        EventGrpcClient client = new EventGrpcClient("localhost", 19090);
        try {
            System.out.println("=== 开始测试 gRPC Event 服务 ===");
            
            // 测试单条事件上报
            System.out.println("\n1. 测试单条事件上报:");
            client.testSingleReport();
            
            // 测试批量事件上报
            System.out.println("\n2. 测试批量事件上报:");
            client.testBatchReport();
            
            // 测试流式事件上报
            System.out.println("\n3. 测试流式事件上报:");
            List<EventDataMessage> streamEvents = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                streamEvents.add(client.createSampleEvent());
            }
            client.testStreamReport(streamEvents);
            
            System.out.println("\n=== 所有事件测试完成 ===");
            
        } finally {
            client.shutdown();
        }
    }

    
}