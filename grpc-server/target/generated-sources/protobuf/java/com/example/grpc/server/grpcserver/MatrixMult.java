// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: matrix.proto

package com.example.grpc.server.grpcserver;

public final class MatrixMult {
  private MatrixMult() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_matrixmult_MatrixRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_matrixmult_MatrixRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_matrixmult_MatrixReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_matrixmult_MatrixReply_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014matrix.proto\022\nmatrixmult\"w\n\rMatrixRequ" +
      "est\022\013\n\003a00\030\001 \001(\005\022\013\n\003a01\030\002 \001(\005\022\013\n\003a10\030\003 \001" +
      "(\005\022\013\n\003a11\030\004 \001(\005\022\013\n\003b00\030\005 \001(\005\022\013\n\003b01\030\006 \001(" +
      "\005\022\013\n\003b10\030\007 \001(\005\022\013\n\003b11\030\010 \001(\005\"A\n\013MatrixRep" +
      "ly\022\013\n\003c00\030\001 \001(\005\022\013\n\003c01\030\002 \001(\005\022\013\n\003c10\030\003 \001(" +
      "\005\022\013\n\003c11\030\004 \001(\0052\261\002\n\rMatrixService\022E\n\rMult" +
      "iplyBlock\022\031.matrixmult.MatrixRequest\032\027.m" +
      "atrixmult.MatrixReply\"\000\022M\n\023MultiplyBlock" +
      "stream\022\031.matrixmult.MatrixRequest\032\027.matr" +
      "ixmult.MatrixReply\"\0000\001\022@\n\010AddBlock\022\031.mat",
      "rixmult.MatrixRequest\032\027.matrixmult.Matri" +
      "xReply\"\000\022H\n\016AddBlockstream\022\031.matrixmult." +
      "MatrixRequest\032\027.matrixmult.MatrixReply\"\000" +
      "0\001B7\n\"com.example.grpc.server.grpcserver" +
      "B\nMatrixMultP\001\242\002\002MMb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_matrixmult_MatrixRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_matrixmult_MatrixRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_matrixmult_MatrixRequest_descriptor,
        new java.lang.String[] { "A00", "A01", "A10", "A11", "B00", "B01", "B10", "B11", });
    internal_static_matrixmult_MatrixReply_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_matrixmult_MatrixReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_matrixmult_MatrixReply_descriptor,
        new java.lang.String[] { "C00", "C01", "C10", "C11", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
