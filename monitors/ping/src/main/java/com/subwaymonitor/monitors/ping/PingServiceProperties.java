package com.subwaymonitor.monitors.ping;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/** Configurations to parametrize the usage of the Ping API. */
@Component
@ConfigurationProperties(prefix = "ping")
public final class PingServiceProperties {

  private String url;

  /**
   * Gets the configured base URL,
   *
   * @return The configured URl.
   */
  String getUrl() {
    return url;
  }

  /**
   * Sets the API's base URL.
   *
   * @param url A valid URL.
   */
  void setUrl(final String url) {
    this.url = url;
  }
}
