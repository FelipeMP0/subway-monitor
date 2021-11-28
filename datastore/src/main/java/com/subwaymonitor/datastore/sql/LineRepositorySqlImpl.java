package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.datastore.LineRepository;
import com.subwaymonitor.sharedmodel.Line;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

/** SQL implementation of the {@link LineRepository}. */
@Repository
class LineRepositorySqlImpl implements LineRepository {

  private final EntityManager entityManager;

  LineRepositorySqlImpl(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Line getByNumber(final int number) {
    final var lineEntity =
        entityManager
            .createQuery("SELECT l FROM LineEntity l WHERE l.number = :number", LineEntity.class)
            .setParameter("number", number)
            .getSingleResult();
    return lineEntity.toModel();
  }
}
