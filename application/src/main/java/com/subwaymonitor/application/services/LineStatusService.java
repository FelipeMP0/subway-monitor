package com.subwaymonitor.application.services;

import com.subwaymonitor.datastore.LineStatusRepository;
import com.subwaymonitor.sharedmodel.LineStatus;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineStatusService {

  private final LineStatusRepository repository;

  @Autowired
  public LineStatusService(final LineStatusRepository repository) {
    this.repository = repository;
  }

  public List<LineStatus> getCurrentStatus() {
    return repository.getCurrentState();
  }
}
