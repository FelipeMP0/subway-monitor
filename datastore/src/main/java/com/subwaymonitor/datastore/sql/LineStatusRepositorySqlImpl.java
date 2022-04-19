package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.datastore.LineStatusRepository;
import com.subwaymonitor.sharedmodel.LineStatus;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

/** SQL implementation of the {@link com.subwaymonitor.datastore.LineStatusRepository}. */
@Repository
public class LineStatusRepositorySqlImpl implements LineStatusRepository {

  private final EntityManager entityManager;

  LineStatusRepositorySqlImpl(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<LineStatus> getCurrentState() {
    return entityManager
        .createQuery(
            "SELECT l FROM VerificationEntity v "
                + "JOIN v.lineStatuses l "
                + "WHERE v.createdAt = ("
                + "SELECT MAX(v.createdAt) FROM VerificationEntity v"
                + ")",
            LineStatusEntity.class)
        .getResultList()
        .stream()
        .map(LineStatusEntity::toModel)
        .toList();
  }
}
