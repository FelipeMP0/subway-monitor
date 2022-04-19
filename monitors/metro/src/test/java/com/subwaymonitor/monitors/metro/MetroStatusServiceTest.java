package com.subwaymonitor.monitors.metro;

import static org.mockito.Mockito.when;

import com.subwaymonitor.config.MonitorConfig;
import com.subwaymonitor.sharedmodel.Line;
import com.subwaymonitor.sharedmodel.LineCurrentStatus;
import com.subwaymonitor.sharedmodel.StatusEnum;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MetroStatusServiceTest {

  private MetroStatusService subject;

  private MetroApiService metroApiService;

  @BeforeEach
  void setUp() {
    final MonitorConfig<MetroApiServiceProperties> config =
        TestConfig.getIntegrationConfigInstance();
    metroApiService = Mockito.mock(MetroApiService.class);
    subject = new MetroStatusService(config, metroApiService);
  }

  @Test
  void findLineStatuses_success() throws ExecutionException, InterruptedException {
    final MetroApiResponse metroApiResponse =
        new MetroApiResponse(
            new MetroApiResponse.StatusMetro(LocalDateTime.now(), TestHelper.buildLineStatuses()));
    when(metroApiService.getStatuses())
        .thenReturn(CompletableFuture.completedFuture(metroApiResponse));
    final List<LineCurrentStatus> result = subject.findLineStatuses().get();
    final List<LineCurrentStatus> expected =
        List.of(
            new LineCurrentStatus(
                new Line("1", "METRO_SAO_PAULO", "Linha 1 - Azul"), StatusEnum.NORMAL_OPERATION),
            new LineCurrentStatus(
                new Line("2", "METRO_SAO_PAULO", "Linha 2 - Verde"), StatusEnum.OPERATION_CLOSED),
            new LineCurrentStatus(
                new Line("3", "METRO_SAO_PAULO", "Linha 3 - Vermelha"), StatusEnum.REDUCED_SPEED),
            new LineCurrentStatus(
                new Line("15", "METRO_SAO_PAULO", "Linha 15 - Prata"), StatusEnum.UNKNOWN));
    Assertions.assertEquals(expected, result);
  }
}
