// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: DemoProto_V1.proto

// Protobuf Java Version: 3.25.3
package ps.demo.protobuf.model;

public final class DemoProtoV1 {
  private DemoProtoV1() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Addr_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Addr_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Person_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Person_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\022DemoProto_V1.proto\"D\n\004Addr\022\r\n\005line1\030\001 " +
      "\001(\t\022\r\n\005line2\030\002 \001(\t\022\r\n\005line3\030\003 \001(\t\022\017\n\007ver" +
      "sion\030\004 \001(\003\"q\n\006Person\022\014\n\004name\030\001 \001(\t\022\020\n\010ni" +
      "ckname\030\002 \003(\t\022\n\n\002id\030\003 \001(\005\022\r\n\005email\030\004 \001(\t\022" +
      "\027\n\006peType\030\005 \001(\0162\007.PeType\022\023\n\004addr\030\006 \001(\0132\005" +
      ".Addr* \n\006PeType\022\013\n\007INVALID\020\000\022\t\n\005VALID\020\001B" +
      "\032\n\026ps.demo.protobuf.modelP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Addr_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Addr_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Addr_descriptor,
        new String[] { "Line1", "Line2", "Line3", "Version", });
    internal_static_Person_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_Person_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Person_descriptor,
        new String[] { "Name", "Nickname", "Id", "Email", "PeType", "Addr", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
