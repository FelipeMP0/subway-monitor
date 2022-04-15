package com.subwaymonitor.sharedmodel;

import org.immutables.value.Value;

/** Represent a line in a transport system. */
@Value.Immutable
public interface Line {

  /** A unique identifier for the line within its owning company. */
  String companyLineId();

  /** Owning company slug. */
  String companySlug();

  /** The display name of the line. */
  String name();
}
