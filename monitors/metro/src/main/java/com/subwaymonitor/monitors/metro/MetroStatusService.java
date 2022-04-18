package com.subwaymonitor.monitors.metro;

import com.subwaymonitor.config.MonitorConfig;
import com.subwaymonitor.sharedmodel.*;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/** Service class that performs the necessary actions to find statuses of the Metro's lines. */
@Service
@Qualifier("MetroStatusService")
class MetroStatusService implements SubwayStatusService {

  private static final Logger LOGGER = LoggerFactory.getLogger(MetroStatusService.class);

  private static final String COMPANY_SLUG = "METRO_SAO_PAULO";

  private final MonitorConfig<MetroApiServiceProperties> config;
  private final MetroApiService metroApiService;

  @Autowired
  public MetroStatusService(
      final MonitorConfig<MetroApiServiceProperties> monitorConfig,
      final MetroApiService metroApiService) {
    this.config = monitorConfig;
    this.metroApiService = metroApiService;
  }

  /**
   * Finds the currents statuses of Metro's lines and then adapt the results to the shared model.
   *
   * @return A list of {@link LineCurrentStatus} containing an adapted version the current statuses
   *     of the lines.
   */
  @Override
  public CompletableFuture<List<LineCurrentStatus>> findLineStatuses() {
    return metroApiService
        .getStatuses()
        .thenApplyAsync(
            response -> {
              final var statusMetro = response.statusMetro();
              return statusMetro
                  .lineStatuses()
                  .stream()
                  .map(
                      lineStatus ->
                          ImmutableLineCurrentStatus.builder()
                              .line(
                                  ImmutableLine.builder()
                                      .companyLineId(lineStatus.id())
                                      .companySlug(COMPANY_SLUG)
                                      .name(lineStatus.lineFullName())
                                      .build())
                              .status(convertStatus(lineStatus.statusDescription()))
                              .build())
                  .collect(Collectors.toList());
            },
            config.executor());
  }

  private StatusEnum convertStatus(String statusDescription) {
    statusDescription = statusDescription.toLowerCase(Locale.ROOT);
    if (statusDescription.endsWith("normal")) {
      return StatusEnum.NORMAL_OPERATION;
    } else if ("Velocidade Reduzida".equalsIgnoreCase(statusDescription)) {
      return StatusEnum.REDUCED_SPEED;
    } else if (statusDescription.endsWith("encerrada")) {
      return StatusEnum.OPERATION_CLOSED;
    } else if (statusDescription.endsWith("interrompida")) {
      return StatusEnum.OPERATION_INTERRUPTED;
    } else {
      LOGGER.warn("Unknown status metro status description = {}", statusDescription);
      return StatusEnum.UNKNOWN;
    }
  }
}
