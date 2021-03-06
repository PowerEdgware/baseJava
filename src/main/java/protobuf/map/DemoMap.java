// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: DemoMap.proto

package protobuf.map;

public final class DemoMap {
  private DemoMap() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface MessageReqOrBuilder extends
      // @@protoc_insertion_point(interface_extends:protobuf.map.MessageReq)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * </pre>
     *
     * <code>string reqId = 1;</code>
     */
    java.lang.String getReqId();
    /**
     * <pre>
     * </pre>
     *
     * <code>string reqId = 1;</code>
     */
    com.google.protobuf.ByteString
        getReqIdBytes();

    /**
     * <code>int32 ID = 2;</code>
     */
    int getID();

    /**
     * <pre>
     * </pre>
     *
     * <code>map&lt;string, string&gt; paramMap = 3;</code>
     */
    int getParamMapCount();
    /**
     * <pre>
     * </pre>
     *
     * <code>map&lt;string, string&gt; paramMap = 3;</code>
     */
    boolean containsParamMap(
        java.lang.String key);
    /**
     * Use {@link #getParamMapMap()} instead.
     */
    @java.lang.Deprecated
    java.util.Map<java.lang.String, java.lang.String>
    getParamMap();
    /**
     * <pre>
     * </pre>
     *
     * <code>map&lt;string, string&gt; paramMap = 3;</code>
     */
    java.util.Map<java.lang.String, java.lang.String>
    getParamMapMap();
    /**
     * <pre>
     * </pre>
     *
     * <code>map&lt;string, string&gt; paramMap = 3;</code>
     */

    java.lang.String getParamMapOrDefault(
        java.lang.String key,
        java.lang.String defaultValue);
    /**
     * <pre>
     * </pre>
     *
     * <code>map&lt;string, string&gt; paramMap = 3;</code>
     */

