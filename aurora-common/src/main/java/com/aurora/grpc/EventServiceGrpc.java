package com.aurora.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * 事件服务接口定义
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: event.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class EventServiceGrpc {

  private EventServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "aurora.EventService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.aurora.grpc.EventDataMessage,
      com.aurora.grpc.EventAck> getReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Report",
      requestType = com.aurora.grpc.EventDataMessage.class,
      responseType = com.aurora.grpc.EventAck.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.aurora.grpc.EventDataMessage,
      com.aurora.grpc.EventAck> getReportMethod() {
    io.grpc.MethodDescriptor<com.aurora.grpc.EventDataMessage, com.aurora.grpc.EventAck> getReportMethod;
    if ((getReportMethod = EventServiceGrpc.getReportMethod) == null) {
      synchronized (EventServiceGrpc.class) {
        if ((getReportMethod = EventServiceGrpc.getReportMethod) == null) {
          EventServiceGrpc.getReportMethod = getReportMethod =
              io.grpc.MethodDescriptor.<com.aurora.grpc.EventDataMessage, com.aurora.grpc.EventAck>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Report"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aurora.grpc.EventDataMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aurora.grpc.EventAck.getDefaultInstance()))
              .setSchemaDescriptor(new EventServiceMethodDescriptorSupplier("Report"))
              .build();
        }
      }
    }
    return getReportMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.aurora.grpc.EventDataMessage,
      com.aurora.grpc.EventAck> getReportStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReportStream",
      requestType = com.aurora.grpc.EventDataMessage.class,
      responseType = com.aurora.grpc.EventAck.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.aurora.grpc.EventDataMessage,
      com.aurora.grpc.EventAck> getReportStreamMethod() {
    io.grpc.MethodDescriptor<com.aurora.grpc.EventDataMessage, com.aurora.grpc.EventAck> getReportStreamMethod;
    if ((getReportStreamMethod = EventServiceGrpc.getReportStreamMethod) == null) {
      synchronized (EventServiceGrpc.class) {
        if ((getReportStreamMethod = EventServiceGrpc.getReportStreamMethod) == null) {
          EventServiceGrpc.getReportStreamMethod = getReportStreamMethod =
              io.grpc.MethodDescriptor.<com.aurora.grpc.EventDataMessage, com.aurora.grpc.EventAck>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReportStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aurora.grpc.EventDataMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aurora.grpc.EventAck.getDefaultInstance()))
              .setSchemaDescriptor(new EventServiceMethodDescriptorSupplier("ReportStream"))
              .build();
        }
      }
    }
    return getReportStreamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.aurora.grpc.BatchEventDataRequest,
      com.aurora.grpc.EventAck> getReportBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReportBatch",
      requestType = com.aurora.grpc.BatchEventDataRequest.class,
      responseType = com.aurora.grpc.EventAck.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.aurora.grpc.BatchEventDataRequest,
      com.aurora.grpc.EventAck> getReportBatchMethod() {
    io.grpc.MethodDescriptor<com.aurora.grpc.BatchEventDataRequest, com.aurora.grpc.EventAck> getReportBatchMethod;
    if ((getReportBatchMethod = EventServiceGrpc.getReportBatchMethod) == null) {
      synchronized (EventServiceGrpc.class) {
        if ((getReportBatchMethod = EventServiceGrpc.getReportBatchMethod) == null) {
          EventServiceGrpc.getReportBatchMethod = getReportBatchMethod =
              io.grpc.MethodDescriptor.<com.aurora.grpc.BatchEventDataRequest, com.aurora.grpc.EventAck>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReportBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aurora.grpc.BatchEventDataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aurora.grpc.EventAck.getDefaultInstance()))
              .setSchemaDescriptor(new EventServiceMethodDescriptorSupplier("ReportBatch"))
              .build();
        }
      }
    }
    return getReportBatchMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EventServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EventServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EventServiceStub>() {
        @java.lang.Override
        public EventServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EventServiceStub(channel, callOptions);
        }
      };
    return EventServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EventServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EventServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EventServiceBlockingStub>() {
        @java.lang.Override
        public EventServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EventServiceBlockingStub(channel, callOptions);
        }
      };
    return EventServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EventServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EventServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EventServiceFutureStub>() {
        @java.lang.Override
        public EventServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EventServiceFutureStub(channel, callOptions);
        }
      };
    return EventServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * 事件服务接口定义
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * 单条事件上报
     * </pre>
     */
    default void report(com.aurora.grpc.EventDataMessage request,
        io.grpc.stub.StreamObserver<com.aurora.grpc.EventAck> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReportMethod(), responseObserver);
    }

    /**
     * <pre>
     * 流式上报事件数据 - 适合高频连续数据
     * </pre>
     */
    default io.grpc.stub.StreamObserver<com.aurora.grpc.EventDataMessage> reportStream(
        io.grpc.stub.StreamObserver<com.aurora.grpc.EventAck> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getReportStreamMethod(), responseObserver);
    }

    /**
     * <pre>
     * 批量上报事件数据 - 推荐生产环境使用
     * </pre>
     */
    default void reportBatch(com.aurora.grpc.BatchEventDataRequest request,
        io.grpc.stub.StreamObserver<com.aurora.grpc.EventAck> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReportBatchMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service EventService.
   * <pre>
   * 事件服务接口定义
   * </pre>
   */
  public static abstract class EventServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return EventServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service EventService.
   * <pre>
   * 事件服务接口定义
   * </pre>
   */
  public static final class EventServiceStub
      extends io.grpc.stub.AbstractAsyncStub<EventServiceStub> {
    private EventServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EventServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * 单条事件上报
     * </pre>
     */
    public void report(com.aurora.grpc.EventDataMessage request,
        io.grpc.stub.StreamObserver<com.aurora.grpc.EventAck> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReportMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 流式上报事件数据 - 适合高频连续数据
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.aurora.grpc.EventDataMessage> reportStream(
        io.grpc.stub.StreamObserver<com.aurora.grpc.EventAck> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getReportStreamMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * 批量上报事件数据 - 推荐生产环境使用
     * </pre>
     */
    public void reportBatch(com.aurora.grpc.BatchEventDataRequest request,
        io.grpc.stub.StreamObserver<com.aurora.grpc.EventAck> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReportBatchMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service EventService.
   * <pre>
   * 事件服务接口定义
   * </pre>
   */
  public static final class EventServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<EventServiceBlockingStub> {
    private EventServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EventServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * 单条事件上报
     * </pre>
     */
    public com.aurora.grpc.EventAck report(com.aurora.grpc.EventDataMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReportMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 批量上报事件数据 - 推荐生产环境使用
     * </pre>
     */
    public com.aurora.grpc.EventAck reportBatch(com.aurora.grpc.BatchEventDataRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReportBatchMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service EventService.
   * <pre>
   * 事件服务接口定义
   * </pre>
   */
  public static final class EventServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<EventServiceFutureStub> {
    private EventServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EventServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * 单条事件上报
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aurora.grpc.EventAck> report(
        com.aurora.grpc.EventDataMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReportMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * 批量上报事件数据 - 推荐生产环境使用
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aurora.grpc.EventAck> reportBatch(
        com.aurora.grpc.BatchEventDataRequest request) {
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
          serviceImpl.report((com.aurora.grpc.EventDataMessage) request,
              (io.grpc.stub.StreamObserver<com.aurora.grpc.EventAck>) responseObserver);
          break;
        case METHODID_REPORT_BATCH:
          serviceImpl.reportBatch((com.aurora.grpc.BatchEventDataRequest) request,
              (io.grpc.stub.StreamObserver<com.aurora.grpc.EventAck>) responseObserver);
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
              (io.grpc.stub.StreamObserver<com.aurora.grpc.EventAck>) responseObserver);
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
              com.aurora.grpc.EventDataMessage,
              com.aurora.grpc.EventAck>(
                service, METHODID_REPORT)))
        .addMethod(
          getReportStreamMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              com.aurora.grpc.EventDataMessage,
              com.aurora.grpc.EventAck>(
                service, METHODID_REPORT_STREAM)))
        .addMethod(
          getReportBatchMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.aurora.grpc.BatchEventDataRequest,
              com.aurora.grpc.EventAck>(
                service, METHODID_REPORT_BATCH)))
        .build();
  }

  private static abstract class EventServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EventServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.aurora.grpc.EventServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EventService");
    }
  }

  private static final class EventServiceFileDescriptorSupplier
      extends EventServiceBaseDescriptorSupplier {
    EventServiceFileDescriptorSupplier() {}
  }

  private static final class EventServiceMethodDescriptorSupplier
      extends EventServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    EventServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (EventServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EventServiceFileDescriptorSupplier())
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
