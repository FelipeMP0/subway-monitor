package com.subwaymonitor.datastore;

import com.subwaymonitor.sharedmodel.Verification;

/** Repository responsible for datastore interactions related to {@link Verification}. */
public interface VerificationRepository {

  /**
   * Creates a new verification.
   *
   * @param verification An instance of {@link Verification} containing all the required data about
   *     a verification.
   * @return A representation of the created object.
   */
  Verification create(Verification verification);

  /**
   * Returns the last saved verification.
   *
   * @return an instance {@link Verification} representing the last saved verification.
   */
  Verification getLast();
}
