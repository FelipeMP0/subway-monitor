package com.subwaymonitor.config;

import java.util.concurrent.Executor;

/** Holds configurations to be used across the application. */
public class MonitorConfig<T> {

  private final T properties;
  private final Executor executor;

  public MonitorConfig(final T properties, final Executor executor) {
    this.properties = properties;
    this.executor = executor;
  }

  public T properties() {
    return properties;
  }

  public Executor executor() {
    return executor;
  }
}
