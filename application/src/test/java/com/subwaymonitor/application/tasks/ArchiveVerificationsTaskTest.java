package com.subwaymonitor.application.tasks;

import static org.mockito.Mockito.verify;

import com.subwaymonitor.application.services.VerificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ArchiveVerificationsTaskTest {

  private ArchiveVerificationsTask subject;

  private VerificationService verificationService;

  @BeforeEach
  void setUp() {
    verificationService = Mockito.mock(VerificationService.class);
    subject = new ArchiveVerificationsTask(verificationService);
  }

  @Test
  void run_success() {
    subject.run();
    verify(verificationService).archive();
  }
}
