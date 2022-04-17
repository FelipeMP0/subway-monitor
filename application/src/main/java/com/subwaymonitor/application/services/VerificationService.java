package com.subwaymonitor.application.services;

import com.subwaymonitor.datastore.LineRepository;
import com.subwaymonitor.datastore.StatusRepository;
import com.subwaymonitor.datastore.VerificationRepository;
import com.subwaymonitor.sharedmodel.*;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for verifying the current state of the lines and to persist the
 * verified state.
 */
@Service
public class VerificationService {

  private final VerificationRepository repository;
  private final LineRepository lineRepository;
  private final StatusRepository statusRepository;

  @Qualifier("MetroStatusService")
  private final SubwayStatusService metroStatusService;

  @Autowired
  public VerificationService(
      final VerificationRepository repository,
      final LineRepository lineRepository,
      final StatusRepository statusRepository,
      final SubwayStatusService metroStatusService) {
    this.repository = repository;
    this.lineRepository = lineRepository;
    this.statusRepository = statusRepository;
    this.metroStatusService = metroStatusService;
  }

  /** Verifies the current state of the lines and then persist them in a database. */
  @Transactional
  public void verifyCurrentStatuses() {
    final List<CompletableFuture<List<LineCurrentStatus>>> lineStatusesFutures =
        List.of(metroStatusService.findLineStatuses());
    CompletableFuture.allOf(lineStatusesFutures.toArray(new CompletableFuture[0]))
        .thenAccept(
            result -> {
              final List<LineCurrentStatus> lineCurrentStatuses =
                  lineStatusesFutures
                      .stream()
                      .map(CompletableFuture::join)
                      .flatMap(Collection::stream)
                      .collect(Collectors.toList());
              final List<LineStatus> lineStatuses =
                  lineCurrentStatuses
                      .stream()
                      .map(
                          lineCurrentStatus -> {
                            final var currentLine = lineCurrentStatus.line();
                            final var line =
                                lineRepository.getByCompanyLineIdAndCompanySlug(
                                    currentLine.companyLineId(), currentLine.companySlug());
                            final var status =
                                statusRepository.getBySlug(lineCurrentStatus.status());
                            return buildLineStatus(line, status);
                          })
                      .collect(Collectors.toList());
              final Verification verification =
                  ImmutableVerification.builder().addAllLineStatuses(lineStatuses).build();
              repository.create(verification);
            });
  }

  private LineStatus buildLineStatus(final Line line, final Status status) {
    return ImmutableLineStatus.builder().line(line).status(status).build();
  }
}
