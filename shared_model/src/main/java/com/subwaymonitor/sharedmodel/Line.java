package com.subwaymonitor.sharedmodel;

import org.immutables.value.Value;

/** Represent a line in a transport system. */
@Value.Immutable
public interface Line {

  /** A unique identifier for the line. */
  String slug();

  /** The display name of the line. */
  String name();

  /** Numeric identifier of the line in the transport system. */
  Integer number();
}
