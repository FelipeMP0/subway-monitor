package com.subwaymonitor.server.domains.ping;

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

  @GetMapping()
  ResponseEntity<PingDTO> ping() {
    final PingDTO pingDTO = PingDTO.builder().build();
    return ResponseEntity.ok(pingDTO);
  }
}
