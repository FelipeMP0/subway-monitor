package com.subwaymonitor.config;

import java.util.concurrent.Executor;

/** Holds configurations to be used across the application. */
public interface ApplicationConfig {

  /** Returns the available executors for the application. */
  ExecutorsContainer executors();

  /** Container for async executors. */
  class ExecutorsContainer {

    private final Executor defaultExecutor;
    private final Executor monitorsExecutor;

    public ExecutorsContainer(final Executor defaultExecutor, final Executor monitorsExecutor) {
      this.defaultExecutor = defaultExecutor;
      this.monitorsExecutor = monitorsExecutor;
    }

    public Executor defaultExecutor() {
      return defaultExecutor;
    }

    public Executor monitorsExecutor() {
      return monitorsExecutor;
    }
  }
}
