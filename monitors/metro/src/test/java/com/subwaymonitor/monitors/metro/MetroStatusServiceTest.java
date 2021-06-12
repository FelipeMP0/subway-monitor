package com.subwaymonitor.monitors.metro;

import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import com.google.common.truth.Truth;
import com.subwaymonitor.sharedmodel.ImmutableLineCurrentStatus;
import com.subwaymonitor.sharedmodel.LineCurrentStatus;
import com.subwaymonitor.sharedmodel.StatusEnum;
import java.time.LocalDateTime;
import java.util.List;
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
        ImmutableList.of(
            ImmutableLineCurrentStatus.builder()
                .lineNumber(1)
                .statusSlug(StatusEnum.NORMAL_OPERATION)
                .build(),
            ImmutableLineCurrentStatus.builder()
                .lineNumber(2)
                .statusSlug(StatusEnum.OPERATION_CLOSED)
                .build(),
            ImmutableLineCurrentStatus.builder()
                .lineNumber(3)
                .statusSlug(StatusEnum.REDUCED_SPEED)
                .build(),
            ImmutableLineCurrentStatus.builder()
                .lineNumber(15)
                .statusSlug(StatusEnum.UNKNOWN)
                .build());
    Truth.assertThat(result).isEqualTo(expected);
  }
}
