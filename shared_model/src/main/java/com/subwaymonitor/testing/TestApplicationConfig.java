package com.subwaymonitor.testing;

import com.subwaymonitor.config.ApplicationConfig;
import java.util.concurrent.Executors;

/** An implementation for {@link ApplicationConfig} to be used in unit tests. */
public final class TestApplicationConfig implements ApplicationConfig {

  private TestApplicationConfig() {}

  public static TestApplicationConfig newInstance() {
    return new TestApplicationConfig();
  }

  @Override
  public ExecutorsContainer executors() {
    return new ExecutorsContainer(
        Executors.newSingleThreadExecutor(), Executors.newSingleThreadExecutor());
  }
}
