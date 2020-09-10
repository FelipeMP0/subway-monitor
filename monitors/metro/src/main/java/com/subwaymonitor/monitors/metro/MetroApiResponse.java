package com.subwaymonitor.monitors.metro;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDateTime;
import java.util.List;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableMetroApiResponse.class)
interface MetroApiResponse {

  @JsonProperty("StatusMetro")
  StatusMetro statusMetro();

  @Value.Immutable
  @JsonDeserialize(as = ImmutableStatusMetro.class)
  interface StatusMetro {

    @JsonProperty("DateUpdateMetro")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime dateUpdateMetro();

    @JsonProperty("ListLineStatus")
    List<LineStatus> lineStatuses();

    @Value.Immutable
    @JsonDeserialize(as = ImmutableLineStatus.class)
    interface LineStatus {

      @JsonProperty("Id")
      String id();

      @JsonProperty("Color")
      String color();

      @JsonProperty("Line")
      String lineFullName();

      @JsonProperty("LineRaw")
      String lineNumberDescription();

      @JsonProperty("StatusMetro")
      String statusDescription();

      @JsonProperty("Status")
      Integer statusNumber();

      @JsonProperty("Description")
      String description();
    }
  }
}
