package com.subwaymonitor.datastore;

import com.subwaymonitor.sharedmodel.LineStatus;
import java.util.List;

/** Repository responsible for datastore interactions related to {@link LineStatus}. */
public interface LineStatusRepository {

  /**
   * Gets current state of all lines.
   *
   * @return A set of all lines with statuses.
   */
  List<LineStatus> getCurrentState();
}
