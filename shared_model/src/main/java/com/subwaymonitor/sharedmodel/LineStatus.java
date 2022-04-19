package com.subwaymonitor.sharedmodel;

/**
 * Wrapper class representing the status of a line on a given moment.
 *
 * @param line Complete information about a line.
 * @param status Complete information about a status.
 */
public record LineStatus(Line line, Status status) {}
