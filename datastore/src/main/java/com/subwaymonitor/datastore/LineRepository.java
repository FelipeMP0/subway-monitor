package com.subwaymonitor.datastore;

import com.subwaymonitor.sharedmodel.Line;

/** Repository responsible for datastore interactions related to {@link Line}. */
public interface LineRepository {

  /**
   * Returns the matching {@link Line} for the given integer number. Each line has an integer
   * identifier on the transport system.
   *
   * @param number Integer identifier for a line in the transport system.
   * @return The matching {@link Line}.
   */
  Line getByNumber(int number);
}
