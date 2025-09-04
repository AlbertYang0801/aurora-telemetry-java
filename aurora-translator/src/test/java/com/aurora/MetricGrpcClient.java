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
 * Metric Service gRPC 测试客户端
 * 用于测试指标上报功能
 * 
 * @author AlbertYang
 * @date 2025/9/3 21:30
 */
public class MetricGrpcClient {
    private final ManagedChannel channel;
    private final MetricServiceGrpc.MetricServiceStub asyncStub;
    private final MetricServiceGrpc.MetricServiceBlockingStub blockingStub;

    public MetricGrpcClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext() // 仅用于测试，生产环境应该使用TLS
                .build());
    }

    public MetricGrpcClient(ManagedChannel channel) {
        this.channel = channel;
        asyncStub = MetricServiceGrpc.newStub(channel);
        blockingStub = MetricServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * 单条指标上报测试
     */
    public void testSingleReport() {
        try {
            MetricDataMessage request = createSampleMetric();
            MetricAck response = blockingStub.report(request);
            System.out.println("单条指标上报响应: " + response.getMessage() + ", 码: " + response.getCode());
        } catch (Exception e) {
            System.err.println("单条指标上报失败: " + e.getMessage());
        }
    }

    /**
     * 批量指标上报测试
     */
    public void testBatchReport() {
        try {
            List<MetricDataMessage> metrics = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                metrics.add(createSampleMetric());
            }
            
            BatchMetricDataRequest batchRequest = BatchMetricDataRequest.newBuilder()
                    .addAllMetrics(metrics)
                    .build();
                    
            MetricAck response = blockingStub.reportBatch(batchRequest);
            System.out.println("批量指标上报响应: " + response.getMessage() + ", 码: " + response.getCode() 
                    + ", 处理数量: " + response.getProcessedCount());
        } catch (Exception e) {
            System.err.println("批量指标上报失败: " + e.getMessage());
        }
    }

    /**
     * 流式指标上报测试
     */
    public void testStreamReport(List<MetricDataMessage> metrics) throws InterruptedException {
        final CountDownLatch finishLatch = new CountDownLatch(1);

        StreamObserver<MetricAck> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(MetricAck reply) {
                System.out.println("流式指标上报响应: " + reply.getMessage() + ", 处理数量: " + reply.getProcessedCount());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("流式指标上报失败: " + t.getMessage());
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                System.out.println("流式指标上报完成");
                finishLatch.countDown();
            }
        };

        StreamObserver<MetricDataMessage> requestObserver = asyncStub.reportStream(responseObserver);

        try {
            for (MetricDataMessage metric : metrics) {
                requestObserver.onNext(metric);
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
     * 创建样本指标数据
     */
    private MetricDataMessage createSampleMetric() {
        List<MetricDataItem> items = new ArrayList<>();
        items.add(MetricDataItem.newBuilder()
                .setMetricId(1)
                .setValue(85.5)
                .build());
        items.add(MetricDataItem.newBuilder()
                .setMetricId(2)
                .setValue(90.0)
                .build());


        return MetricDataMessage.newBuilder()
                .setTimestamp(System.currentTimeMillis())
                .setSourceId("cpu_usage")
                .setTraceId(ByteString.copyFromUtf8("test-trace-001"))
                .addAllMetrics(items)
                .build();
    }

    public static void main(String[] args) throws InterruptedException {
        MetricGrpcClient client = new MetricGrpcClient("localhost", 19090);
        try {
            System.out.println("=== 开始测试 gRPC Metric 服务 ===");
            
            // 测试单条指标上报
            System.out.println("\n1. 测试单条指标上报:");
            client.testSingleReport();
            
            // 测试批量指标上报
            // 测试批量指标上报
            System.out.println("\n2. 测试批量指标上报:");
            client.testBatchReport();
            
            // 测试流式指标上报
            System.out.println("\n3. 测试流式指标上报:");
            List<MetricDataMessage> streamMetrics = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                streamMetrics.add(client.createSampleMetric());
            }
            client.testStreamReport(streamMetrics);
            
            System.out.println("\n=== 所有指标测试完成 ===");
            
        } finally {
            client.shutdown();
        }
    }

    
}