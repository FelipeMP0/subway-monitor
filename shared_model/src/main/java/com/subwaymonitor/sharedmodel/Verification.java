package com.subwaymonitor.sharedmodel;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a verification of the current statuses of a list of lines in a given moment.
 *
 * @param lineStatuses List of statuses and lines.
 * @param createdAt Date and time of creation.
 */
public record Verification(List<LineStatus> lineStatuses, LocalDateTime createdAt) {}
