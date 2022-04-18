package com.subwaymonitor.application.config;

import java.util.concurrent.Executor;

/** Runtime configurations. */
public interface ApplicationConfig {

  /** Returns a container object with available executors. */
  ExecutorsContainer executors();

  /** Container for async executors. */
  class ExecutorsContainer {

    private final Executor defaultExecutor;
    private final Executor monitorsExecutor;

    public ExecutorsContainer(final Executor defaultExecutor, final Executor monitorsExecutor) {
      this.defaultExecutor = defaultExecutor;
      this.monitorsExecutor = monitorsExecutor;
    }

    /** Returns the default async executor. */
    public Executor getDefaultExecutor() {
      return defaultExecutor;
    }

    /** Returns a specific executor for monitors. */
    public Executor getMonitorsExecutor() {
      return monitorsExecutor;
    }
  }
}
