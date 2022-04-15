package com.subwaymonitor.sharedmodel;

import org.immutables.value.Value;

/** Wrapper class that represents the current status of a line. */
@Value.Immutable
public interface LineCurrentStatus {

  /** Line representation. */
  Line line();

  /** Current status of the line. */
  StatusEnum status();
}
