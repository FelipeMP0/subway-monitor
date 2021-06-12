package com.subwaymonitor.sharedmodel;

import org.immutables.value.Value;

@Value.Immutable
public interface Status {

  StatusEnum slug();

  String name();
}
