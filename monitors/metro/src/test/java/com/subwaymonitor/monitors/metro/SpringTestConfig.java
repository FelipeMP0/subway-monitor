package com.subwaymonitor.monitors.metro;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class SpringTestConfig {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public MetroApiServiceProperties metroApiServiceProperties() {
    final MetroApiServiceProperties metroApiServiceProperties = new MetroApiServiceProperties();
    metroApiServiceProperties.setUrl("http://example.com");
    return metroApiServiceProperties;
  }
}
