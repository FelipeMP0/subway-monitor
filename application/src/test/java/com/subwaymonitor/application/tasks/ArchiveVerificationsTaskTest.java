package com.subwaymonitor.application.tasks;

import static org.mockito.Mockito.verify;

import com.subwaymonitor.application.services.VerificationService;
import java.util.concurrent.ExecutionException;
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
  void run_success() throws ExecutionException, InterruptedException {
    subject.run();
    verify(verificationService).archiveOldVerifications();
  }
}
