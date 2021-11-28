package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.datastore.VerificationRepository;
import com.subwaymonitor.sharedmodel.Verification;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

/** SQL implementation of the {@link VerificationRepository}. */
@Repository
class VerificationRepositorySqlImpl implements VerificationRepository {

  private final EntityManager entityManager;

  VerificationRepositorySqlImpl(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Verification create(final Verification verification) {
    final var verificationEntity = new VerificationEntity(verification);
    entityManager.persist(verificationEntity);
    return verificationEntity.toModel();
  }
}
