package com.subwaymonitor.sharedmodel;

import org.immutables.value.Value;

/** Wrapper class representing the status of a line on a given moment. */
@Value.Immutable
public interface LineStatus {

  /** Complete information about a line. */
  Line line();

  /** Complete information about a status. */
  Status status();
}
