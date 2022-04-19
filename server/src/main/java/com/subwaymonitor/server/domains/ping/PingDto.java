package com.subwaymonitor.server.domains.ping;

import java.time.ZonedDateTime;

/**
 * DTO for the ping endpoint.
 *
 * @param response String field. Value is always "pong".
 * @param currentTime Current time of the server.
 */
public record PingDto(String response, ZonedDateTime currentTime) {}
