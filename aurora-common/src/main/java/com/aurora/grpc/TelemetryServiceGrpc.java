package com.aurora.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: telemetry.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TelemetryServiceGrpc {

  private TelemetryServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "aurora.TelemetryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.aurora.grpc.TelemetryRequest,
      com.aurora.grpc.Ack> getReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Report",
      requestType = com.aurora.grpc.TelemetryRequest.class,
      responseType = com.aurora.grpc.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.aurora.grpc.TelemetryRequest,
      com.aurora.grpc.Ack> getReportMethod() {
    io.grpc.MethodDescriptor<com.aurora.grpc.TelemetryRequest, com.aurora.grpc.Ack> getReportMethod;
    if ((getReportMethod = TelemetryServiceGrpc.getReportMethod) == null) {
      synchronized (TelemetryServiceGrpc.class) {
        if ((getReportMethod = TelemetryServiceGrpc.getReportMethod) == null) {
          TelemetryServiceGrpc.getReportMethod = getReportMethod =
              io.grpc.MethodDescriptor.<com.aurora.grpc.TelemetryRequest, com.aurora.grpc.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Report"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aurora.grpc.TelemetryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aurora.grpc.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new TelemetryServiceMethodDescriptorSupplier("Report"))
              .build();
        }
      }
    }
    return getReportMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.aurora.grpc.BatchTelemetryRequest,
      com.aurora.grpc.Ack> getReportBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReportBatch",
      requestType = com.aurora.grpc.BatchTelemetryRequest.class,
      responseType = com.aurora.grpc.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.aurora.grpc.BatchTelemetryRequest,
      com.aurora.grpc.Ack> getReportBatchMethod() {
    io.grpc.MethodDescriptor<com.aurora.grpc.BatchTelemetryRequest, com.aurora.grpc.Ack> getReportBatchMethod;
    if ((getReportBatchMethod = TelemetryServiceGrpc.getReportBatchMethod) == null) {
      synchronized (TelemetryServiceGrpc.class) {
        if ((getReportBatchMethod = TelemetryServiceGrpc.getReportBatchMethod) == null) {
          TelemetryServiceGrpc.getReportBatchMethod = getReportBatchMethod =
              io.grpc.MethodDescriptor.<com.aurora.grpc.BatchTelemetryRequest, com.aurora.grpc.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReportBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aurora.grpc.BatchTelemetryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aurora.grpc.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new TelemetryServiceMethodDescriptorSupplier("ReportBatch"))
              .build();
        }
      }
    }
    return getReportBatchMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TelemetryServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TelemetryServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TelemetryServiceStub>() {
        @java.lang.Override
        public TelemetryServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TelemetryServiceStub(channel, callOptions);
        }
      };
    return TelemetryServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TelemetryServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TelemetryServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TelemetryServiceBlockingStub>() {
        @java.lang.Override
        public TelemetryServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TelemetryServiceBlockingStub(channel, callOptions);
        }
      };
    return TelemetryServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TelemetryServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TelemetryServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TelemetryServiceFutureStub>() {
        @java.lang.Override
        public TelemetryServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TelemetryServiceFutureStub(channel, callOptions);
        }
      };
    return TelemetryServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * 原始流式接口：客户端持续发送数据，服务端最后返回结果
     * </pre>
     */
    default io.grpc.stub.StreamObserver<com.aurora.grpc.TelemetryRequest> report(
        io.grpc.stub.StreamObserver<com.aurora.grpc.Ack> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getReportMethod(), responseObserver);
    }

    /**
     * <pre>
     * 新增批量接口：一次性发送多条指标数据
     * </pre>
     */
    default void reportBatch(com.aurora.grpc.BatchTelemetryRequest request,
        io.grpc.stub.StreamObserver<com.aurora.grpc.Ack> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReportBatchMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service TelemetryService.
   */
  public static abstract class TelemetryServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return TelemetryServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service TelemetryService.
   */
  public static final class TelemetryServiceStub
      extends io.grpc.stub.AbstractAsyncStub<TelemetryServiceStub> {
    private TelemetryServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TelemetryServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TelemetryServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * 原始流式接口：客户端持续发送数据，服务端最后返回结果
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.aurora.grpc.TelemetryRequest> report(
        io.grpc.stub.StreamObserver<com.aurora.grpc.Ack> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getReportMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * 新增批量接口：一次性发送多条指标数据
     * </pre>
     */
    public void reportBatch(com.aurora.grpc.BatchTelemetryRequest request,
        io.grpc.stub.StreamObserver<com.aurora.grpc.Ack> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReportBatchMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service TelemetryService.
   */
  public static final class TelemetryServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<TelemetryServiceBlockingStub> {
    private TelemetryServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TelemetryServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TelemetryServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * 新增批量接口：一次性发送多条指标数据
     * </pre>
     */
    public com.aurora.grpc.Ack reportBatch(com.aurora.grpc.BatchTelemetryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReportBatchMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service TelemetryService.
   */
  public static final class TelemetryServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<TelemetryServiceFutureStub> {
    private TelemetryServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TelemetryServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TelemetryServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * 新增批量接口：一次性发送多条指标数据
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aurora.grpc.Ack> reportBatch(
        com.aurora.grpc.BatchTelemetryRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReportBatchMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REPORT_BATCH = 0;
  private static final int METHODID_REPORT = 1;

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
        case METHODID_REPORT_BATCH:
          serviceImpl.reportBatch((com.aurora.grpc.BatchTelemetryRequest) request,
              (io.grpc.stub.StreamObserver<com.aurora.grpc.Ack>) responseObserver);
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
        case METHODID_REPORT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.report(
              (io.grpc.stub.StreamObserver<com.aurora.grpc.Ack>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getReportMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              com.aurora.grpc.TelemetryRequest,
              com.aurora.grpc.Ack>(
                service, METHODID_REPORT)))
        .addMethod(
          getReportBatchMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.aurora.grpc.BatchTelemetryRequest,
              com.aurora.grpc.Ack>(
                service, METHODID_REPORT_BATCH)))
        .build();
  }

  private static abstract class TelemetryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TelemetryServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.aurora.grpc.TelemetryProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TelemetryService");
    }
  }

  private static final class TelemetryServiceFileDescriptorSupplier
      extends TelemetryServiceBaseDescriptorSupplier {
    TelemetryServiceFileDescriptorSupplier() {}
  }

  private static final class TelemetryServiceMethodDescriptorSupplier
      extends TelemetryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    TelemetryServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (TelemetryServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TelemetryServiceFileDescriptorSupplier())
              .addMethod(getReportMethod())
              .addMethod(getReportBatchMethod())
              .build();
        }
      }
    }
    return result;
  }
}
