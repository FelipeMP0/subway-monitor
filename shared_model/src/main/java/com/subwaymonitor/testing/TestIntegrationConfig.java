package com.subwaymonitor.testing;

import com.subwaymonitor.config.MonitorConfig;
import java.util.concurrent.Executors;

/** Utility class to create an instance of {@link MonitorConfig} for testing. */
public final class TestIntegrationConfig {

  private TestIntegrationConfig() {}

  public static <T> MonitorConfig<T> createInstance(T properties) {
    return new MonitorConfig<>(properties, Executors.newSingleThreadExecutor());
  }
}
