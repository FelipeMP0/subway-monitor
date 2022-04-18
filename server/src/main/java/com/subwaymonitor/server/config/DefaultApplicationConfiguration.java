package com.subwaymonitor.server.config;

import static com.subwaymonitor.server.config.AsyncConfiguration.DEFAULT_TASK_EXECUTOR;
import static com.subwaymonitor.server.config.AsyncConfiguration.MONITORS_TASK_EXECUTOR;

import com.subwaymonitor.application.config.ApplicationConfig;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultApplicationConfiguration implements ApplicationConfig {

  private final Executor defaultTaskExecutor;
  private final Executor monitorsTaskExecutor;

  @Autowired
  DefaultApplicationConfiguration(
      @Qualifier(DEFAULT_TASK_EXECUTOR) final Executor defaultTaskExecutor,
      @Qualifier(MONITORS_TASK_EXECUTOR) final Executor monitorsTaskExecutor) {
    this.defaultTaskExecutor = defaultTaskExecutor;
    this.monitorsTaskExecutor = monitorsTaskExecutor;
  }

  @Override
  public ExecutorsContainer executors() {
    return new ExecutorsContainer(defaultTaskExecutor, monitorsTaskExecutor);
  }
}
