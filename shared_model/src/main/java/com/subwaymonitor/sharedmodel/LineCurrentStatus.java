package com.subwaymonitor.sharedmodel;

/**
 * Wrapper class that represents the current status of a line.
 *
 * @param line Line representation.
 * @param status Current status of the line.
 */
public record LineCurrentStatus(Line line, StatusEnum status) {}
