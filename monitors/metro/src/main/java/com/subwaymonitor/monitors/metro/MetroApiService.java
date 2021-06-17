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

  MetroApiResponse getStatuses() {
    final var uri = UriComponentsBuilder.fromHttpUrl(properties.getUrl()).build().toUri();

    final ResponseEntity<MetroApiResponse> metroApiResponse =
        restTemplate.getForEntity(uri, MetroApiResponse.class);

    var metroApiResponseBody = metroApiResponse.getBody();

    LOGGER.info("GET request for {} returned = {}", properties.getUrl(), metroApiResponseBody);

    return metroApiResponseBody;
  }
}
