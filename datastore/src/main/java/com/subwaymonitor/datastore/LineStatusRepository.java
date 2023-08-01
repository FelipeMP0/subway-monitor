package com.subwaymonitor.datastore;

import com.subwaymonitor.sharedmodel.LineStatus;
import com.subwaymonitor.sharedmodel.StatusEnum;
import java.time.LocalDateTime;
import java.util.List;

/** Repository responsible for datastore interactions related to {@link LineStatus}. */
public interface LineStatusRepository {

  /**
   * Finds all the line-statusEnumList entries for a given database until a specified data-time.
   *
   * @param statusEnumList The statusEnumList to filter.
   * @param until The end date-time of the search.
   * @return A list of {@link LineStatus}.
   */
  List<LineStatus> findByStatus(List<StatusEnum> statusEnumList, LocalDateTime until);
}
