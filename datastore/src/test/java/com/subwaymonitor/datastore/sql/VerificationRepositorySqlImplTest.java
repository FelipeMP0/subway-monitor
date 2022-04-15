package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.sharedmodel.*;
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
        ImmutableVerification.builder()
            .addAllLineStatuses(
                List.of(
                    ImmutableLineStatus.builder()
                        .line(
                            ImmutableLine.builder()
                                .companyLineId("1")
                                .companySlug(COMPANY_SLUG)
                                .name("line 1")
                                .build())
                        .status(
                            ImmutableStatus.builder()
                                .name("status 1")
                                .status(StatusEnum.NORMAL_OPERATION)
                                .build())
                        .build(),
                    ImmutableLineStatus.builder()
                        .line(
                            ImmutableLine.builder()
                                .companyLineId("2")
                                .companySlug(COMPANY_SLUG)
                                .name("line 2")
                                .build())
                        .status(
                            ImmutableStatus.builder()
                                .name("status 2")
                                .status(StatusEnum.REDUCED_SPEED)
                                .build())
                        .build()))
            .build();
    final Verification result = subject.create(input);
    Assertions.assertEquals(input, result);
  }
}
