package com.subwaymonitor.server.config;

import com.subwaymonitor.config.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
class DefaultApplicationConfig implements ApplicationConfig {

  private final AsyncConfiguration asyncConfiguration;

  @Autowired
  DefaultApplicationConfig(final AsyncConfiguration asyncConfiguration) {
    this.asyncConfiguration = asyncConfiguration;
  }

  @Override
  public ExecutorsContainer executors() {
    return new ExecutorsContainer(
        asyncConfiguration.getAsyncExecutor(), asyncConfiguration.getMonitorsAsyncExecutor());
  }
}
