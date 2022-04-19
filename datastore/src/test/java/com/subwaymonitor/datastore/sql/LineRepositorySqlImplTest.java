package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.sharedmodel.Line;
import java.util.UUID;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class LineRepositorySqlImplTest extends BaseSqlRepositoryTest {

  private final LineRepositorySqlImpl subject;
  private final EntityManager entityManager;

  @Autowired
  LineRepositorySqlImplTest(
      final LineRepositorySqlImpl lineRepositorySqlImpl, final EntityManager entityManager) {
    subject = lineRepositorySqlImpl;
    this.entityManager = entityManager;
  }

  @Test
  void getByCompanyLineIdAndCompanySlug_success() {
    final String companyLineId = UUID.randomUUID().toString();
    final String companySlug = UUID.randomUUID().toString();
    final String name = UUID.randomUUID().toString();
    final Line expected = new Line(companyLineId, companySlug, name);
    final LineEntity lineEntity = new LineEntity(expected);
    final CompanyEntity company = new CompanyEntity();
    company.setSlug(companySlug);
    company.setName("test");
    lineEntity.setCompany(company);
    entityManager.persist(company);
    entityManager.persist(lineEntity);
    final Line result = subject.getByCompanyLineIdAndCompanySlug(companyLineId, companySlug);
    Assertions.assertEquals(expected, result);
  }
}
