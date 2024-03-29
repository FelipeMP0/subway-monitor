package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.datastore.StatusRepository;
import com.subwaymonitor.sharedmodel.Status;
import com.subwaymonitor.sharedmodel.StatusEnum;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

/** SQL implementation of the {@link StatusRepository}. */
@Repository
class StatusRepositorySqlImpl implements StatusRepository {

  private final EntityManager entityManager;

  StatusRepositorySqlImpl(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Status getBySlug(final StatusEnum slug) {
    final var statusEntity =
        entityManager
            .createQuery("SELECT s FROM StatusEntity s WHERE slug = :slug", StatusEntity.class)
            .setParameter("slug", slug.name())
            .getSingleResult();
    return statusEntity.toModel();
  }
}
