package com.subwaymonitor.integrations.metro;

import com.subwaymonitor.config.MonitorConfig;
import com.subwaymonitor.testing.TestIntegrationConfig;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
class TestConfig {

  private static final MetroApiServiceProperties PROPERTIES = buildMetroApiServiceProperties();

  private static final MonitorConfig<MetroApiServiceProperties> INTEGRATION_CONFIG =
      buildIntegrationConfig();

  @Bean
  MetroApiServiceProperties metroApiServiceProperties() {
    return PROPERTIES;
  }

  @Bean
  MonitorConfig<MetroApiServiceProperties> integrationConfig() {
    return INTEGRATION_CONFIG;
  }

  static MetroApiServiceProperties getPropertiesInstance() {
    return PROPERTIES;
  }

  static MonitorConfig<MetroApiServiceProperties> getIntegrationConfigInstance() {
    return INTEGRATION_CONFIG;
  }

  private static MetroApiServiceProperties buildMetroApiServiceProperties() {
    final MetroApiServiceProperties metroApiServiceProperties = new MetroApiServiceProperties();
    metroApiServiceProperties.setUrl("http://example.com");
    return metroApiServiceProperties;
  }

  private static MonitorConfig<MetroApiServiceProperties> buildIntegrationConfig() {
    return TestIntegrationConfig.createInstance(PROPERTIES);
  }
}
