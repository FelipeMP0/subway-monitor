package com.subwaymonitor.application.tasks;

import static org.mockito.Mockito.verify;

import com.subwaymonitor.application.services.VerificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class VerificationTaskTest {

  private VerificationTask subject;

  private VerificationService verificationService;

  @BeforeEach
  void setup() {
    verificationService = Mockito.mock(VerificationService.class);
    subject = new VerificationTask(verificationService);
  }

  @Test
  void verifyLinesCurrentStatus_success() {
    subject.verifyLinesCurrentStatus();
    verify(verificationService).verifyCurrentStatuses();
  }
}
