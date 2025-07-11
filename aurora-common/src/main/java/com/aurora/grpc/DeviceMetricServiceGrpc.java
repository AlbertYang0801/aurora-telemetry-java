package com.aurora.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 *设备指标
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: devicemetric.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class DeviceMetricServiceGrpc {

  private DeviceMetricServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "metric.DeviceMetricService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.aurora.grpc.DeviceMetricMessage,
      com.aurora.grpc.DeviceMetricAck> getReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Report",
      requestType = com.aurora.grpc.DeviceMetricMessage.class,
      responseType = com.aurora.grpc.DeviceMetricAck.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.aurora.grpc.DeviceMetricMessage,
      com.aurora.grpc.DeviceMetricAck> getReportMethod() {
    io.grpc.MethodDescriptor<com.aurora.grpc.DeviceMetricMessage, com.aurora.grpc.DeviceMetricAck> getReportMethod;
    if ((getReportMethod = DeviceMetricServiceGrpc.getReportMethod) == null) {
      synchronized (DeviceMetricServiceGrpc.class) {
        if ((getReportMethod = DeviceMetricServiceGrpc.getReportMethod) == null) {
          DeviceMetricServiceGrpc.getReportMethod = getReportMethod =
              io.grpc.MethodDescriptor.<com.aurora.grpc.DeviceMetricMessage, com.aurora.grpc.DeviceMetricAck>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Report"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aurora.grpc.DeviceMetricMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aurora.grpc.DeviceMetricAck.getDefaultInstance()))
              .setSchemaDescriptor(new DeviceMetricServiceMethodDescriptorSupplier("Report"))
              .build();
        }
      }
    }
    return getReportMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DeviceMetricServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DeviceMetricServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DeviceMetricServiceStub>() {
        @java.lang.Override
        public DeviceMetricServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DeviceMetricServiceStub(channel, callOptions);
        }
      };
    return DeviceMetricServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DeviceMetricServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DeviceMetricServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DeviceMetricServiceBlockingStub>() {
        @java.lang.Override
        public DeviceMetricServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DeviceMetricServiceBlockingStub(channel, callOptions);
        }
      };
    return DeviceMetricServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DeviceMetricServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DeviceMetricServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DeviceMetricServiceFutureStub>() {
        @java.lang.Override
        public DeviceMetricServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DeviceMetricServiceFutureStub(channel, callOptions);
        }
      };
    return DeviceMetricServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   *设备指标
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<com.aurora.grpc.DeviceMetricMessage> report(
        io.grpc.stub.StreamObserver<com.aurora.grpc.DeviceMetricAck> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getReportMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service DeviceMetricService.
   * <pre>
   *设备指标
   * </pre>
   */
  public static abstract class DeviceMetricServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return DeviceMetricServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service DeviceMetricService.
   * <pre>
   *设备指标
   * </pre>
   */
  public static final class DeviceMetricServiceStub
      extends io.grpc.stub.AbstractAsyncStub<DeviceMetricServiceStub> {
    private DeviceMetricServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeviceMetricServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DeviceMetricServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.aurora.grpc.DeviceMetricMessage> report(
        io.grpc.stub.StreamObserver<com.aurora.grpc.DeviceMetricAck> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getReportMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service DeviceMetricService.
   * <pre>
   *设备指标
   * </pre>
   */
  public static final class DeviceMetricServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<DeviceMetricServiceBlockingStub> {
    private DeviceMetricServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeviceMetricServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DeviceMetricServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service DeviceMetricService.
   * <pre>
   *设备指标
   * </pre>
   */
  public static final class DeviceMetricServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<DeviceMetricServiceFutureStub> {
    private DeviceMetricServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeviceMetricServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DeviceMetricServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_REPORT = 0;

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
              (io.grpc.stub.StreamObserver<com.aurora.grpc.DeviceMetricAck>) responseObserver);
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
              com.aurora.grpc.DeviceMetricMessage,
              com.aurora.grpc.DeviceMetricAck>(
                service, METHODID_REPORT)))
        .build();
  }

  private static abstract class DeviceMetricServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DeviceMetricServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.aurora.grpc.Devicemetric.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DeviceMetricService");
    }
  }

  private static final class DeviceMetricServiceFileDescriptorSupplier
      extends DeviceMetricServiceBaseDescriptorSupplier {
    DeviceMetricServiceFileDescriptorSupplier() {}
  }

  private static final class DeviceMetricServiceMethodDescriptorSupplier
      extends DeviceMetricServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    DeviceMetricServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (DeviceMetricServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DeviceMetricServiceFileDescriptorSupplier())
              .addMethod(getReportMethod())
              .build();
        }
      }
    }
    return result;
  }
}
