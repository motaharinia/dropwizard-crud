package com.motaharinia.client.project.modules.member.rpc.stub.findbyid;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: MemberFindByNationalCode.proto")
public final class MemberGrpc {

  private MemberGrpc() {}

  public static final String SERVICE_NAME = "Member";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberReadByIdRequestDto,
      com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberResponseDto> getReadByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReadById",
      requestType = com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberReadByIdRequestDto.class,
      responseType = com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberResponseDto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberReadByIdRequestDto,
      com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberResponseDto> getReadByIdMethod() {
    io.grpc.MethodDescriptor<com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberReadByIdRequestDto, com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberResponseDto> getReadByIdMethod;
    if ((getReadByIdMethod = MemberGrpc.getReadByIdMethod) == null) {
      synchronized (MemberGrpc.class) {
        if ((getReadByIdMethod = MemberGrpc.getReadByIdMethod) == null) {
          MemberGrpc.getReadByIdMethod = getReadByIdMethod = 
              io.grpc.MethodDescriptor.<com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberReadByIdRequestDto, com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberResponseDto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Member", "ReadById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberReadByIdRequestDto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberResponseDto.getDefaultInstance()))
                  .setSchemaDescriptor(new MemberMethodDescriptorSupplier("ReadById"))
                  .build();
          }
        }
     }
     return getReadByIdMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MemberStub newStub(io.grpc.Channel channel) {
    return new MemberStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MemberBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MemberBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MemberFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MemberFutureStub(channel);
  }

  /**
   */
  public static abstract class MemberImplBase implements io.grpc.BindableService {

    /**
     */
    public void readById(com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberReadByIdRequestDto request,
        io.grpc.stub.StreamObserver<com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberResponseDto> responseObserver) {
      asyncUnimplementedUnaryCall(getReadByIdMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getReadByIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberReadByIdRequestDto,
                com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberResponseDto>(
                  this, METHODID_READ_BY_ID)))
          .build();
    }
  }

  /**
   */
  public static final class MemberStub extends io.grpc.stub.AbstractStub<MemberStub> {
    private MemberStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MemberStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MemberStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MemberStub(channel, callOptions);
    }

    /**
     */
    public void readById(com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberReadByIdRequestDto request,
        io.grpc.stub.StreamObserver<com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberResponseDto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReadByIdMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MemberBlockingStub extends io.grpc.stub.AbstractStub<MemberBlockingStub> {
    private MemberBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MemberBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MemberBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MemberBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberResponseDto readById(com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberReadByIdRequestDto request) {
      return blockingUnaryCall(
          getChannel(), getReadByIdMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MemberFutureStub extends io.grpc.stub.AbstractStub<MemberFutureStub> {
    private MemberFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MemberFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MemberFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MemberFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberResponseDto> readById(
        com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberReadByIdRequestDto request) {
      return futureUnaryCall(
          getChannel().newCall(getReadByIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_READ_BY_ID = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MemberImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MemberImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_READ_BY_ID:
          serviceImpl.readById((com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberReadByIdRequestDto) request,
              (io.grpc.stub.StreamObserver<com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberResponseDto>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MemberBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MemberBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.motaharinia.client.project.modules.member.rpc.stub.findbyid.MemberFindByIdProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Member");
    }
  }

  private static final class MemberFileDescriptorSupplier
      extends MemberBaseDescriptorSupplier {
    MemberFileDescriptorSupplier() {}
  }

  private static final class MemberMethodDescriptorSupplier
      extends MemberBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MemberMethodDescriptorSupplier(String methodName) {
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
      synchronized (MemberGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MemberFileDescriptorSupplier())
              .addMethod(getReadByIdMethod())
              .build();
        }
      }
    }
    return result;
  }
}
