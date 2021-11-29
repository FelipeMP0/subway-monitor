package com.subwaymonitor.sharedmodel;

import java.util.List;
import org.immutables.value.Value;

/** Represents a verification of the current statuses of a list of lines in a given moment. */
@Value.Immutable
public interface Verification {

  /** List of statuses and lines. */
  List<LineStatus> lineStatuses();
}
