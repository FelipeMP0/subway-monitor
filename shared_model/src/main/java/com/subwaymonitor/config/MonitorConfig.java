package com.subwaymonitor.config;

import java.util.concurrent.Executor;

/** Holds configurations to be used across the application. */
public record MonitorConfig<T>(T properties, Executor executor) {}
