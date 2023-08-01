package com.subwaymonitor.datastore;

import com.subwaymonitor.sharedmodel.Verification;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/** Repository responsible for datastore interactions related to {@link Verification}. */
public interface VerificationRepository {

  /**
   * Gets a verification by its identifier.
   *
   * @param id The identifier.
   * @return The found verification.
   */
  Verification getById(UUID id);

  /**
   * Lists the verifications stored.
   *
   * @return The list of verifications.
   */
  List<Verification> list();

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

  /**
   * Deletes the verifications based on the passed parameters.
   *
   * @param until Deletes the entries that were created before this parameter.
   */
  void delete(LocalDateTime until);
}
