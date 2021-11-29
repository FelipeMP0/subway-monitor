package com.subwaymonitor.sharedmodel;

import org.immutables.value.Value;

/** Represent a possible status for a line. */
@Value.Immutable
public interface Status {

  /** Unique identifier of a status. */
  StatusEnum status();

  /** Display name for the status. */
  String name();
}
