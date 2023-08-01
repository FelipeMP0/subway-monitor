package com.subwaymonitor.application.tasks;

import com.subwaymonitor.application.services.VerificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class ArchiveVerificationsTask {

  private static final Logger LOGGER = LoggerFactory.getLogger(ArchiveVerificationsTask.class);

  private final VerificationService verificationService;

  @Autowired
  ArchiveVerificationsTask(final VerificationService verificationService) {
    this.verificationService = verificationService;
  }

  // @Scheduled(cron = "${application.schedulers.archiveVerifications.cron-expression}")
  void run() {
    LOGGER.info("Running archive verifications task");
    verificationService.archive();
  }
}
