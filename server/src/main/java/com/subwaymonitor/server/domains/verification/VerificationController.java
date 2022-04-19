package com.subwaymonitor.server.domains.verification;

import com.subwaymonitor.application.services.VerificationService;
import com.subwaymonitor.server.dto.shared.VerificationDto;
import com.subwaymonitor.server.dto.shared.mappers.VerificationMapper;
import com.subwaymonitor.sharedmodel.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/verifications")
class VerificationController {

  private final VerificationService service;

  @Autowired
  VerificationController(final VerificationService service) {
    this.service = service;
  }

  @GetMapping("/last")
  VerificationDto getCurrentStatus() {
    final Verification verification = service.getLast();
    return VerificationMapper.INSTANCE.toDto(verification);
  }
}
