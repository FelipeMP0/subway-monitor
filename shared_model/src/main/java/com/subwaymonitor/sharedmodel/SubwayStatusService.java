package com.subwaymonitor.sharedmodel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/** Interface responsible for providing the current statuses of lines in a given moment. */
public interface SubwayStatusService {

  /**
   * Finds the statuses for the list of supported lines.
   *
   * @return the list of current statuses and line identifiers.
   */
  CompletableFuture<List<LineCurrentStatus>> findLineStatuses();
}
