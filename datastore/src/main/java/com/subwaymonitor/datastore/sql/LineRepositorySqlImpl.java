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
  public Line getByCompanyLineIdAndCompanySlug(
      final String companyLineId, final String companySlug) {
    final var lineEntity =
        entityManager
            .createQuery(
                "SELECT l FROM LineEntity l "
                    + "WHERE l.lineId.companyLineId = :companyLineId "
                    + "AND l.lineId.companySlug = :companySlug",
                LineEntity.class)
            .setParameter("companyLineId", companyLineId)
            .setParameter("companySlug", companySlug)
            .getSingleResult();
    return lineEntity.toModel();
  }
}
