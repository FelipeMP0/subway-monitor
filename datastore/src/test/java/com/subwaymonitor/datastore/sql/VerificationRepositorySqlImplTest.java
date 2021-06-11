package com.subwaymonitor.datastore.sql;

import com.google.common.collect.ImmutableList;
import com.google.common.truth.Truth;
import com.subwaymonitor.sharedmodel.ImmutableLine;
import com.subwaymonitor.sharedmodel.ImmutableLineStatus;
import com.subwaymonitor.sharedmodel.ImmutableStatus;
import com.subwaymonitor.sharedmodel.ImmutableVerification;
import com.subwaymonitor.sharedmodel.Verification;
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
                ImmutableList.of(
                    ImmutableLineStatus.builder()
                        .line(
                            ImmutableLine.builder().number(1).name("line 1").slug("LINE_1").build())
                        .status(ImmutableStatus.builder().name("status 1").slug("STATUS_1").build())
                        .build(),
                    ImmutableLineStatus.builder()
                        .line(
                            ImmutableLine.builder().number(1).name("line 2").slug("LINE_2").build())
                        .status(ImmutableStatus.builder().name("status 2").slug("STATUS_2").build())
                        .build()))
            .build();
    final Verification result = subject.create(input);
    Truth.assertThat(input).isEqualTo(result);
  }
}
