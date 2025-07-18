// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: processmetric.proto

package com.aurora.grpc;

/**
 * <pre>
 *进程指标
 * </pre>
 *
 * Protobuf type {@code aurora.ProcessMetricMessage}
 */
public final class ProcessMetricMessage extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:aurora.ProcessMetricMessage)
    ProcessMetricMessageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ProcessMetricMessage.newBuilder() to construct.
  private ProcessMetricMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ProcessMetricMessage() {
    ip_ = "";
    metrics_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ProcessMetricMessage();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.aurora.grpc.ProcessMetricProto.internal_static_aurora_ProcessMetricMessage_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.aurora.grpc.ProcessMetricProto.internal_static_aurora_ProcessMetricMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.aurora.grpc.ProcessMetricMessage.class, com.aurora.grpc.ProcessMetricMessage.Builder.class);
  }

  public static final int PLACEID_FIELD_NUMBER = 1;
  private int placeId_ = 0;
  /**
   * <code>int32 placeId = 1;</code>
   * @return The placeId.
   */
  @java.lang.Override
  public int getPlaceId() {
    return placeId_;
  }

  public static final int IP_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private volatile java.lang.Object ip_ = "";
  /**
   * <code>string ip = 2;</code>
   * @return The ip.
   */
  @java.lang.Override
  public java.lang.String getIp() {
    java.lang.Object ref = ip_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      ip_ = s;
      return s;
    }
  }
  /**
   * <code>string ip = 2;</code>
   * @return The bytes for ip.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getIpBytes() {
    java.lang.Object ref = ip_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      ip_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TIME_FIELD_NUMBER = 3;
  private long time_ = 0L;
  /**
   * <code>int64 time = 3;</code>
   * @return The time.
   */
  @java.lang.Override
  public long getTime() {
    return time_;
  }

  public static final int PID_FIELD_NUMBER = 4;
  private int pid_ = 0;
  /**
   * <code>int32 pid = 4;</code>
   * @return The pid.
   */
  @java.lang.Override
  public int getPid() {
    return pid_;
  }

  public static final int METRICS_FIELD_NUMBER = 5;
  @SuppressWarnings("serial")
  private java.util.List<com.aurora.grpc.ProcessMetricItem> metrics_;
  /**
   * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
   */
  @java.lang.Override
  public java.util.List<com.aurora.grpc.ProcessMetricItem> getMetricsList() {
    return metrics_;
  }
  /**
   * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.aurora.grpc.ProcessMetricItemOrBuilder> 
      getMetricsOrBuilderList() {
    return metrics_;
  }
  /**
   * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
   */
  @java.lang.Override
  public int getMetricsCount() {
    return metrics_.size();
  }
  /**
   * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
   */
  @java.lang.Override
  public com.aurora.grpc.ProcessMetricItem getMetrics(int index) {
    return metrics_.get(index);
  }
  /**
   * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
   */
  @java.lang.Override
  public com.aurora.grpc.ProcessMetricItemOrBuilder getMetricsOrBuilder(
      int index) {
    return metrics_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (placeId_ != 0) {
      output.writeInt32(1, placeId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(ip_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, ip_);
    }
    if (time_ != 0L) {
      output.writeInt64(3, time_);
    }
    if (pid_ != 0) {
      output.writeInt32(4, pid_);
    }
    for (int i = 0; i < metrics_.size(); i++) {
      output.writeMessage(5, metrics_.get(i));
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (placeId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, placeId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(ip_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, ip_);
    }
    if (time_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, time_);
    }
    if (pid_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, pid_);
    }
    for (int i = 0; i < metrics_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(5, metrics_.get(i));
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.aurora.grpc.ProcessMetricMessage)) {
      return super.equals(obj);
    }
    com.aurora.grpc.ProcessMetricMessage other = (com.aurora.grpc.ProcessMetricMessage) obj;

    if (getPlaceId()
        != other.getPlaceId()) return false;
    if (!getIp()
        .equals(other.getIp())) return false;
    if (getTime()
        != other.getTime()) return false;
    if (getPid()
        != other.getPid()) return false;
    if (!getMetricsList()
        .equals(other.getMetricsList())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + PLACEID_FIELD_NUMBER;
    hash = (53 * hash) + getPlaceId();
    hash = (37 * hash) + IP_FIELD_NUMBER;
    hash = (53 * hash) + getIp().hashCode();
    hash = (37 * hash) + TIME_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTime());
    hash = (37 * hash) + PID_FIELD_NUMBER;
    hash = (53 * hash) + getPid();
    if (getMetricsCount() > 0) {
      hash = (37 * hash) + METRICS_FIELD_NUMBER;
      hash = (53 * hash) + getMetricsList().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.aurora.grpc.ProcessMetricMessage parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aurora.grpc.ProcessMetricMessage parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aurora.grpc.ProcessMetricMessage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aurora.grpc.ProcessMetricMessage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aurora.grpc.ProcessMetricMessage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aurora.grpc.ProcessMetricMessage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aurora.grpc.ProcessMetricMessage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.aurora.grpc.ProcessMetricMessage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.aurora.grpc.ProcessMetricMessage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.aurora.grpc.ProcessMetricMessage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.aurora.grpc.ProcessMetricMessage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.aurora.grpc.ProcessMetricMessage parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.aurora.grpc.ProcessMetricMessage prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
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
   * <pre>
   *进程指标
   * </pre>
   *
   * Protobuf type {@code aurora.ProcessMetricMessage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:aurora.ProcessMetricMessage)
      com.aurora.grpc.ProcessMetricMessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.aurora.grpc.ProcessMetricProto.internal_static_aurora_ProcessMetricMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.aurora.grpc.ProcessMetricProto.internal_static_aurora_ProcessMetricMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.aurora.grpc.ProcessMetricMessage.class, com.aurora.grpc.ProcessMetricMessage.Builder.class);
    }

    // Construct using com.aurora.grpc.ProcessMetricMessage.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      placeId_ = 0;
      ip_ = "";
      time_ = 0L;
      pid_ = 0;
      if (metricsBuilder_ == null) {
        metrics_ = java.util.Collections.emptyList();
      } else {
        metrics_ = null;
        metricsBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000010);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.aurora.grpc.ProcessMetricProto.internal_static_aurora_ProcessMetricMessage_descriptor;
    }

    @java.lang.Override
    public com.aurora.grpc.ProcessMetricMessage getDefaultInstanceForType() {
      return com.aurora.grpc.ProcessMetricMessage.getDefaultInstance();
    }

    @java.lang.Override
    public com.aurora.grpc.ProcessMetricMessage build() {
      com.aurora.grpc.ProcessMetricMessage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.aurora.grpc.ProcessMetricMessage buildPartial() {
      com.aurora.grpc.ProcessMetricMessage result = new com.aurora.grpc.ProcessMetricMessage(this);
      buildPartialRepeatedFields(result);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartialRepeatedFields(com.aurora.grpc.ProcessMetricMessage result) {
      if (metricsBuilder_ == null) {
        if (((bitField0_ & 0x00000010) != 0)) {
          metrics_ = java.util.Collections.unmodifiableList(metrics_);
          bitField0_ = (bitField0_ & ~0x00000010);
        }
        result.metrics_ = metrics_;
      } else {
        result.metrics_ = metricsBuilder_.build();
      }
    }

    private void buildPartial0(com.aurora.grpc.ProcessMetricMessage result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.placeId_ = placeId_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.ip_ = ip_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.time_ = time_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.pid_ = pid_;
      }
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.aurora.grpc.ProcessMetricMessage) {
        return mergeFrom((com.aurora.grpc.ProcessMetricMessage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.aurora.grpc.ProcessMetricMessage other) {
      if (other == com.aurora.grpc.ProcessMetricMessage.getDefaultInstance()) return this;
      if (other.getPlaceId() != 0) {
        setPlaceId(other.getPlaceId());
      }
      if (!other.getIp().isEmpty()) {
        ip_ = other.ip_;
        bitField0_ |= 0x00000002;
        onChanged();
      }
      if (other.getTime() != 0L) {
        setTime(other.getTime());
      }
      if (other.getPid() != 0) {
        setPid(other.getPid());
      }
      if (metricsBuilder_ == null) {
        if (!other.metrics_.isEmpty()) {
          if (metrics_.isEmpty()) {
            metrics_ = other.metrics_;
            bitField0_ = (bitField0_ & ~0x00000010);
          } else {
            ensureMetricsIsMutable();
            metrics_.addAll(other.metrics_);
          }
          onChanged();
        }
      } else {
        if (!other.metrics_.isEmpty()) {
          if (metricsBuilder_.isEmpty()) {
            metricsBuilder_.dispose();
            metricsBuilder_ = null;
            metrics_ = other.metrics_;
            bitField0_ = (bitField0_ & ~0x00000010);
            metricsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getMetricsFieldBuilder() : null;
          } else {
            metricsBuilder_.addAllMessages(other.metrics_);
          }
        }
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              placeId_ = input.readInt32();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 18: {
              ip_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 24: {
              time_ = input.readInt64();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            case 32: {
              pid_ = input.readInt32();
              bitField0_ |= 0x00000008;
              break;
            } // case 32
            case 42: {
              com.aurora.grpc.ProcessMetricItem m =
                  input.readMessage(
                      com.aurora.grpc.ProcessMetricItem.parser(),
                      extensionRegistry);
              if (metricsBuilder_ == null) {
                ensureMetricsIsMutable();
                metrics_.add(m);
              } else {
                metricsBuilder_.addMessage(m);
              }
              break;
            } // case 42
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private int placeId_ ;
    /**
     * <code>int32 placeId = 1;</code>
     * @return The placeId.
     */
    @java.lang.Override
    public int getPlaceId() {
      return placeId_;
    }
    /**
     * <code>int32 placeId = 1;</code>
     * @param value The placeId to set.
     * @return This builder for chaining.
     */
    public Builder setPlaceId(int value) {

      placeId_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>int32 placeId = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearPlaceId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      placeId_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object ip_ = "";
    /**
     * <code>string ip = 2;</code>
     * @return The ip.
     */
    public java.lang.String getIp() {
      java.lang.Object ref = ip_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        ip_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string ip = 2;</code>
     * @return The bytes for ip.
     */
    public com.google.protobuf.ByteString
        getIpBytes() {
      java.lang.Object ref = ip_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        ip_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string ip = 2;</code>
     * @param value The ip to set.
     * @return This builder for chaining.
     */
    public Builder setIp(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      ip_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>string ip = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearIp() {
      ip_ = getDefaultInstance().getIp();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <code>string ip = 2;</code>
     * @param value The bytes for ip to set.
     * @return This builder for chaining.
     */
    public Builder setIpBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      ip_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }

    private long time_ ;
    /**
     * <code>int64 time = 3;</code>
     * @return The time.
     */
    @java.lang.Override
    public long getTime() {
      return time_;
    }
    /**
     * <code>int64 time = 3;</code>
     * @param value The time to set.
     * @return This builder for chaining.
     */
    public Builder setTime(long value) {

      time_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>int64 time = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearTime() {
      bitField0_ = (bitField0_ & ~0x00000004);
      time_ = 0L;
      onChanged();
      return this;
    }

    private int pid_ ;
    /**
     * <code>int32 pid = 4;</code>
     * @return The pid.
     */
    @java.lang.Override
    public int getPid() {
      return pid_;
    }
    /**
     * <code>int32 pid = 4;</code>
     * @param value The pid to set.
     * @return This builder for chaining.
     */
    public Builder setPid(int value) {

      pid_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>int32 pid = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearPid() {
      bitField0_ = (bitField0_ & ~0x00000008);
      pid_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<com.aurora.grpc.ProcessMetricItem> metrics_ =
      java.util.Collections.emptyList();
    private void ensureMetricsIsMutable() {
      if (!((bitField0_ & 0x00000010) != 0)) {
        metrics_ = new java.util.ArrayList<com.aurora.grpc.ProcessMetricItem>(metrics_);
        bitField0_ |= 0x00000010;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.aurora.grpc.ProcessMetricItem, com.aurora.grpc.ProcessMetricItem.Builder, com.aurora.grpc.ProcessMetricItemOrBuilder> metricsBuilder_;

    /**
     * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
     */
    public java.util.List<com.aurora.grpc.ProcessMetricItem> getMetricsList() {
      if (metricsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(metrics_);
      } else {
        return metricsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
     */
    public int getMetricsCount() {
      if (metricsBuilder_ == null) {
        return metrics_.size();
      } else {
        return metricsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
     */
    public com.aurora.grpc.ProcessMetricItem getMetrics(int index) {
      if (metricsBuilder_ == null) {
        return metrics_.get(index);
      } else {
        return metricsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
     */
    public Builder setMetrics(
        int index, com.aurora.grpc.ProcessMetricItem value) {
      if (metricsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMetricsIsMutable();
        metrics_.set(index, value);
        onChanged();
      } else {
        metricsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
     */
    public Builder setMetrics(
        int index, com.aurora.grpc.ProcessMetricItem.Builder builderForValue) {
      if (metricsBuilder_ == null) {
        ensureMetricsIsMutable();
        metrics_.set(index, builderForValue.build());
        onChanged();
      } else {
        metricsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
     */
    public Builder addMetrics(com.aurora.grpc.ProcessMetricItem value) {
      if (metricsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMetricsIsMutable();
        metrics_.add(value);
        onChanged();
      } else {
        metricsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
     */
    public Builder addMetrics(
        int index, com.aurora.grpc.ProcessMetricItem value) {
      if (metricsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMetricsIsMutable();
        metrics_.add(index, value);
        onChanged();
      } else {
        metricsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
     */
    public Builder addMetrics(
        com.aurora.grpc.ProcessMetricItem.Builder builderForValue) {
      if (metricsBuilder_ == null) {
        ensureMetricsIsMutable();
        metrics_.add(builderForValue.build());
        onChanged();
      } else {
        metricsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
     */
    public Builder addMetrics(
        int index, com.aurora.grpc.ProcessMetricItem.Builder builderForValue) {
      if (metricsBuilder_ == null) {
        ensureMetricsIsMutable();
        metrics_.add(index, builderForValue.build());
        onChanged();
      } else {
        metricsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
     */
    public Builder addAllMetrics(
        java.lang.Iterable<? extends com.aurora.grpc.ProcessMetricItem> values) {
      if (metricsBuilder_ == null) {
        ensureMetricsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, metrics_);
        onChanged();
      } else {
        metricsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
     */
    public Builder clearMetrics() {
      if (metricsBuilder_ == null) {
        metrics_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000010);
        onChanged();
      } else {
        metricsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
     */
    public Builder removeMetrics(int index) {
      if (metricsBuilder_ == null) {
        ensureMetricsIsMutable();
        metrics_.remove(index);
        onChanged();
      } else {
        metricsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
     */
    public com.aurora.grpc.ProcessMetricItem.Builder getMetricsBuilder(
        int index) {
      return getMetricsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
     */
    public com.aurora.grpc.ProcessMetricItemOrBuilder getMetricsOrBuilder(
        int index) {
      if (metricsBuilder_ == null) {
        return metrics_.get(index);  } else {
        return metricsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
     */
    public java.util.List<? extends com.aurora.grpc.ProcessMetricItemOrBuilder> 
         getMetricsOrBuilderList() {
      if (metricsBuilder_ != null) {
        return metricsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(metrics_);
      }
    }
    /**
     * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
     */
    public com.aurora.grpc.ProcessMetricItem.Builder addMetricsBuilder() {
      return getMetricsFieldBuilder().addBuilder(
          com.aurora.grpc.ProcessMetricItem.getDefaultInstance());
    }
    /**
     * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
     */
    public com.aurora.grpc.ProcessMetricItem.Builder addMetricsBuilder(
        int index) {
      return getMetricsFieldBuilder().addBuilder(
          index, com.aurora.grpc.ProcessMetricItem.getDefaultInstance());
    }
    /**
     * <code>repeated .aurora.ProcessMetricItem metrics = 5;</code>
     */
    public java.util.List<com.aurora.grpc.ProcessMetricItem.Builder> 
         getMetricsBuilderList() {
      return getMetricsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.aurora.grpc.ProcessMetricItem, com.aurora.grpc.ProcessMetricItem.Builder, com.aurora.grpc.ProcessMetricItemOrBuilder> 
        getMetricsFieldBuilder() {
      if (metricsBuilder_ == null) {
        metricsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.aurora.grpc.ProcessMetricItem, com.aurora.grpc.ProcessMetricItem.Builder, com.aurora.grpc.ProcessMetricItemOrBuilder>(
                metrics_,
                ((bitField0_ & 0x00000010) != 0),
                getParentForChildren(),
                isClean());
        metrics_ = null;
      }
      return metricsBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:aurora.ProcessMetricMessage)
  }

  // @@protoc_insertion_point(class_scope:aurora.ProcessMetricMessage)
  private static final com.aurora.grpc.ProcessMetricMessage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.aurora.grpc.ProcessMetricMessage();
  }

  public static com.aurora.grpc.ProcessMetricMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ProcessMetricMessage>
      PARSER = new com.google.protobuf.AbstractParser<ProcessMetricMessage>() {
    @java.lang.Override
    public ProcessMetricMessage parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<ProcessMetricMessage> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ProcessMetricMessage> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.aurora.grpc.ProcessMetricMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

