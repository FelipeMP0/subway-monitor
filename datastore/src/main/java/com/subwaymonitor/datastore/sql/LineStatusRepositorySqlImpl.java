package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.datastore.LineStatusRepository;
import com.subwaymonitor.sharedmodel.LineStatus;
import com.subwaymonitor.sharedmodel.StatusEnum;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

/** SQL implementation of the {@link LineStatusRepository}. */
@Repository
class LineStatusRepositorySqlImpl implements LineStatusRepository {

  private final EntityManager entityManager;

  LineStatusRepositorySqlImpl(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<LineStatus> findByStatus(List<StatusEnum> statusEnumList, LocalDateTime until) {
    List<String> statuses = statusEnumList.stream().map(Enum::name).toList();
    return entityManager
        .createQuery(
            "SELECT lineStatus FROM LineStatusEntity lineStatus "
                + "WHERE lineStatus.status.slug in :statusSlug "
                + "AND lineStatus.createdAt < :until "
                + "ORDER BY lineStatus.createdAt DESC",
            LineStatusEntity.class)
        .setParameter("statusSlug", statuses)
        .setParameter("until", until)
        .getResultList()
        .stream()
        .map(LineStatusEntity::toModel)
        .toList();
  }
}
