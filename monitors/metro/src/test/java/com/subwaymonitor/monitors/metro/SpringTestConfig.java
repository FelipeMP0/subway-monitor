package com.subwaymonitor.monitors.metro;

import com.subwaymonitor.config.ApplicationConfig;
import com.subwaymonitor.testing.TestApplicationConfig;
import org.springframework.context.annotation.Bean;

public class SpringTestConfig {

  @Bean
  public MetroApiServiceProperties metroApiServiceProperties() {
    final MetroApiServiceProperties metroApiServiceProperties = new MetroApiServiceProperties();
    metroApiServiceProperties.setUrl("http://example.com");
    return metroApiServiceProperties;
  }

  @Bean
  public ApplicationConfig applicationConfig() {
    return TestApplicationConfig.newInstance();
  }
}
