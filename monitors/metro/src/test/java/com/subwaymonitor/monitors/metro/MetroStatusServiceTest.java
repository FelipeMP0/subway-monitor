package com.subwaymonitor.monitors.metro;

import static org.mockito.Mockito.when;

import com.subwaymonitor.config.MonitorConfig;
import com.subwaymonitor.sharedmodel.ImmutableLine;
import com.subwaymonitor.sharedmodel.ImmutableLineCurrentStatus;
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
        ImmutableMetroApiResponse.builder()
            .statusMetro(
                ImmutableStatusMetro.builder()
                    .dateUpdateMetro(LocalDateTime.now())
                    .addAllLineStatuses(TestHelper.buildLineStatuses())
                    .build())
            .build();
    when(metroApiService.getStatuses())
        .thenReturn(CompletableFuture.completedFuture(metroApiResponse));
    final List<LineCurrentStatus> result = subject.findLineStatuses().get();
    final List<LineCurrentStatus> expected =
        List.of(
            ImmutableLineCurrentStatus.builder()
                .line(
                    ImmutableLine.builder()
                        .companyLineId("1")
                        .companySlug("METRO_SAO_PAULO")
                        .name("Linha 1 - Azul")
                        .build())
                .status(StatusEnum.NORMAL_OPERATION)
                .build(),
            ImmutableLineCurrentStatus.builder()
                .line(
                    ImmutableLine.builder()
                        .companyLineId("2")
                        .companySlug("METRO_SAO_PAULO")
                        .name("Linha 2 - Verde")
                        .build())
                .status(StatusEnum.OPERATION_CLOSED)
                .build(),
            ImmutableLineCurrentStatus.builder()
                .line(
                    ImmutableLine.builder()
                        .companyLineId("3")
                        .companySlug("METRO_SAO_PAULO")
                        .name("Linha 3 - Vermelha")
                        .build())
                .status(StatusEnum.REDUCED_SPEED)
                .build(),
            ImmutableLineCurrentStatus.builder()
                .line(
                    ImmutableLine.builder()
                        .companyLineId("15")
                        .companySlug("METRO_SAO_PAULO")
                        .name("Linha 15 - Prata")
                        .build())
                .status(StatusEnum.UNKNOWN)
                .build());
    Assertions.assertEquals(expected, result);
  }
}
