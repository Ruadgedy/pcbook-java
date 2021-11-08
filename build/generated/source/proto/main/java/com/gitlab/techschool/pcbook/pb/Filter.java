// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: filter_message.proto

package com.gitlab.techschool.pcbook.pb;

/**
 * Protobuf type {@code techschool.pcbook.Filter}
 */
public final class Filter extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:techschool.pcbook.Filter)
    FilterOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Filter.newBuilder() to construct.
  private Filter(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Filter() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Filter();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Filter(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 9: {

            maxPriceUsd_ = input.readDouble();
            break;
          }
          case 16: {

            minCpuCores_ = input.readUInt32();
            break;
          }
          case 25: {

            minCpuGhz_ = input.readDouble();
            break;
          }
          case 34: {
            com.gitlab.techschool.pcbook.pb.Memory.Builder subBuilder = null;
            if (minRam_ != null) {
              subBuilder = minRam_.toBuilder();
            }
            minRam_ = input.readMessage(com.gitlab.techschool.pcbook.pb.Memory.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(minRam_);
              minRam_ = subBuilder.buildPartial();
            }

            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.gitlab.techschool.pcbook.pb.FilterMessage.internal_static_techschool_pcbook_Filter_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.gitlab.techschool.pcbook.pb.FilterMessage.internal_static_techschool_pcbook_Filter_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.gitlab.techschool.pcbook.pb.Filter.class, com.gitlab.techschool.pcbook.pb.Filter.Builder.class);
  }

  public static final int MAX_PRICE_USD_FIELD_NUMBER = 1;
  private double maxPriceUsd_;
  /**
   * <code>double max_price_usd = 1;</code>
   * @return The maxPriceUsd.
   */
  @java.lang.Override
  public double getMaxPriceUsd() {
    return maxPriceUsd_;
  }

  public static final int MIN_CPU_CORES_FIELD_NUMBER = 2;
  private int minCpuCores_;
  /**
   * <code>uint32 min_cpu_cores = 2;</code>
   * @return The minCpuCores.
   */
  @java.lang.Override
  public int getMinCpuCores() {
    return minCpuCores_;
  }

  public static final int MIN_CPU_GHZ_FIELD_NUMBER = 3;
  private double minCpuGhz_;
  /**
   * <code>double min_cpu_ghz = 3;</code>
   * @return The minCpuGhz.
   */
  @java.lang.Override
  public double getMinCpuGhz() {
    return minCpuGhz_;
  }

  public static final int MIN_RAM_FIELD_NUMBER = 4;
  private com.gitlab.techschool.pcbook.pb.Memory minRam_;
  /**
   * <code>.techschool.pcbook.Memory min_ram = 4;</code>
   * @return Whether the minRam field is set.
   */
  @java.lang.Override
  public boolean hasMinRam() {
    return minRam_ != null;
  }
  /**
   * <code>.techschool.pcbook.Memory min_ram = 4;</code>
   * @return The minRam.
   */
  @java.lang.Override
  public com.gitlab.techschool.pcbook.pb.Memory getMinRam() {
    return minRam_ == null ? com.gitlab.techschool.pcbook.pb.Memory.getDefaultInstance() : minRam_;
  }
  /**
   * <code>.techschool.pcbook.Memory min_ram = 4;</code>
   */
  @java.lang.Override
  public com.gitlab.techschool.pcbook.pb.MemoryOrBuilder getMinRamOrBuilder() {
    return getMinRam();
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
    if (maxPriceUsd_ != 0D) {
      output.writeDouble(1, maxPriceUsd_);
    }
    if (minCpuCores_ != 0) {
      output.writeUInt32(2, minCpuCores_);
    }
    if (minCpuGhz_ != 0D) {
      output.writeDouble(3, minCpuGhz_);
    }
    if (minRam_ != null) {
      output.writeMessage(4, getMinRam());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (maxPriceUsd_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(1, maxPriceUsd_);
    }
    if (minCpuCores_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt32Size(2, minCpuCores_);
    }
    if (minCpuGhz_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(3, minCpuGhz_);
    }
    if (minRam_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, getMinRam());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.gitlab.techschool.pcbook.pb.Filter)) {
      return super.equals(obj);
    }
    com.gitlab.techschool.pcbook.pb.Filter other = (com.gitlab.techschool.pcbook.pb.Filter) obj;

    if (java.lang.Double.doubleToLongBits(getMaxPriceUsd())
        != java.lang.Double.doubleToLongBits(
            other.getMaxPriceUsd())) return false;
    if (getMinCpuCores()
        != other.getMinCpuCores()) return false;
    if (java.lang.Double.doubleToLongBits(getMinCpuGhz())
        != java.lang.Double.doubleToLongBits(
            other.getMinCpuGhz())) return false;
    if (hasMinRam() != other.hasMinRam()) return false;
    if (hasMinRam()) {
      if (!getMinRam()
          .equals(other.getMinRam())) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + MAX_PRICE_USD_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getMaxPriceUsd()));
    hash = (37 * hash) + MIN_CPU_CORES_FIELD_NUMBER;
    hash = (53 * hash) + getMinCpuCores();
    hash = (37 * hash) + MIN_CPU_GHZ_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getMinCpuGhz()));
    if (hasMinRam()) {
      hash = (37 * hash) + MIN_RAM_FIELD_NUMBER;
      hash = (53 * hash) + getMinRam().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.gitlab.techschool.pcbook.pb.Filter parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.gitlab.techschool.pcbook.pb.Filter parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.gitlab.techschool.pcbook.pb.Filter parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.gitlab.techschool.pcbook.pb.Filter parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.gitlab.techschool.pcbook.pb.Filter parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.gitlab.techschool.pcbook.pb.Filter parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.gitlab.techschool.pcbook.pb.Filter parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.gitlab.techschool.pcbook.pb.Filter parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.gitlab.techschool.pcbook.pb.Filter parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.gitlab.techschool.pcbook.pb.Filter parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.gitlab.techschool.pcbook.pb.Filter parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.gitlab.techschool.pcbook.pb.Filter parseFrom(
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
  public static Builder newBuilder(com.gitlab.techschool.pcbook.pb.Filter prototype) {
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
   * Protobuf type {@code techschool.pcbook.Filter}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:techschool.pcbook.Filter)
      com.gitlab.techschool.pcbook.pb.FilterOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.gitlab.techschool.pcbook.pb.FilterMessage.internal_static_techschool_pcbook_Filter_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gitlab.techschool.pcbook.pb.FilterMessage.internal_static_techschool_pcbook_Filter_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gitlab.techschool.pcbook.pb.Filter.class, com.gitlab.techschool.pcbook.pb.Filter.Builder.class);
    }

    // Construct using com.gitlab.techschool.pcbook.pb.Filter.newBuilder()
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
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      maxPriceUsd_ = 0D;

      minCpuCores_ = 0;

      minCpuGhz_ = 0D;

      if (minRamBuilder_ == null) {
        minRam_ = null;
      } else {
        minRam_ = null;
        minRamBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.gitlab.techschool.pcbook.pb.FilterMessage.internal_static_techschool_pcbook_Filter_descriptor;
    }

    @java.lang.Override
    public com.gitlab.techschool.pcbook.pb.Filter getDefaultInstanceForType() {
      return com.gitlab.techschool.pcbook.pb.Filter.getDefaultInstance();
    }

    @java.lang.Override
    public com.gitlab.techschool.pcbook.pb.Filter build() {
      com.gitlab.techschool.pcbook.pb.Filter result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.gitlab.techschool.pcbook.pb.Filter buildPartial() {
      com.gitlab.techschool.pcbook.pb.Filter result = new com.gitlab.techschool.pcbook.pb.Filter(this);
      result.maxPriceUsd_ = maxPriceUsd_;
      result.minCpuCores_ = minCpuCores_;
      result.minCpuGhz_ = minCpuGhz_;
      if (minRamBuilder_ == null) {
        result.minRam_ = minRam_;
      } else {
        result.minRam_ = minRamBuilder_.build();
      }
      onBuilt();
      return result;
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
      if (other instanceof com.gitlab.techschool.pcbook.pb.Filter) {
        return mergeFrom((com.gitlab.techschool.pcbook.pb.Filter)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.gitlab.techschool.pcbook.pb.Filter other) {
      if (other == com.gitlab.techschool.pcbook.pb.Filter.getDefaultInstance()) return this;
      if (other.getMaxPriceUsd() != 0D) {
        setMaxPriceUsd(other.getMaxPriceUsd());
      }
      if (other.getMinCpuCores() != 0) {
        setMinCpuCores(other.getMinCpuCores());
      }
      if (other.getMinCpuGhz() != 0D) {
        setMinCpuGhz(other.getMinCpuGhz());
      }
      if (other.hasMinRam()) {
        mergeMinRam(other.getMinRam());
      }
      this.mergeUnknownFields(other.unknownFields);
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
      com.gitlab.techschool.pcbook.pb.Filter parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.gitlab.techschool.pcbook.pb.Filter) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private double maxPriceUsd_ ;
    /**
     * <code>double max_price_usd = 1;</code>
     * @return The maxPriceUsd.
     */
    @java.lang.Override
    public double getMaxPriceUsd() {
      return maxPriceUsd_;
    }
    /**
     * <code>double max_price_usd = 1;</code>
     * @param value The maxPriceUsd to set.
     * @return This builder for chaining.
     */
    public Builder setMaxPriceUsd(double value) {
      
      maxPriceUsd_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double max_price_usd = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearMaxPriceUsd() {
      
      maxPriceUsd_ = 0D;
      onChanged();
      return this;
    }

    private int minCpuCores_ ;
    /**
     * <code>uint32 min_cpu_cores = 2;</code>
     * @return The minCpuCores.
     */
    @java.lang.Override
    public int getMinCpuCores() {
      return minCpuCores_;
    }
    /**
     * <code>uint32 min_cpu_cores = 2;</code>
     * @param value The minCpuCores to set.
     * @return This builder for chaining.
     */
    public Builder setMinCpuCores(int value) {
      
      minCpuCores_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>uint32 min_cpu_cores = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearMinCpuCores() {
      
      minCpuCores_ = 0;
      onChanged();
      return this;
    }

    private double minCpuGhz_ ;
    /**
     * <code>double min_cpu_ghz = 3;</code>
     * @return The minCpuGhz.
     */
    @java.lang.Override
    public double getMinCpuGhz() {
      return minCpuGhz_;
    }
    /**
     * <code>double min_cpu_ghz = 3;</code>
     * @param value The minCpuGhz to set.
     * @return This builder for chaining.
     */
    public Builder setMinCpuGhz(double value) {
      
      minCpuGhz_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double min_cpu_ghz = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearMinCpuGhz() {
      
      minCpuGhz_ = 0D;
      onChanged();
      return this;
    }

    private com.gitlab.techschool.pcbook.pb.Memory minRam_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.gitlab.techschool.pcbook.pb.Memory, com.gitlab.techschool.pcbook.pb.Memory.Builder, com.gitlab.techschool.pcbook.pb.MemoryOrBuilder> minRamBuilder_;
    /**
     * <code>.techschool.pcbook.Memory min_ram = 4;</code>
     * @return Whether the minRam field is set.
     */
    public boolean hasMinRam() {
      return minRamBuilder_ != null || minRam_ != null;
    }
    /**
     * <code>.techschool.pcbook.Memory min_ram = 4;</code>
     * @return The minRam.
     */
    public com.gitlab.techschool.pcbook.pb.Memory getMinRam() {
      if (minRamBuilder_ == null) {
        return minRam_ == null ? com.gitlab.techschool.pcbook.pb.Memory.getDefaultInstance() : minRam_;
      } else {
        return minRamBuilder_.getMessage();
      }
    }
    /**
     * <code>.techschool.pcbook.Memory min_ram = 4;</code>
     */
    public Builder setMinRam(com.gitlab.techschool.pcbook.pb.Memory value) {
      if (minRamBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        minRam_ = value;
        onChanged();
      } else {
        minRamBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.techschool.pcbook.Memory min_ram = 4;</code>
     */
    public Builder setMinRam(
        com.gitlab.techschool.pcbook.pb.Memory.Builder builderForValue) {
      if (minRamBuilder_ == null) {
        minRam_ = builderForValue.build();
        onChanged();
      } else {
        minRamBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.techschool.pcbook.Memory min_ram = 4;</code>
     */
    public Builder mergeMinRam(com.gitlab.techschool.pcbook.pb.Memory value) {
      if (minRamBuilder_ == null) {
        if (minRam_ != null) {
          minRam_ =
            com.gitlab.techschool.pcbook.pb.Memory.newBuilder(minRam_).mergeFrom(value).buildPartial();
        } else {
          minRam_ = value;
        }
        onChanged();
      } else {
        minRamBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.techschool.pcbook.Memory min_ram = 4;</code>
     */
    public Builder clearMinRam() {
      if (minRamBuilder_ == null) {
        minRam_ = null;
        onChanged();
      } else {
        minRam_ = null;
        minRamBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.techschool.pcbook.Memory min_ram = 4;</code>
     */
    public com.gitlab.techschool.pcbook.pb.Memory.Builder getMinRamBuilder() {
      
      onChanged();
      return getMinRamFieldBuilder().getBuilder();
    }
    /**
     * <code>.techschool.pcbook.Memory min_ram = 4;</code>
     */
    public com.gitlab.techschool.pcbook.pb.MemoryOrBuilder getMinRamOrBuilder() {
      if (minRamBuilder_ != null) {
        return minRamBuilder_.getMessageOrBuilder();
      } else {
        return minRam_ == null ?
            com.gitlab.techschool.pcbook.pb.Memory.getDefaultInstance() : minRam_;
      }
    }
    /**
     * <code>.techschool.pcbook.Memory min_ram = 4;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.gitlab.techschool.pcbook.pb.Memory, com.gitlab.techschool.pcbook.pb.Memory.Builder, com.gitlab.techschool.pcbook.pb.MemoryOrBuilder> 
        getMinRamFieldBuilder() {
      if (minRamBuilder_ == null) {
        minRamBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.gitlab.techschool.pcbook.pb.Memory, com.gitlab.techschool.pcbook.pb.Memory.Builder, com.gitlab.techschool.pcbook.pb.MemoryOrBuilder>(
                getMinRam(),
                getParentForChildren(),
                isClean());
        minRam_ = null;
      }
      return minRamBuilder_;
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


    // @@protoc_insertion_point(builder_scope:techschool.pcbook.Filter)
  }

  // @@protoc_insertion_point(class_scope:techschool.pcbook.Filter)
  private static final com.gitlab.techschool.pcbook.pb.Filter DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.gitlab.techschool.pcbook.pb.Filter();
  }

  public static com.gitlab.techschool.pcbook.pb.Filter getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Filter>
      PARSER = new com.google.protobuf.AbstractParser<Filter>() {
    @java.lang.Override
    public Filter parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Filter(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Filter> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Filter> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.gitlab.techschool.pcbook.pb.Filter getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

