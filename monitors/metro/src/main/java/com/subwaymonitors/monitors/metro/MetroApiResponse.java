package com.subwaymonitors.monitors.metro;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import org.immutables.value.Value;

@Value.Immutable
interface MetroApiResponse {

  @JsonProperty("StatusMetro")
  StatusMetro statusMetro();

  @Value.Immutable
  interface StatusMetro {

    @JsonProperty("DateUpdateMetro")
    LocalDateTime dateUpdateMetro();

    @JsonProperty("ListLineStatus")
    List<LineStatus> lineStatuses();

    @Value.Immutable
    interface LineStatus {

      @JsonProperty("Id")
      String id();

      @JsonProperty("Color")
      String color();

      @JsonProperty("Description")
      String description();

      @JsonProperty("LineRaw")
      String lineNumberDescription();

      @JsonProperty("Line")
      String lineFullName();

      @JsonProperty("Status")
      Integer statusNumber();

      @JsonProperty("StatusMetro")
      String statusDescription();
    }
  }
}
