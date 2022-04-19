package com.subwaymonitor.monitors.ping;

import com.subwaymonitor.config.MonitorConfig;
import com.subwaymonitor.testing.TestIntegrationConfig;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
class TestConfig {

  private static final PingServiceProperties PROPERTIES = buildPingServiceProperties();

  private static final MonitorConfig<PingServiceProperties> INTEGRATION_CONFIG =
      buildIntegrationConfig();

  @Bean
  PingServiceProperties pingServiceProperties() {
    return PROPERTIES;
  }

  @Bean
  MonitorConfig<PingServiceProperties> integrationConfig() {
    return INTEGRATION_CONFIG;
  }

  static PingServiceProperties getProperties() {
    return PROPERTIES;
  }

  private static PingServiceProperties buildPingServiceProperties() {
    final PingServiceProperties pingServiceProperties = new PingServiceProperties();
    pingServiceProperties.setUrl("http://example.com");
    return pingServiceProperties;
  }

  private static MonitorConfig<PingServiceProperties> buildIntegrationConfig() {
    return TestIntegrationConfig.createInstance(PROPERTIES);
  }
}
