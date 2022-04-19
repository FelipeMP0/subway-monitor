package com.subwaymonitor.server.dto.lines;

import com.subwaymonitor.server.dto.shared.LineStatusDto;
import java.util.List;

public record LinesCurrentStatusResponse(List<LineStatusDto> linesCurrentStatus) {}
