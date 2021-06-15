package com.subwaymonitor.monitors.metro;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
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
    if (metroApiResponseBody != null) {
      final List<ImmutableLineStatus> normalizedLineStatusList =
          metroApiResponseBody
              .statusMetro()
              .lineStatuses()
              .stream()
              .map(
                  lineStatus ->
                      ImmutableLineStatus.copyOf(lineStatus)
                          .withStatusDescription(iso88591ToUTF8(lineStatus.statusDescription())))
              .collect(Collectors.toList());
      metroApiResponseBody =
          ImmutableMetroApiResponse.copyOf(metroApiResponseBody)
              .withStatusMetro(
                  ImmutableStatusMetro.copyOf(metroApiResponseBody.statusMetro())
                      .withLineStatuses(normalizedLineStatusList));
    }

    LOGGER.info("GET request for {} returned = {}", properties.getUrl(), metroApiResponseBody);

    return metroApiResponseBody;
  }

  private String iso88591ToUTF8(final String value) {
    final byte[] utf8 =
        new String(value.getBytes(), StandardCharsets.ISO_8859_1).getBytes(StandardCharsets.UTF_8);
    return new String(utf8, StandardCharsets.UTF_8);
  }
}
