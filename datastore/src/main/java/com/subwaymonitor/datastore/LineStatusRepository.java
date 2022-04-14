package com.subwaymonitor.datastore;

import com.subwaymonitor.sharedmodel.LineStatus;
import java.util.Set;

/** Repository responsible for datastore interactions related to {@link LineStatus}. */
public interface LineStatusRepository {

  /**
   * Gets current state of all lines.
   *
   * @return A set of all lines with statuses.
   */
  Set<LineStatus> getCurrentState();
}
