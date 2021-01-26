package com.subwaymonitor.sharedmodel;

import org.immutables.value.Value;

@Value.Immutable
public interface Line {

  String slug();

  String name();

  Integer number();
}
