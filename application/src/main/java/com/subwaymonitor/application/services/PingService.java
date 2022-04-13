package com.subwaymonitor.application.services;

import com.subwaymonitor.monitors.ping.PingApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

/** Service class responsible for calling the ping endpoint in the application. */
@Service
public class PingService {

  private static final Logger LOGGER = LoggerFactory.getLogger(PingService.class);

  private final PingApiService pingApiService;

  @Autowired
  public PingService(final PingApiService pingApiService) {
    this.pingApiService = pingApiService;
  }

  public void ping() {
    try {
      pingApiService.ping();
      LOGGER.info("Successfully pinged the application");
    } catch (HttpClientErrorException | HttpServerErrorException exception) {
      LOGGER.warn("Ping request failed");
    }
  }
}
