package com.subwaymonitor.server.config;

import java.util.concurrent.Executor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {

  private static final String MONITORS_TASK_EXECUTOR_NAME_PREFIX = "monitorsTaskExecutor-";

  public static final String MONITORS_TASK_EXECUTOR = "monitorsTaskExecutor";

  private final ApplicationProperties applicationProperties;

  @Autowired
  public AsyncConfiguration(final ApplicationProperties applicationProperties) {
    this.applicationProperties = applicationProperties;
  }

  @Override
  @Bean(name = MONITORS_TASK_EXECUTOR)
  public Executor getAsyncExecutor() {
    return newTaskExecutor(MONITORS_TASK_EXECUTOR_NAME_PREFIX);
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return new SimpleAsyncUncaughtExceptionHandler();
  }

  private Executor newTaskExecutor(final String taskExecutorNamePrefix) {
    final ApplicationProperties.Async asyncProperties = applicationProperties.getAsync();
    final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(asyncProperties.getCorePoolSize());
    executor.setMaxPoolSize(asyncProperties.getMaxPoolSize());
    executor.setQueueCapacity(asyncProperties.getQueueCapacity());
    executor.setThreadNamePrefix(taskExecutorNamePrefix);
    return executor;
  }
}
