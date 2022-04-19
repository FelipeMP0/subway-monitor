package com.subwaymonitor.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

  private final AsyncProperties async = new AsyncProperties();

  public AsyncProperties getAsync() {
    return async;
  }

  public static class AsyncProperties {

    private Integer corePoolSize;
    private Integer maxPoolSize;
    private Integer queueCapacity;

    public Integer getCorePoolSize() {
      return corePoolSize;
    }

    public void setCorePoolSize(final Integer corePoolSize) {
      this.corePoolSize = corePoolSize;
    }

    public Integer getMaxPoolSize() {
      return maxPoolSize;
    }

    public void setMaxPoolSize(final Integer maxPoolSize) {
      this.maxPoolSize = maxPoolSize;
    }

    public Integer getQueueCapacity() {
      return queueCapacity;
    }

    public void setQueueCapacity(final Integer queueCapacity) {
      this.queueCapacity = queueCapacity;
    }
  }
}
