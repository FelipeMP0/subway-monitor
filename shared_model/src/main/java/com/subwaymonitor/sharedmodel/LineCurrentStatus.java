package com.subwaymonitor.sharedmodel;

import org.immutables.value.Value;

@Value.Immutable
public interface LineCurrentStatus {

  Integer lineNumber();

  String statusSlug();
}
