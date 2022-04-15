package com.subwaymonitor.datastore;

import com.subwaymonitor.sharedmodel.Line;

/** Repository responsible for datastore interactions related to {@link Line}. */
public interface LineRepository {

  /**
   * Returns the matching {@link Line} for the given line and company identifier. Each line has a
   * string identifier on a transport system.
   *
   * @param companyLineId String identifier for a line in the transport system.
   * @param companySlug String identifier for the owning company.
   * @return The matching {@link Line}.
   */
  Line getByCompanyLineIdAndCompanySlug(String companyLineId, String companySlug);
}
