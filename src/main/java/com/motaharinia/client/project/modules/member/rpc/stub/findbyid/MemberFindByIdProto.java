// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MemberFindByNationalCode.proto

package com.motaharinia.client.project.modules.member.rpc.stub.findbyid;

public final class MemberFindByIdProto {
  private MemberFindByIdProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_MemberReadByIdRequestDto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_MemberReadByIdRequestDto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_MemberResponseDto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_MemberResponseDto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036MemberFindByNationalCode.proto\"&\n\030Memb" +
      "erReadByIdRequestDto\022\n\n\002id\030\001 \001(\003\"\343\001\n\021Mem" +
      "berResponseDto\022\027\n\017responseMessage\030\001 \001(\t\022" +
      "\024\n\014responseCode\030\002 \001(\005\022\n\n\002id\030\003 \001(\003\022\021\n\tfir" +
      "stName\030\004 \001(\t\022\020\n\010lastName\030\005 \001(\t\022\024\n\014nation" +
      "alCode\030\006 \001(\t\022\023\n\013dateOfBirth\030\007 \001(\003\022\020\n\010cre" +
      "ateAt\030\010 \001(\003\022\020\n\010updateAt\030\t \001(\003\022\017\n\007invalid" +
      "\030\n \001(\010\022\016\n\006hidden\030\013 \001(\0102C\n\006Member\0229\n\010Read" +
      "ById\022\031.MemberReadByIdRequestDto\032\022.Member" +
      "ResponseDtoBX\n?com.motaharinia.client.pr" +
      "oject.modules.member.rpc.stub.findbyidB\023" +
      "MemberFindByIdProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_MemberReadByIdRequestDto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_MemberReadByIdRequestDto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_MemberReadByIdRequestDto_descriptor,
        new java.lang.String[] { "Id", });
    internal_static_MemberResponseDto_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_MemberResponseDto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_MemberResponseDto_descriptor,
        new java.lang.String[] { "ResponseMessage", "ResponseCode", "Id", "FirstName", "LastName", "NationalCode", "DateOfBirth", "CreateAt", "UpdateAt", "Invalid", "Hidden", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
