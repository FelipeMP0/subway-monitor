package com.subwaymonitor.sharedmodel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Interface that provides methods to archive old data that should be removed from the regular
 * datastore.
 */
public interface ArchiveService {

  /**
   * Archives a list of line statuses.
   *
   * @return the future containing the result of the operation.
   */
  CompletableFuture<Void> archiveLineStatus(final List<LineStatus> lineStatusList);
}
