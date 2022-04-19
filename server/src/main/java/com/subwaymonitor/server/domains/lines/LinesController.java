package com.subwaymonitor.server.domains.lines;

import com.subwaymonitor.application.services.LineStatusService;
import com.subwaymonitor.server.dto.lines.LinesCurrentStatusResponse;
import com.subwaymonitor.server.dto.shared.LineStatusDto;
import com.subwaymonitor.server.dto.shared.mappers.LineStatusMapper;
import com.subwaymonitor.sharedmodel.LineStatus;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/lines")
class LinesController {

  private final LineStatusService lineStatusService;

  @Autowired
  LinesController(final LineStatusService lineStatusService) {
    this.lineStatusService = lineStatusService;
  }

  @GetMapping("/currentStatus")
  LinesCurrentStatusResponse getCurrentStatus() {
    final List<LineStatus> currentStatuses = lineStatusService.getCurrentStatus();
    final List<LineStatusDto> lineStatusDtos =
        currentStatuses.stream().map(LineStatusMapper.INSTANCE::lineStatusToLineStatusDto).toList();
    return new LinesCurrentStatusResponse(lineStatusDtos);
  }
}
