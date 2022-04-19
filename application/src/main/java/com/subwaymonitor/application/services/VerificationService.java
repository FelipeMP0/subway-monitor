package com.subwaymonitor.application.services;

import com.subwaymonitor.datastore.LineRepository;
import com.subwaymonitor.datastore.StatusRepository;
import com.subwaymonitor.datastore.VerificationRepository;
import com.subwaymonitor.sharedmodel.Line;
import com.subwaymonitor.sharedmodel.LineCurrentStatus;
import com.subwaymonitor.sharedmodel.LineStatus;
import com.subwaymonitor.sharedmodel.Status;
import com.subwaymonitor.sharedmodel.SubwayStatusService;
import com.subwaymonitor.sharedmodel.Verification;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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

  private final Clock clock;

  private final VerificationRepository repository;
  private final LineRepository lineRepository;
  private final StatusRepository statusRepository;

  @Qualifier("MetroStatusService")
  private final SubwayStatusService metroStatusService;

  @Autowired
  public VerificationService(
      final Clock clock,
      final VerificationRepository repository,
      final LineRepository lineRepository,
      final StatusRepository statusRepository,
      final SubwayStatusService metroStatusService) {
    this.clock = clock;
    this.repository = repository;
    this.lineRepository = lineRepository;
    this.statusRepository = statusRepository;
    this.metroStatusService = metroStatusService;
  }

  /** Verifies the current state of the lines and then persist them in a database. */
  @Transactional
  public void verifyCurrentStatuses() throws ExecutionException, InterruptedException {
    final List<CompletableFuture<List<LineCurrentStatus>>> lineStatusesFutures =
        List.of(metroStatusService.findLineStatuses());
    final List<LineCurrentStatus> lineCurrentStatuses = new ArrayList<>();
    CompletableFuture.allOf(lineStatusesFutures.toArray(new CompletableFuture[0]))
        .thenAccept(
            result ->
                lineStatusesFutures.stream()
                    .map(CompletableFuture::join)
                    .flatMap(Collection::stream)
                    .forEach(lineCurrentStatuses::add))
        .get();
    final List<LineStatus> lineStatuses =
        lineCurrentStatuses.stream()
            .map(
                lineCurrentStatus -> {
                  final var currentLine = lineCurrentStatus.line();
                  final Line line =
                      lineRepository.getByCompanyLineIdAndCompanySlug(
                          currentLine.companyLineId(), currentLine.companySlug());
                  final Status status = statusRepository.getBySlug(lineCurrentStatus.status());
                  return buildLineStatus(line, status);
                })
            .toList();
    final Verification verification = new Verification(lineStatuses, LocalDateTime.now(clock));
    repository.create(verification);
  }

  public Verification getLast() {
    return repository.getLast();
  }

  private LineStatus buildLineStatus(final Line line, final Status status) {
    return new LineStatus(line, status);
  }
}
