package com.subwaymonitor.monitors.metro;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Representation of the response received from the API used to query the statuses of the metro
 * lines. Responses are received in JSON format.
 *
 * @param statusMetro A wrapper object for the received data.
 */
@JsonDeserialize
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record MetroApiResponse(@JsonProperty("StatusMetro") StatusMetro statusMetro) {

  /**
   * Contains the current statuses and information about the lines at current moment.
   *
   * @param dateUpdateMetro Current data and time in UTC -03:00.
   * @param lineStatuses List of lines and their current statuses.
   */
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public record StatusMetro(
      @JsonProperty("DateUpdateMetro")
          @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
          LocalDateTime dateUpdateMetro,
      @JsonProperty("ListLineStatus") List<LineStatus> lineStatuses) {

    /**
     * An object containing line identifiers, descriptions and current states.
     *
     * @param id Integer representing the numeric identifier of the line in the transport system.
     *     e.g. 3.
     * @param color The color identifier of the line in the transport system. e.g. Red.
     * @param lineFullName The full name of the line containing the numeric identifier and color,
     *     used for display purposes. e.g. Line 3 - Red.
     * @param lineNumberDescription String used for display purposes containing the numeric
     *     identifier. e.g. Line 3.
     * @param statusDescription String representing the current state of the line. e.g. Operation
     *     closed.
     * @param statusNumber An integer identifier for the status.
     * @param description String describing what the current status means.
     */
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public record LineStatus(
        @JsonProperty("Id") String id,
        @JsonProperty("Color") String color,
        @JsonProperty("Line") String lineFullName,
        @JsonProperty("LineRaw") String lineNumberDescription,
        @JsonProperty("StatusMetro") String statusDescription,
        @JsonProperty("Status") Integer statusNumber,
        @JsonProperty("Description") String description) {}
  }
}
