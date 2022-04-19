package com.subwaymonitor.server.dto.shared;

import java.time.LocalDateTime;
import java.util.List;

public record VerificationDto(List<LineStatusDto> lineStatuses, LocalDateTime createdAt) {}
