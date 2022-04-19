package com.subwaymonitor.sharedmodel;

import java.util.List;

/**
 * Represents a verification of the current statuses of a list of lines in a given moment.
 *
 * @param lineStatuses List of statuses and lines.
 */
public record Verification(List<LineStatus> lineStatuses) {}
