package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.sharedmodel.Line;
import com.subwaymonitor.sharedmodel.LineStatus;
import com.subwaymonitor.sharedmodel.Status;
import com.subwaymonitor.sharedmodel.StatusEnum;
import com.subwaymonitor.sharedmodel.Verification;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class VerificationRepositorySqlImplTest extends BaseSqlRepositoryTest {

  private static final String COMPANY_SLUG = "company_slug";

  private final VerificationRepositorySqlImpl subject;
  private final EntityManager entityManager;

  @Autowired
  VerificationRepositorySqlImplTest(
      final VerificationRepositorySqlImpl verificationRepositorySql,
      final EntityManager entityManager) {
    subject = verificationRepositorySql;
    this.entityManager = entityManager;
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
                    new Status(StatusEnum.REDUCED_SPEED, "status 2"))));
    final Verification result = subject.create(input);
    Assertions.assertEquals(input, result);
  }
}
