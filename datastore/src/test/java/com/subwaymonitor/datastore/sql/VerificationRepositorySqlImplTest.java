package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.sharedmodel.*;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class VerificationRepositorySqlImplTest extends BaseSqlRepositoryTest {

  private final VerificationRepositorySqlImpl subject;

  @Autowired
  VerificationRepositorySqlImplTest(final VerificationRepositorySqlImpl verificationRepositorySql) {
    subject = verificationRepositorySql;
  }

  @Test
  void createVerification_success() {
    final Verification input =
        ImmutableVerification.builder()
            .addAllLineStatuses(
                Arrays.asList(
                    ImmutableLineStatus.builder()
                        .line(
                            ImmutableLine.builder().number(1).name("line 1").slug("LINE_1").build())
                        .status(
                            ImmutableStatus.builder()
                                .name("status 1")
                                .slug(StatusEnum.NORMAL_OPERATION)
                                .build())
                        .build(),
                    ImmutableLineStatus.builder()
                        .line(
                            ImmutableLine.builder().number(1).name("line 2").slug("LINE_2").build())
                        .status(
                            ImmutableStatus.builder()
                                .name("status 2")
                                .slug(StatusEnum.REDUCED_SPEED)
                                .build())
                        .build()))
            .build();
    final Verification result = subject.create(input);
    Assertions.assertEquals(input, result);
  }
}
