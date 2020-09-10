package com.subwaymonitor.monitors.metro;

import org.springframework.context.annotation.Bean;

public class SpringTestConfig {

  @Bean
  public MetroApiServiceProperties metroApiServiceProperties() {
    final MetroApiServiceProperties metroApiServiceProperties = new MetroApiServiceProperties();
    metroApiServiceProperties.setUrl("http://example.com");
    return metroApiServiceProperties;
  }
}
