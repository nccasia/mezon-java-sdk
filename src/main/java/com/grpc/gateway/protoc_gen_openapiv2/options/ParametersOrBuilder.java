// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: protoc-gen-openapiv2/options/openapiv2.proto
// Protobuf Java Version: 4.28.2

package com.grpc.gateway.protoc_gen_openapiv2.options;

public interface ParametersOrBuilder extends
    // @@protoc_insertion_point(interface_extends:grpc.gateway.protoc_gen_openapiv2.options.Parameters)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * `Headers` is one or more HTTP header parameter.
   * See: https://swagger.io/docs/specification/2-0/describing-parameters/#header-parameters
   * </pre>
   *
   * <code>repeated .grpc.gateway.protoc_gen_openapiv2.options.HeaderParameter headers = 1 [json_name = "headers"];</code>
   */
  java.util.List<com.grpc.gateway.protoc_gen_openapiv2.options.HeaderParameter> 
      getHeadersList();
  /**
   * <pre>
   * `Headers` is one or more HTTP header parameter.
   * See: https://swagger.io/docs/specification/2-0/describing-parameters/#header-parameters
   * </pre>
   *
   * <code>repeated .grpc.gateway.protoc_gen_openapiv2.options.HeaderParameter headers = 1 [json_name = "headers"];</code>
   */
  com.grpc.gateway.protoc_gen_openapiv2.options.HeaderParameter getHeaders(int index);
  /**
   * <pre>
   * `Headers` is one or more HTTP header parameter.
   * See: https://swagger.io/docs/specification/2-0/describing-parameters/#header-parameters
   * </pre>
   *
   * <code>repeated .grpc.gateway.protoc_gen_openapiv2.options.HeaderParameter headers = 1 [json_name = "headers"];</code>
   */
  int getHeadersCount();
  /**
   * <pre>
   * `Headers` is one or more HTTP header parameter.
   * See: https://swagger.io/docs/specification/2-0/describing-parameters/#header-parameters
   * </pre>
   *
   * <code>repeated .grpc.gateway.protoc_gen_openapiv2.options.HeaderParameter headers = 1 [json_name = "headers"];</code>
   */
  java.util.List<? extends com.grpc.gateway.protoc_gen_openapiv2.options.HeaderParameterOrBuilder> 
      getHeadersOrBuilderList();
  /**
   * <pre>
   * `Headers` is one or more HTTP header parameter.
   * See: https://swagger.io/docs/specification/2-0/describing-parameters/#header-parameters
   * </pre>
   *
   * <code>repeated .grpc.gateway.protoc_gen_openapiv2.options.HeaderParameter headers = 1 [json_name = "headers"];</code>
   */
  com.grpc.gateway.protoc_gen_openapiv2.options.HeaderParameterOrBuilder getHeadersOrBuilder(
      int index);
}
