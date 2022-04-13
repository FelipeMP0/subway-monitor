package com.subwaymonitor.server.domains.ping;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A simple controller for test purposes. Only useful to test the communication between a client and
 * the server.
 */
@RestController
@RequestMapping("v1/ping")
class PingController {

  private final Clock clock;

  @Autowired
  PingController(final Clock clock) {
    this.clock = clock;
  }

  @GetMapping()
  CompletableFuture<ResponseEntity<PingDTO>> ping() {
    final PingDTO pingDTO =
        PingDTO.builder()
            .currentTime(ZonedDateTime.ofInstant(clock.instant(), ZoneId.systemDefault()))
            .build();
    return CompletableFuture.completedFuture(ResponseEntity.ok(pingDTO));
  }
}
