package com.subwaymonitor.application.services;

import static com.subwaymonitor.sharedmodel.StatusEnum.NORMAL_OPERATION;
import static com.subwaymonitor.sharedmodel.StatusEnum.REDUCED_SPEED;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.subwaymonitor.datastore.LineRepository;
import com.subwaymonitor.datastore.StatusRepository;
import com.subwaymonitor.datastore.VerificationRepository;
import com.subwaymonitor.sharedmodel.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class VerificationServiceTest {

  private VerificationService subject;

  private VerificationRepository repository;
  private LineRepository lineRepository;
  private StatusRepository statusRepository;

  private SubwayStatusService subwayStatusService;

  private static final int LINE_1_NUMBER = 1;
  private static final int LINE_2_NUMBER = 2;

  private static final Line LINE_1 =
      ImmutableLine.builder().slug("line-1").number(LINE_1_NUMBER).name("Line 1").build();
  private static final Line LINE_2 =
      ImmutableLine.builder().slug("line-2").number(LINE_2_NUMBER).name("Line 2").build();
  private static final Status STATUS_1 =
      ImmutableStatus.builder().status(NORMAL_OPERATION).name(NORMAL_OPERATION.name()).build();
  private static final Status STATUS_2 =
      ImmutableStatus.builder().status(REDUCED_SPEED).name(REDUCED_SPEED.name()).build();

  @BeforeEach
  void setUp() {
    subwayStatusService = Mockito.mock(SubwayStatusService.class);
    repository = Mockito.mock(VerificationRepository.class);
    lineRepository = Mockito.mock(LineRepository.class);
    statusRepository = Mockito.mock(StatusRepository.class);
    subject =
        new VerificationService(repository, lineRepository, statusRepository, subwayStatusService);
  }

  @Test
  void verifyCurrentStatuses_success() {
    final List<LineCurrentStatus> lineCurrentStatuses = buildDefaultLineCurrentStatuses();
    when(subwayStatusService.findLineStatuses()).thenReturn(lineCurrentStatuses);
    when(lineRepository.getByNumber(LINE_1_NUMBER)).thenReturn(LINE_1);
    when(lineRepository.getByNumber(LINE_2_NUMBER)).thenReturn(LINE_2);
    when(statusRepository.getBySlug(NORMAL_OPERATION)).thenReturn(STATUS_1);
    when(statusRepository.getBySlug(REDUCED_SPEED)).thenReturn(STATUS_2);
    final Verification verification =
        ImmutableVerification.builder()
            .addAllLineStatuses(
                Arrays.asList(
                    ImmutableLineStatus.builder().line(LINE_1).status(STATUS_1).build(),
                    ImmutableLineStatus.builder().line(LINE_2).status(STATUS_2).build()))
            .build();
    subject.verifyCurrentStatuses();
    verify(repository).create(verification);
  }

  private List<LineCurrentStatus> buildDefaultLineCurrentStatuses() {
    return Arrays.asList(
        ImmutableLineCurrentStatus.builder()
            .lineNumber(LINE_1_NUMBER)
            .status(NORMAL_OPERATION)
            .build(),
        ImmutableLineCurrentStatus.builder()
            .lineNumber(LINE_2_NUMBER)
            .status(REDUCED_SPEED)
            .build());
  }
}
