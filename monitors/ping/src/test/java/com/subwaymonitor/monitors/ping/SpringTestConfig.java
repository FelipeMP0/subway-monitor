package com.subwaymonitor.monitors.ping;

import org.springframework.context.annotation.Bean;

public class SpringTestConfig {

  @Bean
  public PingServiceProperties pingServiceProperties() {
    final PingServiceProperties pingServiceProperties = new PingServiceProperties();
    pingServiceProperties.setUrl("http://example.com");
    return pingServiceProperties;
  }
}
