// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: matrix.proto

package com.example.grpc.server.grpcserver;

/**
 * Protobuf type {@code matrixmult.MatrixReplystream}
 */
public  final class MatrixReplystream extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:matrixmult.MatrixReplystream)
    MatrixReplystreamOrBuilder {
  // Use MatrixReplystream.newBuilder() to construct.
  private MatrixReplystream(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MatrixReplystream() {
    reply_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private MatrixReplystream(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              reply_ = new java.util.ArrayList<com.example.grpc.server.grpcserver.MatrixReply>();
              mutable_bitField0_ |= 0x00000001;
            }
            reply_.add(
                input.readMessage(com.example.grpc.server.grpcserver.MatrixReply.parser(), extensionRegistry));
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        reply_ = java.util.Collections.unmodifiableList(reply_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.example.grpc.server.grpcserver.MatrixMult.internal_static_matrixmult_MatrixReplystream_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.example.grpc.server.grpcserver.MatrixMult.internal_static_matrixmult_MatrixReplystream_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.example.grpc.server.grpcserver.MatrixReplystream.class, com.example.grpc.server.grpcserver.MatrixReplystream.Builder.class);
  }

  public static final int REPLY_FIELD_NUMBER = 1;
  private java.util.List<com.example.grpc.server.grpcserver.MatrixReply> reply_;
  /**
   * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
   */
  public java.util.List<com.example.grpc.server.grpcserver.MatrixReply> getReplyList() {
    return reply_;
  }
  /**
   * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
   */
  public java.util.List<? extends com.example.grpc.server.grpcserver.MatrixReplyOrBuilder> 
      getReplyOrBuilderList() {
    return reply_;
  }
  /**
   * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
   */
  public int getReplyCount() {
    return reply_.size();
  }
  /**
   * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
   */
  public com.example.grpc.server.grpcserver.MatrixReply getReply(int index) {
    return reply_.get(index);
  }
  /**
   * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
   */
  public com.example.grpc.server.grpcserver.MatrixReplyOrBuilder getReplyOrBuilder(
      int index) {
    return reply_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < reply_.size(); i++) {
      output.writeMessage(1, reply_.get(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < reply_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, reply_.get(i));
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.example.grpc.server.grpcserver.MatrixReplystream)) {
      return super.equals(obj);
    }
    com.example.grpc.server.grpcserver.MatrixReplystream other = (com.example.grpc.server.grpcserver.MatrixReplystream) obj;

    boolean result = true;
    result = result && getReplyList()
        .equals(other.getReplyList());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getReplyCount() > 0) {
      hash = (37 * hash) + REPLY_FIELD_NUMBER;
      hash = (53 * hash) + getReplyList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.example.grpc.server.grpcserver.MatrixReplystream parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.grpc.server.grpcserver.MatrixReplystream parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.grpc.server.grpcserver.MatrixReplystream parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.grpc.server.grpcserver.MatrixReplystream parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.grpc.server.grpcserver.MatrixReplystream parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.grpc.server.grpcserver.MatrixReplystream parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.grpc.server.grpcserver.MatrixReplystream parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.example.grpc.server.grpcserver.MatrixReplystream parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.grpc.server.grpcserver.MatrixReplystream parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.example.grpc.server.grpcserver.MatrixReplystream parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.grpc.server.grpcserver.MatrixReplystream parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.example.grpc.server.grpcserver.MatrixReplystream parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.example.grpc.server.grpcserver.MatrixReplystream prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code matrixmult.MatrixReplystream}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:matrixmult.MatrixReplystream)
      com.example.grpc.server.grpcserver.MatrixReplystreamOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.example.grpc.server.grpcserver.MatrixMult.internal_static_matrixmult_MatrixReplystream_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.example.grpc.server.grpcserver.MatrixMult.internal_static_matrixmult_MatrixReplystream_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.example.grpc.server.grpcserver.MatrixReplystream.class, com.example.grpc.server.grpcserver.MatrixReplystream.Builder.class);
    }

    // Construct using com.example.grpc.server.grpcserver.MatrixReplystream.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getReplyFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (replyBuilder_ == null) {
        reply_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        replyBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.example.grpc.server.grpcserver.MatrixMult.internal_static_matrixmult_MatrixReplystream_descriptor;
    }

    public com.example.grpc.server.grpcserver.MatrixReplystream getDefaultInstanceForType() {
      return com.example.grpc.server.grpcserver.MatrixReplystream.getDefaultInstance();
    }

    public com.example.grpc.server.grpcserver.MatrixReplystream build() {
      com.example.grpc.server.grpcserver.MatrixReplystream result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.example.grpc.server.grpcserver.MatrixReplystream buildPartial() {
      com.example.grpc.server.grpcserver.MatrixReplystream result = new com.example.grpc.server.grpcserver.MatrixReplystream(this);
      int from_bitField0_ = bitField0_;
      if (replyBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          reply_ = java.util.Collections.unmodifiableList(reply_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.reply_ = reply_;
      } else {
        result.reply_ = replyBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.example.grpc.server.grpcserver.MatrixReplystream) {
        return mergeFrom((com.example.grpc.server.grpcserver.MatrixReplystream)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.example.grpc.server.grpcserver.MatrixReplystream other) {
      if (other == com.example.grpc.server.grpcserver.MatrixReplystream.getDefaultInstance()) return this;
      if (replyBuilder_ == null) {
        if (!other.reply_.isEmpty()) {
          if (reply_.isEmpty()) {
            reply_ = other.reply_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureReplyIsMutable();
            reply_.addAll(other.reply_);
          }
          onChanged();
        }
      } else {
        if (!other.reply_.isEmpty()) {
          if (replyBuilder_.isEmpty()) {
            replyBuilder_.dispose();
            replyBuilder_ = null;
            reply_ = other.reply_;
            bitField0_ = (bitField0_ & ~0x00000001);
            replyBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getReplyFieldBuilder() : null;
          } else {
            replyBuilder_.addAllMessages(other.reply_);
          }
        }
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.example.grpc.server.grpcserver.MatrixReplystream parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.example.grpc.server.grpcserver.MatrixReplystream) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<com.example.grpc.server.grpcserver.MatrixReply> reply_ =
      java.util.Collections.emptyList();
    private void ensureReplyIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        reply_ = new java.util.ArrayList<com.example.grpc.server.grpcserver.MatrixReply>(reply_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.example.grpc.server.grpcserver.MatrixReply, com.example.grpc.server.grpcserver.MatrixReply.Builder, com.example.grpc.server.grpcserver.MatrixReplyOrBuilder> replyBuilder_;

    /**
     * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
     */
    public java.util.List<com.example.grpc.server.grpcserver.MatrixReply> getReplyList() {
      if (replyBuilder_ == null) {
        return java.util.Collections.unmodifiableList(reply_);
      } else {
        return replyBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
     */
    public int getReplyCount() {
      if (replyBuilder_ == null) {
        return reply_.size();
      } else {
        return replyBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
     */
    public com.example.grpc.server.grpcserver.MatrixReply getReply(int index) {
      if (replyBuilder_ == null) {
        return reply_.get(index);
      } else {
        return replyBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
     */
    public Builder setReply(
        int index, com.example.grpc.server.grpcserver.MatrixReply value) {
      if (replyBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureReplyIsMutable();
        reply_.set(index, value);
        onChanged();
      } else {
        replyBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
     */
    public Builder setReply(
        int index, com.example.grpc.server.grpcserver.MatrixReply.Builder builderForValue) {
      if (replyBuilder_ == null) {
        ensureReplyIsMutable();
        reply_.set(index, builderForValue.build());
        onChanged();
      } else {
        replyBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
     */
    public Builder addReply(com.example.grpc.server.grpcserver.MatrixReply value) {
      if (replyBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureReplyIsMutable();
        reply_.add(value);
        onChanged();
      } else {
        replyBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
     */
    public Builder addReply(
        int index, com.example.grpc.server.grpcserver.MatrixReply value) {
      if (replyBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureReplyIsMutable();
        reply_.add(index, value);
        onChanged();
      } else {
        replyBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
     */
    public Builder addReply(
        com.example.grpc.server.grpcserver.MatrixReply.Builder builderForValue) {
      if (replyBuilder_ == null) {
        ensureReplyIsMutable();
        reply_.add(builderForValue.build());
        onChanged();
      } else {
        replyBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
     */
    public Builder addReply(
        int index, com.example.grpc.server.grpcserver.MatrixReply.Builder builderForValue) {
      if (replyBuilder_ == null) {
        ensureReplyIsMutable();
        reply_.add(index, builderForValue.build());
        onChanged();
      } else {
        replyBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
     */
    public Builder addAllReply(
        java.lang.Iterable<? extends com.example.grpc.server.grpcserver.MatrixReply> values) {
      if (replyBuilder_ == null) {
        ensureReplyIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, reply_);
        onChanged();
      } else {
        replyBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
     */
    public Builder clearReply() {
      if (replyBuilder_ == null) {
        reply_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        replyBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
     */
    public Builder removeReply(int index) {
      if (replyBuilder_ == null) {
        ensureReplyIsMutable();
        reply_.remove(index);
        onChanged();
      } else {
        replyBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
     */
    public com.example.grpc.server.grpcserver.MatrixReply.Builder getReplyBuilder(
        int index) {
      return getReplyFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
     */
    public com.example.grpc.server.grpcserver.MatrixReplyOrBuilder getReplyOrBuilder(
        int index) {
      if (replyBuilder_ == null) {
        return reply_.get(index);  } else {
        return replyBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
     */
    public java.util.List<? extends com.example.grpc.server.grpcserver.MatrixReplyOrBuilder> 
         getReplyOrBuilderList() {
      if (replyBuilder_ != null) {
        return replyBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(reply_);
      }
    }
    /**
     * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
     */
    public com.example.grpc.server.grpcserver.MatrixReply.Builder addReplyBuilder() {
      return getReplyFieldBuilder().addBuilder(
          com.example.grpc.server.grpcserver.MatrixReply.getDefaultInstance());
    }
    /**
     * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
     */
    public com.example.grpc.server.grpcserver.MatrixReply.Builder addReplyBuilder(
        int index) {
      return getReplyFieldBuilder().addBuilder(
          index, com.example.grpc.server.grpcserver.MatrixReply.getDefaultInstance());
    }
    /**
     * <code>repeated .matrixmult.MatrixReply reply = 1;</code>
     */
    public java.util.List<com.example.grpc.server.grpcserver.MatrixReply.Builder> 
         getReplyBuilderList() {
      return getReplyFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.example.grpc.server.grpcserver.MatrixReply, com.example.grpc.server.grpcserver.MatrixReply.Builder, com.example.grpc.server.grpcserver.MatrixReplyOrBuilder> 
        getReplyFieldBuilder() {
      if (replyBuilder_ == null) {
        replyBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.example.grpc.server.grpcserver.MatrixReply, com.example.grpc.server.grpcserver.MatrixReply.Builder, com.example.grpc.server.grpcserver.MatrixReplyOrBuilder>(
                reply_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        reply_ = null;
      }
      return replyBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:matrixmult.MatrixReplystream)
  }

  // @@protoc_insertion_point(class_scope:matrixmult.MatrixReplystream)
  private static final com.example.grpc.server.grpcserver.MatrixReplystream DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.example.grpc.server.grpcserver.MatrixReplystream();
  }

  public static com.example.grpc.server.grpcserver.MatrixReplystream getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MatrixReplystream>
      PARSER = new com.google.protobuf.AbstractParser<MatrixReplystream>() {
    public MatrixReplystream parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new MatrixReplystream(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MatrixReplystream> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MatrixReplystream> getParserForType() {
    return PARSER;
  }

  public com.example.grpc.server.grpcserver.MatrixReplystream getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

