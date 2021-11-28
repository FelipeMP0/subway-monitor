package com.subwaymonitor.server.domains.ping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A simple controller for test purposes. Only useful to test the communication between a client and
 * the server.
 */
@RestController
@RequestMapping("v1/ping")
class PingController {

  //  @GetMapping()
  //  ResponseEntity<PingDTO> ping() {
  //    return ResponseEntity.ok("pong");
  //  }
}
