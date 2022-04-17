package com.subwaymonitor.monitors.ping;

import java.net.URI;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Service class responsible for interactions with the Ping API. Used to keep the application awake.
 */
@Service
public class PingApiService {

  private final PingServiceProperties properties;
  private final RestTemplate restTemplate;

  @Autowired
  public PingApiService(
      final PingServiceProperties properties, final RestTemplateBuilder restTemplateBuilder) {
    this.properties = properties;
    this.restTemplate = restTemplateBuilder.setReadTimeout(Duration.ofSeconds(30)).build();
  }

  /** Calls the configured Ping endpoint. */
  public void ping() {
    final URI uri = UriComponentsBuilder.fromHttpUrl(properties.getUrl()).build().toUri();

    final ResponseEntity<Void> response = restTemplate.getForEntity(uri, Void.class);

    final HttpStatus statusCode = response.getStatusCode();

    if (statusCode.is4xxClientError()) {
      throw new HttpClientErrorException(statusCode);
    } else if (statusCode.is5xxServerError()) {
      throw new HttpServerErrorException(statusCode);
    }
  }
}