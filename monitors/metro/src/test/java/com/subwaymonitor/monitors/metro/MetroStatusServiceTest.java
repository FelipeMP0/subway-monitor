package com.subwaymonitor.monitors.metro;

import static org.mockito.Mockito.when;

import com.subwaymonitor.sharedmodel.ImmutableLineCurrentStatus;
import com.subwaymonitor.sharedmodel.LineCurrentStatus;
import com.subwaymonitor.sharedmodel.StatusEnum;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MetroStatusServiceTest {

  private MetroStatusService subject;

  private MetroApiService metroApiService;

  @BeforeEach
  void setUp() {
    metroApiService = Mockito.mock(MetroApiService.class);
    subject = new MetroStatusService(metroApiService);
  }

  @Test
  void findLineStatuses_success() {
    final MetroApiResponse metroApiResponse =
        ImmutableMetroApiResponse.builder()
            .statusMetro(
                ImmutableStatusMetro.builder()
                    .dateUpdateMetro(LocalDateTime.now())
                    .addAllLineStatuses(TestHelper.buildLineStatuses())
                    .build())
            .build();
    when(metroApiService.getStatuses()).thenReturn(metroApiResponse);
    final List<LineCurrentStatus> result = subject.findLineStatuses();
    final List<LineCurrentStatus> expected =
        Arrays.asList(
            ImmutableLineCurrentStatus.builder()
                .lineNumber(1)
                .status(StatusEnum.NORMAL_OPERATION)
                .build(),
            ImmutableLineCurrentStatus.builder()
                .lineNumber(2)
                .status(StatusEnum.OPERATION_CLOSED)
                .build(),
            ImmutableLineCurrentStatus.builder()
                .lineNumber(3)
                .status(StatusEnum.REDUCED_SPEED)
                .build(),
            ImmutableLineCurrentStatus.builder().lineNumber(15).status(StatusEnum.UNKNOWN).build());
    Assertions.assertEquals(expected, result);
  }
}
