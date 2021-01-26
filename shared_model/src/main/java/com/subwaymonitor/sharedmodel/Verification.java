package com.subwaymonitor.sharedmodel;

import java.util.List;
import org.immutables.value.Value;

@Value.Immutable
public interface Verification {

  List<LineStatus> lineStatuses();
}
