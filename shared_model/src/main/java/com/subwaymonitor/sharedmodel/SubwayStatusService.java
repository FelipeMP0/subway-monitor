package com.subwaymonitor.sharedmodel;

import java.util.List;

public interface SubwayStatusService {

  List<LineCurrentStatus> findLineStatuses();
}
