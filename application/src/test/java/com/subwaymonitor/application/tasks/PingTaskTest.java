package com.subwaymonitor.application.tasks;

import static org.mockito.Mockito.verify;

import com.subwaymonitor.application.services.PingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PingTaskTest {

  private PingTask subject;

  private PingService pingService;

  @BeforeEach
  void setUp() {
    pingService = Mockito.mock(PingService.class);
    subject = new PingTask(pingService);
  }

  @Test
  void pingApplication_success() {
    subject.pingApplication();
    verify(pingService).ping();
  }
}
