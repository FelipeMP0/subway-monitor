package com.subwaymonitor.server.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/ping")
class PingController {

  @GetMapping()
  ResponseEntity<String> ping() {
    return ResponseEntity.ok("pong");
  }
}
