package com.subwaymonitor.application.tasks;

import com.subwaymonitor.application.services.VerificationService;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class ArchiveVerificationsTask {

  private static final Logger LOGGER = LoggerFactory.getLogger(ArchiveVerificationsTask.class);

  private final VerificationService verificationService;

  @Autowired
  ArchiveVerificationsTask(final VerificationService verificationService) {
    this.verificationService = verificationService;
  }

  @Scheduled(cron = "${application.schedulers.archiveVerificationsTask.cron-expression}")
  void run() throws ExecutionException, InterruptedException {
    LOGGER.info("Running archive verifications task");
    verificationService.archiveOldVerifications();
    LOGGER.info("Verifications successfully archived");
  }
}
