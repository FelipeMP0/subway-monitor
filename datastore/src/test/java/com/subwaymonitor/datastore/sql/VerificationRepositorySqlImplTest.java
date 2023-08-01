package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.sharedmodel.Line;
import com.subwaymonitor.sharedmodel.LineStatus;
import com.subwaymonitor.sharedmodel.Status;
import com.subwaymonitor.sharedmodel.StatusEnum;
import com.subwaymonitor.sharedmodel.Verification;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class VerificationRepositorySqlImplTest extends BaseSqlRepositoryTest {

  private static final UUID VERIFICATION_ID =
      UUID.fromString("555b0f77-382f-466e-b98d-979127c650b7");
  private final VerificationRepositorySqlImpl subject;

  @Autowired
  VerificationRepositorySqlImplTest(final VerificationRepositorySqlImpl verificationRepositorySql) {
    subject = verificationRepositorySql;
  }

  @BeforeEach
  void setUp() throws SQLException {
    clearDatabase();
    runNativeQuery(
        String.format(
            "INSERT INTO verification (id, created_at) "
                + "VALUES ('%s', '%s') "
                + "ON CONFLICT (id) "
                + "DO NOTHING;",
            VERIFICATION_ID, TestHelpers.formatLocalDataTime(TestConstants.NOW)));
    runNativeQuery(
        String.format(
            "INSERT INTO line_status_history (id, verification_id, company_line_id, company_slug, status_slug) "
                + "VALUES ('5f5262d7-40e3-4748-9f80-865e9f6ee4c1', '%1$s', '3', 'METRO_SAO_PAULO', 'REDUCED_SPEED'), "
                + "('13455ab7-ae61-46e3-b5e6-8371b1dcd7c8', '%1$s', '2', 'METRO_SAO_PAULO', 'NORMAL_OPERATION') "
                + "ON CONFLICT (id) "
                + "DO NOTHING;",
            VERIFICATION_ID));
  }

  @Test
  void createVerification_success() {
    final Verification input =
        new Verification(
            List.of(
                new LineStatus(
                    new Line("1", TestConstants.COMPANY_SLUG, "line 1"),
                    new Status(StatusEnum.NORMAL_OPERATION, "status 1")),
                new LineStatus(
                    new Line("2", TestConstants.COMPANY_SLUG, "line 2"),
                    new Status(StatusEnum.REDUCED_SPEED, "status 2"))),
            TestConstants.NOW);
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
            TestConstants.NOW);
    Assertions.assertEquals(expected, result);
  }

  @Test
  void delete_success() {
    final LocalDateTime until = TestConstants.NOW.plusDays(1);

    Assertions.assertDoesNotThrow(() -> subject.delete(until));

    final List<Verification> verifications = subject.list();

    Assertions.assertTrue(verifications.isEmpty());
  }
}
