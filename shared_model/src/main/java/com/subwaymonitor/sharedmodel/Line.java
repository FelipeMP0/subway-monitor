package com.subwaymonitor.sharedmodel;

/**
 * Represent a line in a transport system.
 *
 * @param companyLineId A unique identifier for the line within its owning company.
 * @param companySlug Owning company slug.
 * @param name The display name of the line.
 */
public record Line(String companyLineId, String companySlug, String name) {}
