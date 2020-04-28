package com.subwaymonitor.sharedmodel;

import org.immutables.value.Value;

@Value.Immutable
public interface LineStatus {

  Integer lineNumber();

  String statusSlug();
}
