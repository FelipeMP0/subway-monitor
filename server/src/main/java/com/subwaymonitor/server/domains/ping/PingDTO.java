package com.subwaymonitor.server.domains.ping;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

/** DTO for the ping endpoint. */
@Value.Immutable
@Value.Style(
    visibility = Value.Style.ImplementationVisibility.PACKAGE,
    overshadowImplementation = true)
@JsonSerialize(as = ImmutablePingDTO.class)
@JsonDeserialize(as = ImmutablePingDTO.class)
interface PingDTO {

  /** String field. Value is always "pong". */
  @Value.Default
  default String response() {
    return "pong";
  }

  /** Inner builder class to extend from the generated Builder. Used to simplify the syntax. */
  class Builder extends ImmutablePingDTO.Builder {}

  /**
   * Returns a builder for {@link PingDTO}.
   *
   * @return An instance of {@link Builder} that should be used to create an immutable {@link
   *     PingDTO}.
   */
  static Builder builder() {
    return new Builder();
  }
}
