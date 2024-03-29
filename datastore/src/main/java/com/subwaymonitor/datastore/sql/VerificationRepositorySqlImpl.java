package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.datastore.VerificationRepository;
import com.subwaymonitor.sharedmodel.Verification;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
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
  public Verification getById(final UUID id) {
    return entityManager
        .createQuery(
            "SELECT verification FROM VerificationEntity verification "
                + "WHERE verification.id = :id",
            VerificationEntity.class)
        .setParameter("id", id)
        .getSingleResult()
        .toModel();
  }

  @Override
  public List<Verification> list() {
    return entityManager
        .createQuery(
            "SELECT verification FROM VerificationEntity verification", VerificationEntity.class)
        .getResultList()
        .stream()
        .map(VerificationEntity::toModel)
        .toList();
  }

  @Override
  public Verification create(final Verification verification) {
    final var verificationEntity = new VerificationEntity(verification);
    entityManager.persist(verificationEntity);
    return verificationEntity.toModel();
  }

  @Override
  public Verification getLast() {
    return entityManager
        .createQuery(
            "SELECT verification FROM VerificationEntity verification "
                + "JOIN FETCH verification.lineStatuses lineStatus "
                + "LEFT JOIN FETCH lineStatus.line line "
                + "LEFT JOIN FETCH lineStatus.status status "
                + "WHERE verification.createdAt = ("
                + "SELECT MAX(verification.createdAt) FROM VerificationEntity verification"
                + ") "
                + "ORDER BY line.lineId.companyLineId, line.lineId.companySlug",
            VerificationEntity.class)
        .getSingleResult()
        .toModel();
  }

  @Override
  public void delete(final LocalDateTime until) {
    entityManager
        .createQuery(
            "DELETE FROM VerificationEntity verification "
                + "WHERE verification.createdAt < :until")
        .setParameter("until", until)
        .executeUpdate();
  }
}
