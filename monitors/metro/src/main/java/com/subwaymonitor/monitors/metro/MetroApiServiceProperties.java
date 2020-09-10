package com.subwaymonitor.monitors.metro;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "metro-service")
class MetroApiServiceProperties {

  private String url;

  String getUrl() {
    return url;
  }

  void setUrl(final String url) {
    this.url = url;
  }
}
