package com.subwaymonitor.server.dto.shared.mappers;

import com.subwaymonitor.server.dto.shared.LineStatusDto;
import com.subwaymonitor.server.dto.shared.VerificationDto;
import com.subwaymonitor.sharedmodel.Verification;
import java.util.List;
import java.util.stream.Collectors;

public final class VerificationMapper {

  private VerificationMapper() {}

  public static VerificationDto toDto(final Verification verification) {
    final List<LineStatusDto> lineStatusDtos =
        verification.lineStatuses().stream()
            .map(LineStatusMapper::toDto)
            .collect(Collectors.toList());
    return new VerificationDto(lineStatusDtos, verification.createdAt());
  }
}
