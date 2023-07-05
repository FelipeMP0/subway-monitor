package com.subwaymonitor.server.dto.shared.mappers;

import com.subwaymonitor.server.dto.shared.LineDto;
import com.subwaymonitor.sharedmodel.Line;

public final class LineMapper {

  private LineMapper() {}

  public static LineDto toDto(final Line line) {
    return new LineDto(line.companyLineId(), line.companySlug(), line.name());
  }
}
