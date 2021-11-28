package com.subwaymonitor.datastore;

import com.subwaymonitor.sharedmodel.Status;
import com.subwaymonitor.sharedmodel.StatusEnum;

/** Repository for datastore operations related to {@link Status}. */
public interface StatusRepository {

  /**
   * Returns the matching {@link Status} for the given slug.
   *
   * @param slug The enum representing the status slug.
   * @return An instance of {@link Status} representing the matching slug.
   */
  Status getBySlug(StatusEnum slug);
}
