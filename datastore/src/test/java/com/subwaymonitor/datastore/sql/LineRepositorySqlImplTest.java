package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.sharedmodel.ImmutableLine;
import com.subwaymonitor.sharedmodel.Line;
import java.util.UUID;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class LineRepositorySqlImplTest extends BaseSqlRepositoryTest {

  private final LineRepositorySqlImpl subject;

  @Autowired
  LineRepositorySqlImplTest(
      final LineRepositorySqlImpl lineRepositorySqlImpl,
      final EntityManagerFactory entityManagerFactory) {
    super(entityManagerFactory);
    subject = lineRepositorySqlImpl;
  }

  @Test
  void getByCompanyLineIdAndCompanySlug_success() {
    final String companyLineId = UUID.randomUUID().toString();
    final String companySlug = UUID.randomUUID().toString();
    final String name = UUID.randomUUID().toString();
    final Line expected =
        ImmutableLine.builder()
            .companyLineId(companyLineId)
            .companySlug(companySlug)
            .name(name)
            .build();
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
