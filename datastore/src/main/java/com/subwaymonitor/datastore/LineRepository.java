package com.subwaymonitor.datastore;

import com.subwaymonitor.sharedmodel.Line;

public interface LineRepository {

  Line getByNumber(int number);
}
