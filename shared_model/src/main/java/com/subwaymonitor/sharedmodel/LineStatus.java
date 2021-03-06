package com.subwaymonitor.sharedmodel;

import org.immutables.value.Value;

@Value.Immutable
public interface LineStatus {

  Line line();

  Status status();
}
