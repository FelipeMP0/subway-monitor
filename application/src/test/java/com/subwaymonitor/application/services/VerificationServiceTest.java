package com.subwaymonitor.application.services;

import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import com.subwaymonitor.datastore.VerificationRepository;
import com.subwaymonitor.sharedmodel.ImmutableLineCurrentStatus;
import com.subwaymonitor.sharedmodel.LineCurrentStatus;
import com.subwaymonitor.sharedmodel.SubwayStatusService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class VerificationServiceTest {

  private VerificationService subject;

  private VerificationRepository verificationRepository;

  private SubwayStatusService subwayStatusService;

  @BeforeEach
  void setUp() {
    subwayStatusService = Mockito.mock(SubwayStatusService.class);
    verificationRepository = Mockito.mock(VerificationRepository.class);
    subject = new VerificationService(verificationRepository, subwayStatusService);
  }

  @Test
  void verifyCurrentStatuses_success() {
    final List<LineCurrentStatus> lineCurrentStatuses = buildDefaultLineCurrentStatuses();
    when(subwayStatusService.findLineStatuses()).thenReturn(lineCurrentStatuses);
    subject.verifyCurrentStatuses();
  }

  private List<LineCurrentStatus> buildDefaultLineCurrentStatuses() {
    return ImmutableList.of(
        ImmutableLineCurrentStatus.builder().lineNumber(1).statusSlug("status1").build(),
        ImmutableLineCurrentStatus.builder().lineNumber(2).statusSlug("status2").build());
  }
}
