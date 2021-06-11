package com.subwaymonitor.application.services;

import com.subwaymonitor.datastore.VerificationRepository;
import com.subwaymonitor.sharedmodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {

  private final VerificationRepository repository;

  @Qualifier("MetroStatusService")
  private final SubwayStatusService metroStatusService;

  @Autowired
  public VerificationService(
      final VerificationRepository repository, final SubwayStatusService metroStatusService) {
    this.repository = repository;
    this.metroStatusService = metroStatusService;
  }

  public void verifyCurrentStatuses() {
    metroStatusService.findLineStatuses();
  }
}
