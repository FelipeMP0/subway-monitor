package com.subwaymonitor.server.dto.shared.mappers;

import com.subwaymonitor.server.dto.shared.LineStatusDto;
import com.subwaymonitor.sharedmodel.LineStatus;

public final class LineStatusMapper {

  private LineStatusMapper() {}

  public static LineStatusDto toDto(final LineStatus lineStatus) {
    return new LineStatusDto(
        LineMapper.toDto(lineStatus.line()), StatusMapper.toDto(lineStatus.status()));
  }
}
