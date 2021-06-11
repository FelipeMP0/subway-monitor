package com.subwaymonitor.datastore;

import com.subwaymonitor.sharedmodel.Status;

public interface StatusRepository {

  Status getBySlug(String slug);
}
