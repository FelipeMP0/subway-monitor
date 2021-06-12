package com.subwaymonitor.datastore;

import com.subwaymonitor.sharedmodel.Status;
import com.subwaymonitor.sharedmodel.StatusEnum;

public interface StatusRepository {

  Status getBySlug(StatusEnum slug);
}
