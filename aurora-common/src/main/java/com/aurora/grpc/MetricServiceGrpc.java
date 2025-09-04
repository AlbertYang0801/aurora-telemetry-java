package com.aurora.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * 通用指标数据采集服务
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: metric.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MetricServiceGrpc {

  private MetricServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "aurora.MetricService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.aurora.grpc.MetricDataMessage,
      com.aurora.grpc.MetricAck> getReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Report",
      requestType = com.aurora.grpc.MetricDataMessage.class,
      responseType = com.aurora.grpc.MetricAck.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.aurora.grpc.MetricDataMessage,
      com.aurora.grpc.MetricAck> getReportMethod() {
    io.grpc.MethodDescriptor<com.aurora.grpc.MetricDataMessage, com.aurora.grpc.MetricAck> getReportMethod;
    if ((getReportMethod = MetricServiceGrpc.getReportMethod) == null) {
      synchronized (MetricServiceGrpc.class) {
        if ((getReportMethod = MetricServiceGrpc.getReportMethod) == null) {
          MetricServiceGrpc.getReportMethod = getReportMethod =
              io.grpc.MethodDescriptor.<com.aurora.grpc.MetricDataMessage, com.aurora.grpc.MetricAck>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Report"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aurora.grpc.MetricDataMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aurora.grpc.MetricAck.getDefaultInstance()))
              .setSchemaDescriptor(new MetricServiceMethodDescriptorSupplier("Report"))
              .build();
        }
      }
    }
    return getReportMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.aurora.grpc.MetricDataMessage,
      com.aurora.grpc.MetricAck> getReportStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReportStream",
      requestType = com.aurora.grpc.MetricDataMessage.class,
      responseType = com.aurora.grpc.MetricAck.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.aurora.grpc.MetricDataMessage,
      com.aurora.grpc.MetricAck> getReportStreamMethod() {
    io.grpc.MethodDescriptor<com.aurora.grpc.MetricDataMessage, com.aurora.grpc.MetricAck> getReportStreamMethod;
    if ((getReportStreamMethod = MetricServiceGrpc.getReportStreamMethod) == null) {
      synchronized (MetricServiceGrpc.class) {
        if ((getReportStreamMethod = MetricServiceGrpc.getReportStreamMethod) == null) {
          MetricServiceGrpc.getReportStreamMethod = getReportStreamMethod =
              io.grpc.MethodDescriptor.<com.aurora.grpc.MetricDataMessage, com.aurora.grpc.MetricAck>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReportStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aurora.grpc.MetricDataMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aurora.grpc.MetricAck.getDefaultInstance()))
              .setSchemaDescriptor(new MetricServiceMethodDescriptorSupplier("ReportStream"))
              .build();
        }
      }
    }
    return getReportStreamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.aurora.grpc.BatchMetricDataRequest,
      com.aurora.grpc.MetricAck> getReportBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReportBatch",
      requestType = com.aurora.grpc.BatchMetricDataRequest.class,
      responseType = com.aurora.grpc.MetricAck.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.aurora.grpc.BatchMetricDataRequest,
      com.aurora.grpc.MetricAck> getReportBatchMethod() {
    io.grpc.MethodDescriptor<com.aurora.grpc.BatchMetricDataRequest, com.aurora.grpc.MetricAck> getReportBatchMethod;
    if ((getReportBatchMethod = MetricServiceGrpc.getReportBatchMethod) == null) {
      synchronized (MetricServiceGrpc.class) {
        if ((getReportBatchMethod = MetricServiceGrpc.getReportBatchMethod) == null) {
          MetricServiceGrpc.getReportBatchMethod = getReportBatchMethod =
              io.grpc.MethodDescriptor.<com.aurora.grpc.BatchMetricDataRequest, com.aurora.grpc.MetricAck>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReportBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aurora.grpc.BatchMetricDataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aurora.grpc.MetricAck.getDefaultInstance()))
              .setSchemaDescriptor(new MetricServiceMethodDescriptorSupplier("ReportBatch"))
              .build();
        }
      }
    }
    return getReportBatchMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MetricServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MetricServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MetricServiceStub>() {
        @java.lang.Override
        public MetricServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MetricServiceStub(channel, callOptions);
        }
      };
    return MetricServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MetricServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MetricServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MetricServiceBlockingStub>() {
        @java.lang.Override
        public MetricServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MetricServiceBlockingStub(channel, callOptions);
        }
      };
    return MetricServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MetricServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MetricServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MetricServiceFutureStub>() {
        @java.lang.Override
        public MetricServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MetricServiceFutureStub(channel, callOptions);
        }
      };
    return MetricServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * 通用指标数据采集服务
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * 单条指标上报
     * </pre>
     */
    default void report(com.aurora.grpc.MetricDataMessage request,
        io.grpc.stub.StreamObserver<com.aurora.grpc.MetricAck> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReportMethod(), responseObserver);
    }

    /**
     * <pre>
     * 流式指标上报 - 适合高频连续数据
     * </pre>
     */
    default io.grpc.stub.StreamObserver<com.aurora.grpc.MetricDataMessage> reportStream(
        io.grpc.stub.StreamObserver<com.aurora.grpc.MetricAck> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getReportStreamMethod(), responseObserver);
    }

    /**
     * <pre>
     * 批量指标上报 - 推荐生产环境使用
     * </pre>
     */
    default void reportBatch(com.aurora.grpc.BatchMetricDataRequest request,
        io.grpc.stub.StreamObserver<com.aurora.grpc.MetricAck> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReportBatchMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service MetricService.
   * <pre>
   * 通用指标数据采集服务
   * </pre>
   */
  public static abstract class MetricServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MetricServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service MetricService.
   * <pre>
   * 通用指标数据采集服务
   * </pre>
   */
  public static final class MetricServiceStub
      extends io.grpc.stub.AbstractAsyncStub<MetricServiceStub> {
    private MetricServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MetricServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MetricServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * 单条指标上报
     * </pre>
     */
    public void report(com.aurora.grpc.MetricDataMessage request,
        io.grpc.stub.StreamObserver<com.aurora.grpc.MetricAck> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReportMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 流式指标上报 - 适合高频连续数据
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.aurora.grpc.MetricDataMessage> reportStream(
        io.grpc.stub.StreamObserver<com.aurora.grpc.MetricAck> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getReportStreamMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * 批量指标上报 - 推荐生产环境使用
     * </pre>
     */
    public void reportBatch(com.aurora.grpc.BatchMetricDataRequest request,
        io.grpc.stub.StreamObserver<com.aurora.grpc.MetricAck> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReportBatchMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service MetricService.
   * <pre>
   * 通用指标数据采集服务
   * </pre>
   */
  public static final class MetricServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MetricServiceBlockingStub> {
    private MetricServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MetricServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MetricServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * 单条指标上报
     * </pre>
     */
    public com.aurora.grpc.MetricAck report(com.aurora.grpc.MetricDataMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReportMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 批量指标上报 - 推荐生产环境使用
     * </pre>
     */
    public com.aurora.grpc.MetricAck reportBatch(com.aurora.grpc.BatchMetricDataRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReportBatchMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service MetricService.
   * <pre>
   * 通用指标数据采集服务
   * </pre>
   */
  public static final class MetricServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<MetricServiceFutureStub> {
    private MetricServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MetricServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MetricServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * 单条指标上报
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aurora.grpc.MetricAck> report(
        com.aurora.grpc.MetricDataMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReportMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * 批量指标上报 - 推荐生产环境使用
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aurora.grpc.MetricAck> reportBatch(
        com.aurora.grpc.BatchMetricDataRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReportBatchMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REPORT = 0;
  private static final int METHODID_REPORT_BATCH = 1;
  private static final int METHODID_REPORT_STREAM = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REPORT:
          serviceImpl.report((com.aurora.grpc.MetricDataMessage) request,
              (io.grpc.stub.StreamObserver<com.aurora.grpc.MetricAck>) responseObserver);
          break;
        case METHODID_REPORT_BATCH:
          serviceImpl.reportBatch((com.aurora.grpc.BatchMetricDataRequest) request,
              (io.grpc.stub.StreamObserver<com.aurora.grpc.MetricAck>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REPORT_STREAM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.reportStream(
              (io.grpc.stub.StreamObserver<com.aurora.grpc.MetricAck>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getReportMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.aurora.grpc.MetricDataMessage,
              com.aurora.grpc.MetricAck>(
                service, METHODID_REPORT)))
        .addMethod(
          getReportStreamMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              com.aurora.grpc.MetricDataMessage,
              com.aurora.grpc.MetricAck>(
                service, METHODID_REPORT_STREAM)))
        .addMethod(
          getReportBatchMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.aurora.grpc.BatchMetricDataRequest,
              com.aurora.grpc.MetricAck>(
                service, METHODID_REPORT_BATCH)))
        .build();
  }

  private static abstract class MetricServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MetricServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.aurora.grpc.MetricServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MetricService");
    }
  }

  private static final class MetricServiceFileDescriptorSupplier
      extends MetricServiceBaseDescriptorSupplier {
    MetricServiceFileDescriptorSupplier() {}
  }

  private static final class MetricServiceMethodDescriptorSupplier
      extends MetricServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    MetricServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MetricServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MetricServiceFileDescriptorSupplier())
              .addMethod(getReportMethod())
              .addMethod(getReportStreamMethod())
              .addMethod(getReportBatchMethod())
              .build();
        }
      }
    }
    return result;
  }
}
