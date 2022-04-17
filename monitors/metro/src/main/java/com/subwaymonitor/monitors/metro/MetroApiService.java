package com.subwaymonitor.monitors.metro;

import com.subwaymonitor.config.ApplicationConfig;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
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
  private final Executor executor;

  @Autowired
  MetroApiService(
      final MetroApiServiceProperties properties,
      final RestTemplateBuilder restTemplateBuilder,
      final ApplicationConfig applicationConfig) {
    this.properties = properties;
    this.restTemplate = restTemplateBuilder.setReadTimeout(Duration.ofSeconds(30)).build();
    this.executor = applicationConfig.executors().monitorsExecutor();
    ;
  }

  /**
   * Makes an HTTP request to the Metro's API and returns the current statuses for all lines in this
   * transport system.
   *
   * @return The parsed JSON response from the API containing the information and the current
   *     statuses of the lines.
   */
  CompletableFuture<MetroApiResponse> getStatuses() {
    return CompletableFuture.supplyAsync(
        () -> {
          final var uri =
              UriComponentsBuilder.fromHttpUrl(properties.getUrl() + "/LineStatus").build().toUri();

          final ResponseEntity<MetroApiResponse> metroApiResponse =
              restTemplate.getForEntity(uri, MetroApiResponse.class);

          var metroApiResponseBody = metroApiResponse.getBody();

          LOGGER.info(
              "GET request for {} returned = {}", properties.getUrl(), metroApiResponseBody);

          return metroApiResponseBody;
        },
        executor);
  }
}
