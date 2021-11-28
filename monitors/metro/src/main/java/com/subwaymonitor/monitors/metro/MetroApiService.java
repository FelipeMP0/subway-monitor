package com.subwaymonitor.monitors.metro;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Service class responsible for interactions with the Metro's API. Used to query the current
 * statuses of the Metro lines.
 */
@Service
class MetroApiService {

  private static final Logger LOGGER = LoggerFactory.getLogger(MetroApiService.class);

  private final MetroApiServiceProperties properties;
  private final RestTemplate restTemplate;

  @Autowired
  MetroApiService(
      final MetroApiServiceProperties properties, final RestTemplateBuilder restTemplateBuilder) {
    this.properties = properties;
    this.restTemplate = restTemplateBuilder.setReadTimeout(Duration.ofSeconds(30)).build();
  }

  /**
   * Makes an HTTP request to the Metro's API and returns the current statuses for all lines in this
   * transport system.
   *
   * @return The parsed JSON response from the API containing the information and the current
   *     statuses of the lines.
   */
  MetroApiResponse getStatuses() {
    final var uri =
        UriComponentsBuilder.fromHttpUrl(properties.getUrl() + "/LineStatus").build().toUri();

    final ResponseEntity<MetroApiResponse> metroApiResponse =
        restTemplate.getForEntity(uri, MetroApiResponse.class);

    var metroApiResponseBody = metroApiResponse.getBody();

    LOGGER.info("GET request for {} returned = {}", properties.getUrl(), metroApiResponseBody);

    return metroApiResponseBody;
  }
}
