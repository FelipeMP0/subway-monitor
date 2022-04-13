package com.subwaymonitor.application.tasks;

import com.subwaymonitor.application.services.PingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
final class PingTask {

  private static final Logger LOGGER = LoggerFactory.getLogger(PingTask.class);

  private final PingService service;

  @Autowired
  PingTask(final PingService service) {
    this.service = service;
  }

  @Scheduled(fixedRate = 180000)
  void pingApplication() {
    LOGGER.info("Sending ping request to application");
    service.ping();
  }
}
