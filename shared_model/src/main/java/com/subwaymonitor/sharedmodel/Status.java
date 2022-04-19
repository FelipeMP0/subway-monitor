package com.subwaymonitor.sharedmodel;

/**
 * Represent a possible status for a line.
 *
 * @param status Unique identifier of a status.
 * @param name Display name for the status.
 */
public record Status(StatusEnum status, String name) {}
