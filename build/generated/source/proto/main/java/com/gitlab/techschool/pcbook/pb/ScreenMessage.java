// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: screen_message.proto

package com.gitlab.techschool.pcbook.pb;

public final class ScreenMessage {
  private ScreenMessage() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_techschool_pcbook_Screen_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_techschool_pcbook_Screen_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_techschool_pcbook_Screen_Resolution_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_techschool_pcbook_Screen_Resolution_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024screen_message.proto\022\021techschool.pcboo" +
      "k\"\357\001\n\006Screen\022\021\n\tsize_inch\030\001 \001(\002\0228\n\nresol" +
      "ution\030\002 \001(\0132$.techschool.pcbook.Screen.R" +
      "esolution\022.\n\005panel\030\003 \001(\0162\037.techschool.pc" +
      "book.Screen.Panel\022\022\n\nmultitouch\030\004 \001(\010\032+\n" +
      "\nResolution\022\r\n\005width\030\001 \001(\r\022\016\n\006height\030\002 \001" +
      "(\r\"\'\n\005Panel\022\013\n\007UNKNOWN\020\000\022\007\n\003IPS\020\001\022\010\n\004OLE" +
      "D\020\002B*\n\037com.gitlab.techschool.pcbook.pbP\001" +
      "Z\005./;pbb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_techschool_pcbook_Screen_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_techschool_pcbook_Screen_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_techschool_pcbook_Screen_descriptor,
        new java.lang.String[] { "SizeInch", "Resolution", "Panel", "Multitouch", });
    internal_static_techschool_pcbook_Screen_Resolution_descriptor =
      internal_static_techschool_pcbook_Screen_descriptor.getNestedTypes().get(0);
    internal_static_techschool_pcbook_Screen_Resolution_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_techschool_pcbook_Screen_Resolution_descriptor,
        new java.lang.String[] { "Width", "Height", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
