package com.aurora.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: processmetric.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ProcessMetricServiceGrpc {

  private ProcessMetricServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "aurora.ProcessMetricService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.aurora.grpc.ProcessMetricMessage,
      com.aurora.grpc.ProcessMetricAck> getReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Report",
      requestType = com.aurora.grpc.ProcessMetricMessage.class,
      responseType = com.aurora.grpc.ProcessMetricAck.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.aurora.grpc.ProcessMetricMessage,
      com.aurora.grpc.ProcessMetricAck> getReportMethod() {
    io.grpc.MethodDescriptor<com.aurora.grpc.ProcessMetricMessage, com.aurora.grpc.ProcessMetricAck> getReportMethod;
    if ((getReportMethod = ProcessMetricServiceGrpc.getReportMethod) == null) {
      synchronized (ProcessMetricServiceGrpc.class) {
        if ((getReportMethod = ProcessMetricServiceGrpc.getReportMethod) == null) {
          ProcessMetricServiceGrpc.getReportMethod = getReportMethod =
              io.grpc.MethodDescriptor.<com.aurora.grpc.ProcessMetricMessage, com.aurora.grpc.ProcessMetricAck>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Report"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aurora.grpc.ProcessMetricMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aurora.grpc.ProcessMetricAck.getDefaultInstance()))
              .setSchemaDescriptor(new ProcessMetricServiceMethodDescriptorSupplier("Report"))
              .build();
        }
      }
    }
    return getReportMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProcessMetricServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProcessMetricServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProcessMetricServiceStub>() {
        @java.lang.Override
        public ProcessMetricServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProcessMetricServiceStub(channel, callOptions);
        }
      };
    return ProcessMetricServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProcessMetricServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProcessMetricServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProcessMetricServiceBlockingStub>() {
        @java.lang.Override
        public ProcessMetricServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProcessMetricServiceBlockingStub(channel, callOptions);
        }
      };
    return ProcessMetricServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProcessMetricServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProcessMetricServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProcessMetricServiceFutureStub>() {
        @java.lang.Override
        public ProcessMetricServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProcessMetricServiceFutureStub(channel, callOptions);
        }
      };
    return ProcessMetricServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * 原始流式接口：客户端持续发送数据，服务端最后返回结果
     * </pre>
     */
    default io.grpc.stub.StreamObserver<com.aurora.grpc.ProcessMetricMessage> report(
        io.grpc.stub.StreamObserver<com.aurora.grpc.ProcessMetricAck> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getReportMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ProcessMetricService.
   */
  public static abstract class ProcessMetricServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ProcessMetricServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ProcessMetricService.
   */
  public static final class ProcessMetricServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ProcessMetricServiceStub> {
    private ProcessMetricServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProcessMetricServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProcessMetricServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * 原始流式接口：客户端持续发送数据，服务端最后返回结果
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.aurora.grpc.ProcessMetricMessage> report(
        io.grpc.stub.StreamObserver<com.aurora.grpc.ProcessMetricAck> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getReportMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ProcessMetricService.
   */
  public static final class ProcessMetricServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ProcessMetricServiceBlockingStub> {
    private ProcessMetricServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProcessMetricServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProcessMetricServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ProcessMetricService.
   */
  public static final class ProcessMetricServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ProcessMetricServiceFutureStub> {
    private ProcessMetricServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProcessMetricServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProcessMetricServiceFutureStub(channel, callOptions);
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
              (io.grpc.stub.StreamObserver<com.aurora.grpc.ProcessMetricAck>) responseObserver);
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
              com.aurora.grpc.ProcessMetricMessage,
              com.aurora.grpc.ProcessMetricAck>(
                service, METHODID_REPORT)))
        .build();
  }

  private static abstract class ProcessMetricServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProcessMetricServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.aurora.grpc.ProcessMetricProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ProcessMetricService");
    }
  }

  private static final class ProcessMetricServiceFileDescriptorSupplier
      extends ProcessMetricServiceBaseDescriptorSupplier {
    ProcessMetricServiceFileDescriptorSupplier() {}
  }

  private static final class ProcessMetricServiceMethodDescriptorSupplier
      extends ProcessMetricServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ProcessMetricServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ProcessMetricServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProcessMetricServiceFileDescriptorSupplier())
              .addMethod(getReportMethod())
              .build();
        }
      }
    }
    return result;
  }
}
