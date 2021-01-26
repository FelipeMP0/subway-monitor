package com.subwaymonitor.datastore;

import com.subwaymonitor.sharedmodel.Verification;

public interface VerificationRepository {

  Verification create(Verification verification);
}
