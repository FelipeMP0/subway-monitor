package com.subwaymonitor.sharedmodel;

import org.immutables.value.Value;

/** Wrapper class that represents the current status of a line. */
@Value.Immutable
public interface LineCurrentStatus {

  /** Numeric identifier of the line in the transport system. */
  Integer lineNumber();

  /** Current status of the line. */
  StatusEnum status();
}
