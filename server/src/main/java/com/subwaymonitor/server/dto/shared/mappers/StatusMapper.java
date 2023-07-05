package com.subwaymonitor.server.dto.shared.mappers;

import com.subwaymonitor.server.dto.shared.StatusDto;
import com.subwaymonitor.sharedmodel.Status;

public final class StatusMapper {

  private StatusMapper() {}

  public static StatusDto toDto(final Status status) {
    return new StatusDto(status.name());
  }
}
