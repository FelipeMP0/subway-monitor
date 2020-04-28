package com.subwaymonitors.monitors.metro;

import com.subwaymonitors.monitors.metro.config.MetroApiServiceProperties;
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
  private final RestTemplate httpClient;

  @Autowired
  MetroApiService(MetroApiServiceProperties properties) {
    this.properties = properties;
    this.httpClient = new RestTemplateBuilder().setReadTimeout(Duration.ofSeconds(30)).build();
  }

  MetroApiResponse getStatuses() {
    final URI uri = UriComponentsBuilder.fromHttpUrl(this.properties.getUrl()).build().toUri();

    ResponseEntity<MetroApiResponse> metroApiResponse =
        this.httpClient.getForEntity(uri, MetroApiResponse.class);

    return metroApiResponse.getBody();
  }
}
