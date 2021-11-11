// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: laptop_service.proto

package com.gitlab.techschool.pcbook.pb;

public interface RateLaptopResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:techschool.pcbook.RateLaptopResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string laptop_id = 1;</code>
   * @return The laptopId.
   */
  java.lang.String getLaptopId();
  /**
   * <code>string laptop_id = 1;</code>
   * @return The bytes for laptopId.
   */
  com.google.protobuf.ByteString
      getLaptopIdBytes();

  /**
   * <pre>
   * The number of time this laptop was rated
   * </pre>
   *
   * <code>uint32 rated_count = 2;</code>
   * @return The ratedCount.
   */
  int getRatedCount();

  /**
   * <pre>
   * Average rated score
   * </pre>
   *
   * <code>double average_score = 3;</code>
   * @return The averageScore.
   */
  double getAverageScore();
}
