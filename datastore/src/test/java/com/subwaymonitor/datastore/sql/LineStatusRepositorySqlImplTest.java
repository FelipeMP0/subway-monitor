package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.sharedmodel.*;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class LineStatusRepositorySqlImplTest extends BaseSqlRepositoryTest {

  private final LineStatusRepositorySqlImpl subject;
  private final EntityManager entityManager;

  @Autowired
  LineStatusRepositorySqlImplTest(
      final LineStatusRepositorySqlImpl repository,
      final EntityManagerFactory entityManagerFactory) {
    subject = repository;
    this.entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager
        .createNativeQuery(
            "INSERT INTO verification (id) " + "VALUES ('555b0f77-382f-466e-b98d-979127c650b7');")
        .executeUpdate();
    entityManager
        .createNativeQuery(
            "INSERT INTO line_status_history (id, verification_id, company_line_id, company_slug, status_slug) "
                + "VALUES ('5f5262d7-40e3-4748-9f80-865e9f6ee4c1', '555b0f77-382f-466e-b98d-979127c650b7', '3', 'METRO_SAO_PAULO', 'REDUCED_SPEED'), "
                + "('13455ab7-ae61-46e3-b5e6-8371b1dcd7c8', '555b0f77-382f-466e-b98d-979127c650b7', '2', 'METRO_SAO_PAULO', 'NORMAL_OPERATION');")
        .executeUpdate();
    entityManager.getTransaction().commit();
  }

  @Test
  void getCurrentState_success() {
    final Set<LineStatus> result = subject.getCurrentState();
    final Set<LineStatus> expected =
        Set.of(
            ImmutableLineStatus.builder()
                .line(
                    ImmutableLine.builder()
                        .companyLineId("3")
                        .companySlug("METRO_SAO_PAULO")
                        .name("Vermelha")
                        .build())
                .status(
                    ImmutableStatus.builder()
                        .status(StatusEnum.REDUCED_SPEED)
                        .name("Velocidade reduzida")
                        .build())
                .build(),
            ImmutableLineStatus.builder()
                .line(
                    ImmutableLine.builder()
                        .companyLineId("2")
                        .companySlug("METRO_SAO_PAULO")
                        .name("Verde")
                        .build())
                .status(
                    ImmutableStatus.builder()
                        .status(StatusEnum.NORMAL_OPERATION)
                        .name("Operando normalmente")
                        .build())
                .build());
    Assertions.assertEquals(expected, result);
  }
}
