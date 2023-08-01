package com.subwaymonitor.datastore.sql;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

final class TestHelpers {

  private TestHelpers() {}

  public static String formatLocalDataTime(final LocalDateTime localDateTime) {
    final var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return localDateTime.format(formatter);
  }
}
