package com.subwaymonitor.server.domains.ping;

import java.time.ZonedDateTime;
import org.immutables.value.Value;

/** DTO for the ping endpoint. */
@Value.Immutable
@Value.Style(
    visibility = Value.Style.ImplementationVisibility.PACKAGE,
    overshadowImplementation = true)
interface PingDTO {

  /** String field. Value is always "pong" */
  default String response() {
    return "pong";
  }

  /** Current time of the server. */
  default ZonedDateTime currentTime() {
    return ZonedDateTime.now();
  }

  /** Inner builder class to extend from the generated Builder. Used to simplify the syntax. */
  class Builder extends ImmutablePingDTO.Builder {}
}
