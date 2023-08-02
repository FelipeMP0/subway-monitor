package com.subwaymonitor.application.config;

import com.subwaymonitor.config.MonitorConfig;
import com.subwaymonitor.integrations.google.GoogleServicesProperties;
import com.subwaymonitor.integrations.metro.MetroApiServiceProperties;
import com.subwaymonitor.integrations.ping.PingServiceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MonitorsConfig {

  @Bean
  MonitorConfig<PingServiceProperties> pingConfig(
      final PingServiceProperties properties, final ApplicationConfig applicationConfig) {
    return new MonitorConfig<>(properties, applicationConfig.executors().monitorsExecutor());
  }

  @Bean
  MonitorConfig<MetroApiServiceProperties> metroConfig(
      final MetroApiServiceProperties properties, final ApplicationConfig applicationConfig) {
    return new MonitorConfig<>(properties, applicationConfig.executors().monitorsExecutor());
  }

  @Bean
  MonitorConfig<GoogleServicesProperties> googleConfig(
      final GoogleServicesProperties properties, final ApplicationConfig applicationConfig) {
    return new MonitorConfig<>(properties, applicationConfig.executors().monitorsExecutor());
  }
}
