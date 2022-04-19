package com.subwaymonitor.application.services;

import static com.subwaymonitor.sharedmodel.StatusEnum.NORMAL_OPERATION;
import static com.subwaymonitor.sharedmodel.StatusEnum.REDUCED_SPEED;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.subwaymonitor.datastore.LineRepository;
import com.subwaymonitor.datastore.StatusRepository;
import com.subwaymonitor.datastore.VerificationRepository;
import com.subwaymonitor.sharedmodel.Line;
import com.subwaymonitor.sharedmodel.LineCurrentStatus;
import com.subwaymonitor.sharedmodel.LineStatus;
import com.subwaymonitor.sharedmodel.Status;
import com.subwaymonitor.sharedmodel.SubwayStatusService;
import com.subwaymonitor.sharedmodel.Verification;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class VerificationServiceTest {

  private VerificationService subject;

  private VerificationRepository repository;
  private LineRepository lineRepository;
  private StatusRepository statusRepository;

  private SubwayStatusService subwayStatusService;

  private static final String LINE_1_NUMBER_IDENTIFIER = "1";
  private static final String LINE_2_NUMBER_IDENTIFIER = "2";
  private static final String COMPANY_1_SLUG = "company_1";

  private static final Line LINE_1 = new Line(LINE_1_NUMBER_IDENTIFIER, COMPANY_1_SLUG, "Line 1");
  private static final Line LINE_2 = new Line(LINE_2_NUMBER_IDENTIFIER, COMPANY_1_SLUG, "Line 2");
  private static final Status STATUS_1 = new Status(NORMAL_OPERATION, NORMAL_OPERATION.name());
  private static final Status STATUS_2 = new Status(REDUCED_SPEED, REDUCED_SPEED.name());

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
  void verifyCurrentStatuses_success() throws ExecutionException, InterruptedException {
    final List<LineCurrentStatus> lineCurrentStatuses = buildDefaultLineCurrentStatuses();
    when(subwayStatusService.findLineStatuses())
        .thenReturn(CompletableFuture.completedFuture(lineCurrentStatuses));
    when(lineRepository.getByCompanyLineIdAndCompanySlug(LINE_1_NUMBER_IDENTIFIER, COMPANY_1_SLUG))
        .thenReturn(LINE_1);
    when(lineRepository.getByCompanyLineIdAndCompanySlug(LINE_2_NUMBER_IDENTIFIER, COMPANY_1_SLUG))
        .thenReturn(LINE_2);
    when(statusRepository.getBySlug(NORMAL_OPERATION)).thenReturn(STATUS_1);
    when(statusRepository.getBySlug(REDUCED_SPEED)).thenReturn(STATUS_2);
    final Verification verification =
        new Verification(
            List.of(new LineStatus(LINE_1, STATUS_1), new LineStatus(LINE_2, STATUS_2)));
    subject.verifyCurrentStatuses();
    verify(repository).create(verification);
  }

  private List<LineCurrentStatus> buildDefaultLineCurrentStatuses() {
    return List.of(
        new LineCurrentStatus(LINE_1, NORMAL_OPERATION),
        new LineCurrentStatus(LINE_2, REDUCED_SPEED));
  }
}
