package com.subwaymonitor.serialization;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

  private DateTimeUtils() {}

  public static String formatLocalDataTime(final LocalDateTime localDateTime) {
    final var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return localDateTime.format(formatter);
  }
}
