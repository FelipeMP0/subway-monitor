package com.subwaymonitor.application.config;

import com.subwaymonitor.config.MonitorConfig;
import com.subwaymonitor.monitors.metro.MetroApiServiceProperties;
import com.subwaymonitor.monitors.ping.PingServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MonitorsConfig {

  private final ApplicationConfig applicationConfig;
  private final PingServiceProperties pingServiceProperties;
  private final MetroApiServiceProperties metroApiServiceProperties;

  @Autowired
  MonitorsConfig(
      final ApplicationConfig applicationConfig,
      final PingServiceProperties pingServiceProperties,
      final MetroApiServiceProperties metroApiServiceProperties) {
    this.applicationConfig = applicationConfig;
    this.pingServiceProperties = pingServiceProperties;
    this.metroApiServiceProperties = metroApiServiceProperties;
  }

  @Bean
  MonitorConfig<PingServiceProperties> pingConfig() {
    return new MonitorConfig<>(
        pingServiceProperties, applicationConfig.executors().monitorsExecutor());
  }

  @Bean
  MonitorConfig<MetroApiServiceProperties> metroConfig() {
    return new MonitorConfig<>(
        metroApiServiceProperties, applicationConfig.executors().monitorsExecutor());
  }
}
