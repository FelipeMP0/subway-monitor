package com.subwaymonitor.monitors.metro;

import java.net.URI;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
class MetroApiService {

  private final MetroApiServiceProperties properties;
  private final RestTemplate restTemplate;

  @Autowired
  MetroApiService(
      final MetroApiServiceProperties properties, final RestTemplateBuilder restTemplateBuilder) {
    this.properties = properties;
    this.restTemplate = restTemplateBuilder.setReadTimeout(Duration.ofSeconds(30)).build();
  }

  MetroApiResponse getStatuses() {
    final URI uri = UriComponentsBuilder.fromHttpUrl(this.properties.getUrl()).build().toUri();

    final ResponseEntity<MetroApiResponse> metroApiResponse =
        restTemplate.getForEntity(uri, MetroApiResponse.class);

    return metroApiResponse.getBody();
  }
}
