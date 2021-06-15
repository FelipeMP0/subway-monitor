package com.subwaymonitor.monitors.metro;

import com.subwaymonitor.sharedmodel.ImmutableLineCurrentStatus;
import com.subwaymonitor.sharedmodel.LineCurrentStatus;
import com.subwaymonitor.sharedmodel.StatusEnum;
import com.subwaymonitor.sharedmodel.SubwayStatusService;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("MetroStatusService")
class MetroStatusService implements SubwayStatusService {

  private static final Logger LOGGER = LoggerFactory.getLogger(MetroStatusService.class);

  private final MetroApiService metroApiService;

  @Autowired
  public MetroStatusService(final MetroApiService metroApiService) {
    this.metroApiService = metroApiService;
  }

  @Override
  public List<LineCurrentStatus> findLineStatuses() {
    final var metroApiResponse = metroApiService.getStatuses();

    final var statusMetro = metroApiResponse.statusMetro();

    return statusMetro
        .lineStatuses()
        .stream()
        .map(
            lineStatus ->
                ImmutableLineCurrentStatus.builder()
                    .lineNumber(Integer.parseInt(lineStatus.id()))
                    .statusSlug(convertStatus(lineStatus.statusDescription()))
                    .build())
        .collect(Collectors.toList());
  }

  private StatusEnum convertStatus(String statusDescription) {
    if ("Operação Normal".equalsIgnoreCase(statusDescription)) {
      return StatusEnum.NORMAL_OPERATION;
    } else if ("Velocidade Reduzida".equalsIgnoreCase(statusDescription)) {
      return StatusEnum.REDUCED_SPEED;
    } else if ("Operação Encerrada".contentEquals(statusDescription)) {
      return StatusEnum.OPERATION_CLOSED;
    } else if ("Operação Interrompida".equalsIgnoreCase(statusDescription)) {
      return StatusEnum.OPERATION_INTERRUPTED;
    } else {
      LOGGER.warn("Unknown status metro status description = {}", statusDescription);
      return StatusEnum.UNKNOWN;
    }
  }
}
