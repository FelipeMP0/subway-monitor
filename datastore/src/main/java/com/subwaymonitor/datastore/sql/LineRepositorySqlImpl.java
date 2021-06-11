package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.datastore.LineRepository;
import com.subwaymonitor.sharedmodel.Line;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
class LineRepositorySqlImpl implements LineRepository {

  private final EntityManager entityManager;

  LineRepositorySqlImpl(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Line getBySlug(String slug) {
    final LineEntity lineEntity =
        entityManager
            .createQuery("SELECT l FROM LineEntity l WHERE l.slug = :slug", LineEntity.class)
            .setParameter("slug", slug)
            .getSingleResult();
    return lineEntity.toModel();
  }
}
