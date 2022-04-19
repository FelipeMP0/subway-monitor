package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.sharedmodel.Line;
import com.subwaymonitor.sharedmodel.LineStatus;
import com.subwaymonitor.sharedmodel.Status;
import com.subwaymonitor.sharedmodel.StatusEnum;
import com.subwaymonitor.sharedmodel.Verification;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class VerificationRepositorySqlImplTest extends BaseSqlRepositoryTest {

  private static final String COMPANY_SLUG = "company_slug";
  private static final LocalDateTime NOW = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

  private final VerificationRepositorySqlImpl subject;

  @Autowired
  VerificationRepositorySqlImplTest(
      final VerificationRepositorySqlImpl verificationRepositorySql,
      final EntityManagerFactory entityManagerFactory) {
    subject = verificationRepositorySql;
    final EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager
        .createNativeQuery(
            "INSERT INTO verification (id, created_at) "
                + "VALUES ('555b0f77-382f-466e-b98d-979127c650b7', :now) "
                + "ON CONFLICT (id) "
                + "DO NOTHING")
        .setParameter("now", NOW)
        .executeUpdate();
    entityManager
        .createNativeQuery(
            "INSERT INTO line_status_history (id, verification_id, company_line_id, company_slug, status_slug) "
                + "VALUES ('5f5262d7-40e3-4748-9f80-865e9f6ee4c1', '555b0f77-382f-466e-b98d-979127c650b7', '3', 'METRO_SAO_PAULO', 'REDUCED_SPEED'), "
                + "('13455ab7-ae61-46e3-b5e6-8371b1dcd7c8', '555b0f77-382f-466e-b98d-979127c650b7', '2', 'METRO_SAO_PAULO', 'NORMAL_OPERATION') "
                + "ON CONFLICT (id) "
                + "DO NOTHING;")
        .executeUpdate();
    entityManager.getTransaction().commit();
  }

  @Test
  void createVerification_success() {
    final Verification input =
        new Verification(
            List.of(
                new LineStatus(
                    new Line("1", COMPANY_SLUG, "line 1"),
                    new Status(StatusEnum.NORMAL_OPERATION, "status 1")),
                new LineStatus(
                    new Line("2", COMPANY_SLUG, "line 2"),
                    new Status(StatusEnum.REDUCED_SPEED, "status 2"))),
            NOW);
    final Verification result = subject.create(input);
    Assertions.assertEquals(input, result);
  }

  @Test
  void getLastVerification_success() {
    final Verification result = subject.getLast();
    final Verification expected =
        new Verification(
            List.of(
                new LineStatus(
                    new Line("2", "METRO_SAO_PAULO", "Verde"),
                    new Status(StatusEnum.NORMAL_OPERATION, "Operando normalmente")),
                new LineStatus(
                    new Line("3", "METRO_SAO_PAULO", "Vermelha"),
                    new Status(StatusEnum.REDUCED_SPEED, "Velocidade reduzida"))),
            NOW);
    Assertions.assertEquals(expected, result);
  }
}
