package com.subwaymonitor.application.config;

import java.util.concurrent.Executor;

/** Runtime configurations. */
public interface ApplicationConfig {

  /** Returns a container object with available executors. */
  ExecutorsContainer executors();

  /**
   * Container for async executors.
   *
   * @param defaultExecutor Default async executor.
   * @param monitorsExecutor Specific executor for monitors.
   */
  record ExecutorsContainer(Executor defaultExecutor, Executor monitorsExecutor) {}
}
