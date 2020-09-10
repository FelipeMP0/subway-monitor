package com.subwaymonitor.monitors.metro;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
class MetroApiService {

  private final MetroApiServiceProperties properties;
  private final RestTemplate restTemplate;

  @Autowired
  MetroApiService(final MetroApiServiceProperties properties, final RestTemplate restTemplate) {
    this.properties = properties;
    this.restTemplate = restTemplate;
  }

  MetroApiResponse getStatuses() {
    final URI uri = UriComponentsBuilder.fromHttpUrl(this.properties.getUrl()).build().toUri();

    final ResponseEntity<MetroApiResponse> metroApiResponse =
        this.restTemplate.getForEntity(uri, MetroApiResponse.class);

    return metroApiResponse.getBody();
  }
}
