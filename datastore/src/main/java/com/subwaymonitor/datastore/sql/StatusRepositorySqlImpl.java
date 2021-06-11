package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.datastore.StatusRepository;
import com.subwaymonitor.sharedmodel.Status;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
class StatusRepositorySqlImpl implements StatusRepository {

  private final EntityManager entityManager;

  StatusRepositorySqlImpl(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Status getBySlug(final String slug) {
    final StatusEntity statusEntity =
        entityManager
            .createQuery("SELECT s FROM StatusEntity s WHERE slug = :slug", StatusEntity.class)
            .setParameter("slug", slug)
            .getSingleResult();
    return statusEntity.toModel();
  }
}
