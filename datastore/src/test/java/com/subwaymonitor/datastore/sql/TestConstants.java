package com.subwaymonitor.datastore.sql;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

final class TestConstants {

  private TestConstants() {}

  public static final LocalDateTime NOW = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
  public static final String COMPANY_SLUG = "company_slug";
}
