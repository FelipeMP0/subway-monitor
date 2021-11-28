package com.subwaymonitor.monitors.metro;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDateTime;
import java.util.List;
import org.immutables.value.Value;

/**
 * Representation of the response received from the API used to query the statuses of the metro
 * lines. Responses are received in JSON format.
 */
@Value.Immutable
@JsonDeserialize(as = ImmutableMetroApiResponse.class)
interface MetroApiResponse {

  /** A wrapper object for the received data. */
  @JsonProperty("StatusMetro")
  StatusMetro statusMetro();

  /** Contains the current statuses and information about the lines at current moment. */
  @Value.Immutable
  @JsonDeserialize(as = ImmutableStatusMetro.class)
  interface StatusMetro {

    /** Current data and time in UTC -03:00. */
    @JsonProperty("DateUpdateMetro")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime dateUpdateMetro();

    /** List of lines and their current statuses. */
    @JsonProperty("ListLineStatus")
    List<LineStatus> lineStatuses();

    /** An object containing line identifiers, descriptions and current states. */
    @Value.Immutable
    @JsonDeserialize(as = ImmutableLineStatus.class)
    interface LineStatus {

      /** Integer representing the numeric identifier of the line in the transport system. e.g. 3 */
      @JsonProperty("Id")
      String id();

      /** The color identifier of the line in the transport system. e.g. Red */
      @JsonProperty("Color")
      String color();

      /**
       * The full name of the line containing the numeric identifier and color, used for display
       * purposes. e.g. Line 3 - Red.
       */
      @JsonProperty("Line")
      String lineFullName();

      /** String used for display purposes containing the numeric identifier. e.g. Line 3. */
      @JsonProperty("LineRaw")
      String lineNumberDescription();

      /** String representing the current state of the line. e.g. Operation closed. */
      @JsonProperty("StatusMetro")
      String statusDescription();

      /** An integer identifier for the status. */
      @JsonProperty("Status")
      Integer statusNumber();

      /** String describing what the current status means. */
      @JsonProperty("Description")
      String description();
    }
  }
}
