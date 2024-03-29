package com.subwaymonitor.integrations.metro;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/** Configurations to parametrize the usage of the Metro's API. */
@Component
@ConfigurationProperties(prefix = "integrations.metro-service")
public class MetroApiServiceProperties {

  private String url;

  /**
   * Gets the configured base URL used to query line statuses.
   *
   * @return The configured URl.
   */
  public String getUrl() {
    return url;
  }

  /**
   * Sets the API's base URL.
   *
   * @param url A valid URL that will be used to query line statuses.
   */
  public void setUrl(final String url) {
    this.url = url;
  }
}