    java.lang.String getParamMapOrThrow(
        java.lang.String key);
  }
  /**
   * Protobuf type {@code protobuf.map.MessageReq}
   */
  public  static final class MessageReq extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:protobuf.map.MessageReq)
      MessageReqOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use MessageReq.newBuilder() to construct.
    private MessageReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private MessageReq() {
      reqId_ = "";
      iD_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private MessageReq(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
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
            default: {
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              reqId_ = s;
              break;
            }
            case 16: {

              iD_ = input.readInt32();
              break;
            }
            case 26: {
              if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
                paramMap_ = com.google.protobuf.MapField.newMapField(
                    ParamMapDefaultEntryHolder.defaultEntry);
                mutable_bitField0_ |= 0x00000004;
              }
              com.google.protobuf.MapEntry<java.lang.String, java.lang.String>
              paramMap__ = input.readMessage(
                  ParamMapDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
              paramMap_.getMutableMap().put(
                  paramMap__.getKey(), paramMap__.getValue());
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
      return protobuf.map.DemoMap.internal_static_protobuf_map_MessageReq_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 3:
          return internalGetParamMap();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return protobuf.map.DemoMap.internal_static_protobuf_map_MessageReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              protobuf.map.DemoMap.MessageReq.class, protobuf.map.DemoMap.MessageReq.Builder.class);
    }

    private int bitField0_;
    public static final int REQID_FIELD_NUMBER = 1;
    private volatile java.lang.Object reqId_;
    /**
     * <pre>
     * </pre>
     *
     * <code>string reqId = 1;</code>
     */
    public java.lang.String getReqId() {
      java.lang.Object ref = reqId_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        reqId_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>string reqId = 1;</code>
     */
    public com.google.protobuf.ByteString
        getReqIdBytes() {
      java.lang.Object ref = reqId_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        reqId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int ID_FIELD_NUMBER = 2;
    private int iD_;
    /**
     * <code>int32 ID = 2;</code>
     */
    public int getID() {
      return iD_;
    }

    public static final int PARAMMAP_FIELD_NUMBER = 3;
    private static final class ParamMapDefaultEntryHolder {
      static final com.google.protobuf.MapEntry<
          java.lang.String, java.lang.String> defaultEntry =
              com.google.protobuf.MapEntry
              .<java.lang.String, java.lang.String>newDefaultInstance(
                  protobuf.map.DemoMap.internal_static_protobuf_map_MessageReq_ParamMapEntry_descriptor, 
                  com.google.protobuf.WireFormat.FieldType.STRING,
                  "",
                  com.google.protobuf.WireFormat.FieldType.STRING,
                  "");
    }
    private com.google.protobuf.MapField<
        java.lang.String, java.lang.String> paramMap_;
    private com.google.protobuf.MapField<java.lang.String, java.lang.String>
    internalGetParamMap() {
      if (paramMap_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            ParamMapDefaultEntryHolder.defaultEntry);
      }
      return paramMap_;
    }

    public int getParamMapCount() {
      return internalGetParamMap().getMap().size();
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>map&lt;string, string&gt; paramMap = 3;</code>
     */

    public boolean containsParamMap(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      return internalGetParamMap().getMap().containsKey(key);
    }
    /**
     * Use {@link #getParamMapMap()} instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, java.lang.String> getParamMap() {
      return getParamMapMap();
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>map&lt;string, string&gt; paramMap = 3;</code>
     */

    public java.util.Map<java.lang.String, java.lang.String> getParamMapMap() {
      return internalGetParamMap().getMap();
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>map&lt;string, string&gt; paramMap = 3;</code>
     */

    public java.lang.String getParamMapOrDefault(
        java.lang.String key,
        java.lang.String defaultValue) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      java.util.Map<java.lang.String, java.lang.String> map =
          internalGetParamMap().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>map&lt;string, string&gt; paramMap = 3;</code>
     */

    public java.lang.String getParamMapOrThrow(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      java.util.Map<java.lang.String, java.lang.String> map =
          internalGetParamMap().getMap();
      if (!map.containsKey(key)) {
        throw new java.lang.IllegalArgumentException();
      }
      return map.get(key);
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
      if (!getReqIdBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, reqId_);
      }
      if (iD_ != 0) {
        output.writeInt32(2, iD_);
      }
      com.google.protobuf.GeneratedMessageV3
        .serializeStringMapTo(
          output,
          internalGetParamMap(),
          ParamMapDefaultEntryHolder.defaultEntry,
          3);
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getReqIdBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, reqId_);
      }
      if (iD_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, iD_);
      }
      for (java.util.Map.Entry<java.lang.String, java.lang.String> entry
           : internalGetParamMap().getMap().entrySet()) {
        com.google.protobuf.MapEntry<java.lang.String, java.lang.String>
        paramMap__ = ParamMapDefaultEntryHolder.defaultEntry.newBuilderForType()
            .setKey(entry.getKey())
            .setValue(entry.getValue())
            .build();
        size += com.google.protobuf.CodedOutputStream
            .computeMessageSize(3, paramMap__);
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
      if (!(obj instanceof protobuf.map.DemoMap.MessageReq)) {
        return super.equals(obj);
      }
      protobuf.map.DemoMap.MessageReq other = (protobuf.map.DemoMap.MessageReq) obj;

      boolean result = true;
      result = result && getReqId()
          .equals(other.getReqId());
      result = result && (getID()
          == other.getID());
      result = result && internalGetParamMap().equals(
          other.internalGetParamMap());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + REQID_FIELD_NUMBER;
      hash = (53 * hash) + getReqId().hashCode();
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + getID();
      if (!internalGetParamMap().getMap().isEmpty()) {
        hash = (37 * hash) + PARAMMAP_FIELD_NUMBER;
        hash = (53 * hash) + internalGetParamMap().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static protobuf.map.DemoMap.MessageReq parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protobuf.map.DemoMap.MessageReq parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protobuf.map.DemoMap.MessageReq parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protobuf.map.DemoMap.MessageReq parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protobuf.map.DemoMap.MessageReq parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protobuf.map.DemoMap.MessageReq parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protobuf.map.DemoMap.MessageReq parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static protobuf.map.DemoMap.MessageReq parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static protobuf.map.DemoMap.MessageReq parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static protobuf.map.DemoMap.MessageReq parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static protobuf.map.DemoMap.MessageReq parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static protobuf.map.DemoMap.MessageReq parseFrom(
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
    public static Builder newBuilder(protobuf.map.DemoMap.MessageReq prototype) {
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
     * Protobuf type {@code protobuf.map.MessageReq}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:protobuf.map.MessageReq)
        protobuf.map.DemoMap.MessageReqOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return protobuf.map.DemoMap.internal_static_protobuf_map_MessageReq_descriptor;
      }

      @SuppressWarnings({"rawtypes"})
      protected com.google.protobuf.MapField internalGetMapField(
          int number) {
        switch (number) {
          case 3:
            return internalGetParamMap();
          default:
            throw new RuntimeException(
                "Invalid map field number: " + number);
        }
      }
      @SuppressWarnings({"rawtypes"})
      protected com.google.protobuf.MapField internalGetMutableMapField(
          int number) {
        switch (number) {
          case 3:
            return internalGetMutableParamMap();
          default:
            throw new RuntimeException(
                "Invalid map field number: " + number);
        }
      }
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return protobuf.map.DemoMap.internal_static_protobuf_map_MessageReq_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                protobuf.map.DemoMap.MessageReq.class, protobuf.map.DemoMap.MessageReq.Builder.class);
      }

      // Construct using protobuf.map.DemoMap.MessageReq.newBuilder()
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
      public Builder clear() {
        super.clear();
        reqId_ = "";

        iD_ = 0;

        internalGetMutableParamMap().clear();
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return protobuf.map.DemoMap.internal_static_protobuf_map_MessageReq_descriptor;
      }

      public protobuf.map.DemoMap.MessageReq getDefaultInstanceForType() {
        return protobuf.map.DemoMap.MessageReq.getDefaultInstance();
      }

      public protobuf.map.DemoMap.MessageReq build() {
        protobuf.map.DemoMap.MessageReq result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public protobuf.map.DemoMap.MessageReq buildPartial() {
        protobuf.map.DemoMap.MessageReq result = new protobuf.map.DemoMap.MessageReq(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        result.reqId_ = reqId_;
        result.iD_ = iD_;
        result.paramMap_ = internalGetParamMap();
        result.paramMap_.makeImmutable();
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
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
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof protobuf.map.DemoMap.MessageReq) {
          return mergeFrom((protobuf.map.DemoMap.MessageReq)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(protobuf.map.DemoMap.MessageReq other) {
        if (other == protobuf.map.DemoMap.MessageReq.getDefaultInstance()) return this;
        if (!other.getReqId().isEmpty()) {
          reqId_ = other.reqId_;
          onChanged();
        }
        if (other.getID() != 0) {
          setID(other.getID());
        }
        internalGetMutableParamMap().mergeFrom(
            other.internalGetParamMap());
        this.mergeUnknownFields(other.unknownFields);
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
        protobuf.map.DemoMap.MessageReq parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (protobuf.map.DemoMap.MessageReq) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.lang.Object reqId_ = "";
      /**
       * <pre>
       * </pre>
       *
       * <code>string reqId = 1;</code>
       */
      public java.lang.String getReqId() {
        java.lang.Object ref = reqId_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          reqId_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * </pre>
       *
       * <code>string reqId = 1;</code>
       */
      public com.google.protobuf.ByteString
          getReqIdBytes() {
        java.lang.Object ref = reqId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          reqId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * </pre>
       *
       * <code>string reqId = 1;</code>
       */
      public Builder setReqId(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        reqId_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * </pre>
       *
       * <code>string reqId = 1;</code>
       */
      public Builder clearReqId() {
        
        reqId_ = getDefaultInstance().getReqId();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * </pre>
       *
       * <code>string reqId = 1;</code>
       */
      public Builder setReqIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        reqId_ = value;
        onChanged();
        return this;
      }

      private int iD_ ;
      /**
       * <code>int32 ID = 2;</code>
       */
      public int getID() {
        return iD_;
      }
      /**
       * <code>int32 ID = 2;</code>
       */
      public Builder setID(int value) {
        
        iD_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 ID = 2;</code>
       */
      public Builder clearID() {
        
        iD_ = 0;
        onChanged();
        return this;
      }

      private com.google.protobuf.MapField<
          java.lang.String, java.lang.String> paramMap_;
      private com.google.protobuf.MapField<java.lang.String, java.lang.String>
      internalGetParamMap() {
        if (paramMap_ == null) {
          return com.google.protobuf.MapField.emptyMapField(
              ParamMapDefaultEntryHolder.defaultEntry);
        }
        return paramMap_;
      }
      private com.google.protobuf.MapField<java.lang.String, java.lang.String>
      internalGetMutableParamMap() {
        onChanged();;
        if (paramMap_ == null) {
          paramMap_ = com.google.protobuf.MapField.newMapField(
              ParamMapDefaultEntryHolder.defaultEntry);
        }
        if (!paramMap_.isMutable()) {
          paramMap_ = paramMap_.copy();
        }
        return paramMap_;
      }

      public int getParamMapCount() {
        return internalGetParamMap().getMap().size();
      }
      /**
       * <pre>
       * </pre>
       *
       * <code>map&lt;string, string&gt; paramMap = 3;</code>
       */

      public boolean containsParamMap(
          java.lang.String key) {
        if (key == null) { throw new java.lang.NullPointerException(); }
        return internalGetParamMap().getMap().containsKey(key);
      }
      /**
       * Use {@link #getParamMapMap()} instead.
       */
      @java.lang.Deprecated
      public java.util.Map<java.lang.String, java.lang.String> getParamMap() {
        return getParamMapMap();
      }
      /**
       * <pre>
       * </pre>
       *
       * <code>map&lt;string, string&gt; paramMap = 3;</code>
       */

      public java.util.Map<java.lang.String, java.lang.String> getParamMapMap() {
        return internalGetParamMap().getMap();
      }
      /**
       * <pre>
       * </pre>
       *
       * <code>map&lt;string, string&gt; paramMap = 3;</code>
       */

      public java.lang.String getParamMapOrDefault(
          java.lang.String key,
          java.lang.String defaultValue) {
        if (key == null) { throw new java.lang.NullPointerException(); }
        java.util.Map<java.lang.String, java.lang.String> map =
            internalGetParamMap().getMap();
        return map.containsKey(key) ? map.get(key) : defaultValue;
      }
      /**
       * <pre>
       * </pre>
       *
       * <code>map&lt;string, string&gt; paramMap = 3;</code>
       */

      public java.lang.String getParamMapOrThrow(
          java.lang.String key) {
        if (key == null) { throw new java.lang.NullPointerException(); }
        java.util.Map<java.lang.String, java.lang.String> map =
            internalGetParamMap().getMap();
        if (!map.containsKey(key)) {
          throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
      }

      public Builder clearParamMap() {
        internalGetMutableParamMap().getMutableMap()
            .clear();
        return this;
      }
      /**
       * <pre>
       * </pre>
       *
       * <code>map&lt;string, string&gt; paramMap = 3;</code>
       */

      public Builder removeParamMap(
          java.lang.String key) {
        if (key == null) { throw new java.lang.NullPointerException(); }
        internalGetMutableParamMap().getMutableMap()
            .remove(key);
        return this;
      }
      /**
       * Use alternate mutation accessors instead.
       */
      @java.lang.Deprecated
      public java.util.Map<java.lang.String, java.lang.String>
      getMutableParamMap() {
        return internalGetMutableParamMap().getMutableMap();
      }
      /**
       * <pre>
       * </pre>
       *
       * <code>map&lt;string, string&gt; paramMap = 3;</code>
       */
      public Builder putParamMap(
          java.lang.String key,
          java.lang.String value) {
        if (key == null) { throw new java.lang.NullPointerException(); }
        if (value == null) { throw new java.lang.NullPointerException(); }
        internalGetMutableParamMap().getMutableMap()
            .put(key, value);
        return this;
      }
      /**
       * <pre>
       * </pre>
       *
       * <code>map&lt;string, string&gt; paramMap = 3;</code>
       */

      public Builder putAllParamMap(
          java.util.Map<java.lang.String, java.lang.String> values) {
        internalGetMutableParamMap().getMutableMap()
            .putAll(values);
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:protobuf.map.MessageReq)
    }

    // @@protoc_insertion_point(class_scope:protobuf.map.MessageReq)
    private static final protobuf.map.DemoMap.MessageReq DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new protobuf.map.DemoMap.MessageReq();
    }

    public static protobuf.map.DemoMap.MessageReq getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<MessageReq>
        PARSER = new com.google.protobuf.AbstractParser<MessageReq>() {
      public MessageReq parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new MessageReq(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<MessageReq> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<MessageReq> getParserForType() {
      return PARSER;
    }

    public protobuf.map.DemoMap.MessageReq getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_protobuf_map_MessageReq_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_protobuf_map_MessageReq_fieldAccessorTable;
  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_protobuf_map_MessageReq_ParamMapEntry_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_protobuf_map_MessageReq_ParamMapEntry_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rDemoMap.proto\022\014protobuf.map\"\222\001\n\nMessag" +
      "eReq\022\r\n\005reqId\030\001 \001(\t\022\n\n\002ID\030\002 \001(\005\0228\n\010param" +
      "Map\030\003 \003(\0132&.protobuf.map.MessageReq.Para" +
      "mMapEntry\032/\n\rParamMapEntry\022\013\n\003key\030\001 \001(\t\022" +
      "\r\n\005value\030\002 \001(\t:\0028\001B\002H\001b\006proto3"
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
    internal_static_protobuf_map_MessageReq_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_protobuf_map_MessageReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_protobuf_map_MessageReq_descriptor,
        new java.lang.String[] { "ReqId", "ID", "ParamMap", });
    internal_static_protobuf_map_MessageReq_ParamMapEntry_descriptor =
      internal_static_protobuf_map_MessageReq_descriptor.getNestedTypes().get(0);
    internal_static_protobuf_map_MessageReq_ParamMapEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_protobuf_map_MessageReq_ParamMapEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
