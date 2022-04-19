package com.subwaymonitor.application.tasks;

import com.subwaymonitor.application.services.VerificationService;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class VerificationTask {

  private static final Logger LOGGER = LoggerFactory.getLogger(VerificationTask.class);

  private final VerificationService verificationService;

  @Autowired
  VerificationTask(final VerificationService verificationService) {
    this.verificationService = verificationService;
  }

  @Scheduled(cron = "${application.schedulers.verificationTask.cron-expression}")
  void verifyLinesCurrentStatus() throws ExecutionException, InterruptedException {
    LOGGER.info("Running line current status verification task");
    verificationService.verifyCurrentStatuses();
  }
}
