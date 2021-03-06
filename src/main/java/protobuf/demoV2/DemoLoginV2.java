// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: DemoLoginV2.proto

package protobuf.demoV2;

public final class DemoLoginV2 {
  private DemoLoginV2() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface DemoReqV2OrBuilder extends
      // @@protoc_insertion_point(interface_extends:protobuf.demoV2.DemoReqV2)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     *账号id
     * </pre>
     *
     * <code>sint32 acctID = 2;</code>
     */
    int getAcctID();

    /**
     * <pre>
     *Required fields are not allowed in proto3.
     * </pre>
     *
     * <code>string name = 3;</code>
     */
    java.lang.String getName();
    /**
     * <pre>
     *Required fields are not allowed in proto3.
     * </pre>
     *
     * <code>string name = 3;</code>
     */
    com.google.protobuf.ByteString
        getNameBytes();

    /**
     * <pre>
     * 'optional' labels are disallowed in the Proto3 syntax.as fields are 'optional' by default.
     * </pre>
     *
     * <code>bool suc = 4;</code>
     */
    boolean getSuc();

    /**
     * <code>string email = 1;</code>
     */
    java.lang.String getEmail();
    /**
     * <code>string email = 1;</code>
     */
    com.google.protobuf.ByteString
        getEmailBytes();
  }
  /**
   * Protobuf type {@code protobuf.demoV2.DemoReqV2}
   */
  public  static final class DemoReqV2 extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:protobuf.demoV2.DemoReqV2)
      DemoReqV2OrBuilder {
  private static final long serialVersionUID = 0L;
    // Use DemoReqV2.newBuilder() to construct.
    private DemoReqV2(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private DemoReqV2() {
      acctID_ = 0;
      name_ = "";
      suc_ = false;
      email_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private DemoReqV2(
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

              email_ = s;
              break;
            }
            case 16: {

              acctID_ = input.readSInt32();
              break;
            }
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();

              name_ = s;
              break;
            }
            case 32: {

              suc_ = input.readBool();
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
      return protobuf.demoV2.DemoLoginV2.internal_static_protobuf_demoV2_DemoReqV2_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return protobuf.demoV2.DemoLoginV2.internal_static_protobuf_demoV2_DemoReqV2_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              protobuf.demoV2.DemoLoginV2.DemoReqV2.class, protobuf.demoV2.DemoLoginV2.DemoReqV2.Builder.class);
    }

    public static final int ACCTID_FIELD_NUMBER = 2;
    private int acctID_;
    /**
     * <pre>
     *账号id
     * </pre>
     *
     * <code>sint32 acctID = 2;</code>
     */
    public int getAcctID() {
      return acctID_;
    }

    public static final int NAME_FIELD_NUMBER = 3;
    private volatile java.lang.Object name_;
    /**
     * <pre>
     *Required fields are not allowed in proto3.
     * </pre>
     *
     * <code>string name = 3;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      }
    }
    /**
     * <pre>
     *Required fields are not allowed in proto3.
     * </pre>
     *
     * <code>string name = 3;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int SUC_FIELD_NUMBER = 4;
    private boolean suc_;
    /**
     * <pre>
     * 'optional' labels are disallowed in the Proto3 syntax.as fields are 'optional' by default.
     * </pre>
     *
     * <code>bool suc = 4;</code>
     */
    public boolean getSuc() {
      return suc_;
    }

    public static final int EMAIL_FIELD_NUMBER = 1;
    private volatile java.lang.Object email_;
    /**
     * <code>string email = 1;</code>
     */
    public java.lang.String getEmail() {
      java.lang.Object ref = email_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        email_ = s;
        return s;
      }
    }
    /**
     * <code>string email = 1;</code>
     */
    public com.google.protobuf.ByteString
        getEmailBytes() {
      java.lang.Object ref = email_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        email_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
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
      if (!getEmailBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, email_);
      }
      if (acctID_ != 0) {
        output.writeSInt32(2, acctID_);
      }
      if (!getNameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, name_);
      }
      if (suc_ != false) {
        output.writeBool(4, suc_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getEmailBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, email_);
      }
      if (acctID_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeSInt32Size(2, acctID_);
      }
      if (!getNameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, name_);
      }
      if (suc_ != false) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(4, suc_);
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
      if (!(obj instanceof protobuf.demoV2.DemoLoginV2.DemoReqV2)) {
        return super.equals(obj);
      }
      protobuf.demoV2.DemoLoginV2.DemoReqV2 other = (protobuf.demoV2.DemoLoginV2.DemoReqV2) obj;

      boolean result = true;
      result = result && (getAcctID()
          == other.getAcctID());
      result = result && getName()
          .equals(other.getName());
      result = result && (getSuc()
          == other.getSuc());
      result = result && getEmail()
          .equals(other.getEmail());
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
      hash = (37 * hash) + ACCTID_FIELD_NUMBER;
      hash = (53 * hash) + getAcctID();
      hash = (37 * hash) + NAME_FIELD_NUMBER;
      hash = (53 * hash) + getName().hashCode();
      hash = (37 * hash) + SUC_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getSuc());
      hash = (37 * hash) + EMAIL_FIELD_NUMBER;
      hash = (53 * hash) + getEmail().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static protobuf.demoV2.DemoLoginV2.DemoReqV2 parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protobuf.demoV2.DemoLoginV2.DemoReqV2 parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protobuf.demoV2.DemoLoginV2.DemoReqV2 parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protobuf.demoV2.DemoLoginV2.DemoReqV2 parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protobuf.demoV2.DemoLoginV2.DemoReqV2 parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protobuf.demoV2.DemoLoginV2.DemoReqV2 parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protobuf.demoV2.DemoLoginV2.DemoReqV2 parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static protobuf.demoV2.DemoLoginV2.DemoReqV2 parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static protobuf.demoV2.DemoLoginV2.DemoReqV2 parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static protobuf.demoV2.DemoLoginV2.DemoReqV2 parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static protobuf.demoV2.DemoLoginV2.DemoReqV2 parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static protobuf.demoV2.DemoLoginV2.DemoReqV2 parseFrom(
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
    public static Builder newBuilder(protobuf.demoV2.DemoLoginV2.DemoReqV2 prototype) {
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
     * Protobuf type {@code protobuf.demoV2.DemoReqV2}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:protobuf.demoV2.DemoReqV2)
        protobuf.demoV2.DemoLoginV2.DemoReqV2OrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return protobuf.demoV2.DemoLoginV2.internal_static_protobuf_demoV2_DemoReqV2_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return protobuf.demoV2.DemoLoginV2.internal_static_protobuf_demoV2_DemoReqV2_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                protobuf.demoV2.DemoLoginV2.DemoReqV2.class, protobuf.demoV2.DemoLoginV2.DemoReqV2.Builder.class);
      }

      // Construct using protobuf.demoV2.DemoLoginV2.DemoReqV2.newBuilder()
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
        acctID_ = 0;

        name_ = "";

        suc_ = false;

        email_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return protobuf.demoV2.DemoLoginV2.internal_static_protobuf_demoV2_DemoReqV2_descriptor;
      }

      public protobuf.demoV2.DemoLoginV2.DemoReqV2 getDefaultInstanceForType() {
        return protobuf.demoV2.DemoLoginV2.DemoReqV2.getDefaultInstance();
      }

      public protobuf.demoV2.DemoLoginV2.DemoReqV2 build() {
        protobuf.demoV2.DemoLoginV2.DemoReqV2 result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public protobuf.demoV2.DemoLoginV2.DemoReqV2 buildPartial() {
        protobuf.demoV2.DemoLoginV2.DemoReqV2 result = new protobuf.demoV2.DemoLoginV2.DemoReqV2(this);
        result.acctID_ = acctID_;
        result.name_ = name_;
        result.suc_ = suc_;
        result.email_ = email_;
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
        if (other instanceof protobuf.demoV2.DemoLoginV2.DemoReqV2) {
          return mergeFrom((protobuf.demoV2.DemoLoginV2.DemoReqV2)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(protobuf.demoV2.DemoLoginV2.DemoReqV2 other) {
        if (other == protobuf.demoV2.DemoLoginV2.DemoReqV2.getDefaultInstance()) return this;
        if (other.getAcctID() != 0) {
          setAcctID(other.getAcctID());
        }
        if (!other.getName().isEmpty()) {
          name_ = other.name_;
          onChanged();
        }
        if (other.getSuc() != false) {
          setSuc(other.getSuc());
        }
        if (!other.getEmail().isEmpty()) {
          email_ = other.email_;
          onChanged();
        }
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
        protobuf.demoV2.DemoLoginV2.DemoReqV2 parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (protobuf.demoV2.DemoLoginV2.DemoReqV2) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int acctID_ ;
      /**
       * <pre>
       *账号id
       * </pre>
       *
       * <code>sint32 acctID = 2;</code>
       */
      public int getAcctID() {
        return acctID_;
      }
      /**
       * <pre>
       *账号id
       * </pre>
       *
       * <code>sint32 acctID = 2;</code>
       */
      public Builder setAcctID(int value) {
        
        acctID_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *账号id
       * </pre>
       *
       * <code>sint32 acctID = 2;</code>
       */
      public Builder clearAcctID() {
        
        acctID_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object name_ = "";
      /**
       * <pre>
       *Required fields are not allowed in proto3.
       * </pre>
       *
       * <code>string name = 3;</code>
       */
      public java.lang.String getName() {
        java.lang.Object ref = name_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          name_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       *Required fields are not allowed in proto3.
       * </pre>
       *
       * <code>string name = 3;</code>
       */
      public com.google.protobuf.ByteString
          getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          name_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       *Required fields are not allowed in proto3.
       * </pre>
       *
       * <code>string name = 3;</code>
       */
      public Builder setName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        name_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *Required fields are not allowed in proto3.
       * </pre>
       *
       * <code>string name = 3;</code>
       */
      public Builder clearName() {
        
        name_ = getDefaultInstance().getName();
        onChanged();
        return this;
      }
      /**
       * <pre>
       *Required fields are not allowed in proto3.
       * </pre>
       *
       * <code>string name = 3;</code>
       */
      public Builder setNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        name_ = value;
        onChanged();
        return this;
      }

      private boolean suc_ ;
      /**
       * <pre>
       * 'optional' labels are disallowed in the Proto3 syntax.as fields are 'optional' by default.
       * </pre>
       *
       * <code>bool suc = 4;</code>
       */
      public boolean getSuc() {
        return suc_;
      }
      /**
       * <pre>
       * 'optional' labels are disallowed in the Proto3 syntax.as fields are 'optional' by default.
       * </pre>
       *
       * <code>bool suc = 4;</code>
       */
      public Builder setSuc(boolean value) {
        
        suc_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 'optional' labels are disallowed in the Proto3 syntax.as fields are 'optional' by default.
       * </pre>
       *
       * <code>bool suc = 4;</code>
       */
      public Builder clearSuc() {
        
        suc_ = false;
        onChanged();
        return this;
      }

      private java.lang.Object email_ = "";
      /**
       * <code>string email = 1;</code>
       */
      public java.lang.String getEmail() {
        java.lang.Object ref = email_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          email_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string email = 1;</code>
       */
      public com.google.protobuf.ByteString
          getEmailBytes() {
        java.lang.Object ref = email_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          email_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string email = 1;</code>
       */
      public Builder setEmail(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        email_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string email = 1;</code>
       */
      public Builder clearEmail() {
        
        email_ = getDefaultInstance().getEmail();
        onChanged();
        return this;
      }
      /**
       * <code>string email = 1;</code>
       */
      public Builder setEmailBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        email_ = value;
        onChanged();
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


      // @@protoc_insertion_point(builder_scope:protobuf.demoV2.DemoReqV2)
    }

    // @@protoc_insertion_point(class_scope:protobuf.demoV2.DemoReqV2)
    private static final protobuf.demoV2.DemoLoginV2.DemoReqV2 DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new protobuf.demoV2.DemoLoginV2.DemoReqV2();
    }

    public static protobuf.demoV2.DemoLoginV2.DemoReqV2 getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<DemoReqV2>
        PARSER = new com.google.protobuf.AbstractParser<DemoReqV2>() {
      public DemoReqV2 parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new DemoReqV2(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<DemoReqV2> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<DemoReqV2> getParserForType() {
      return PARSER;
    }

    public protobuf.demoV2.DemoLoginV2.DemoReqV2 getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_protobuf_demoV2_DemoReqV2_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_protobuf_demoV2_DemoReqV2_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021DemoLoginV2.proto\022\017protobuf.demoV2\"E\n\t" +
      "DemoReqV2\022\016\n\006acctID\030\002 \001(\021\022\014\n\004name\030\003 \001(\t\022" +
      "\013\n\003suc\030\004 \001(\010\022\r\n\005email\030\001 \001(\tB\002H\001b\006proto3"
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
    internal_static_protobuf_demoV2_DemoReqV2_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_protobuf_demoV2_DemoReqV2_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_protobuf_demoV2_DemoReqV2_descriptor,
        new java.lang.String[] { "AcctID", "Name", "Suc", "Email", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
