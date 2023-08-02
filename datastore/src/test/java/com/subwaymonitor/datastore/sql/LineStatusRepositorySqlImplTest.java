package com.subwaymonitor.datastore.sql;

import static com.subwaymonitor.sharedmodel.StatusEnum.NORMAL_OPERATION;
import static com.subwaymonitor.sharedmodel.StatusEnum.OPERATION_INTERRUPTED;

import com.subwaymonitor.serialization.DateTimeUtils;
import com.subwaymonitor.sharedmodel.Line;
import com.subwaymonitor.sharedmodel.LineStatus;
import com.subwaymonitor.sharedmodel.Status;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class LineStatusRepositorySqlImplTest extends BaseSqlRepositoryTest {

  private final LineStatusRepositorySqlImpl subject;

  @Autowired
  LineStatusRepositorySqlImplTest(final LineStatusRepositorySqlImpl lineStatusRepositorySql) {
    subject = lineStatusRepositorySql;
  }

  @BeforeEach
  void setUp() throws SQLException {
    clearDatabase();
    final var formattedTime = DateTimeUtils.formatLocalDataTime(TestConstants.NOW);
    final var formattedTimePlus1Day =
        DateTimeUtils.formatLocalDataTime(TestConstants.NOW.plusDays(1));
    runNativeQuery(
        String.format(
            "INSERT INTO verification (id, created_at) "
                + "VALUES ('555b0f77-382f-466e-b98d-979127c650b7', '%1$s'), "
                + "('1bc8836f-076a-4a3e-9ebd-7dbb21e5fb5c', '%1$s') "
                + "ON CONFLICT (id) "
                + "DO NOTHING;",
            formattedTime));
    runNativeQuery(
        String.format(
            "INSERT INTO line_status_history (id, verification_id, company_line_id, company_slug, status_slug, created_at) "
                + "VALUES ('5f5262d7-40e3-4748-9f80-865e9f6ee4c1', '555b0f77-382f-466e-b98d-979127c650b7', '3', 'METRO_SAO_PAULO', 'REDUCED_SPEED', '%1$s'), "
                + "('13455ab7-ae61-46e3-b5e6-8371b1dcd7c8', '555b0f77-382f-466e-b98d-979127c650b7', '2', 'METRO_SAO_PAULO', 'NORMAL_OPERATION', '%1$s'), "
                + "('1e07b6a8-5cbd-4d64-82b6-bee834a23a30', '1bc8836f-076a-4a3e-9ebd-7dbb21e5fb5c', '1', 'METRO_SAO_PAULO', 'OPERATION_INTERRUPTED', '%1$s'), "
                + "('0d87b663-d306-4316-b794-74830bbcaca4', '1bc8836f-076a-4a3e-9ebd-7dbb21e5fb5c', '15', 'METRO_SAO_PAULO', 'NORMAL_OPERATION', '%2$s') "
                + "ON CONFLICT (id) "
                + "DO NOTHING;",
            formattedTime, formattedTimePlus1Day));
  }

  @Test
  void findByStatus_success() {
    final List<LineStatus> result =
        subject.findByStatus(
            List.of(NORMAL_OPERATION, OPERATION_INTERRUPTED), TestConstants.NOW.plusHours(1));

    final var line1 = new Line("2", "METRO_SAO_PAULO", "Verde");
    final var line2 = new Line("1", "METRO_SAO_PAULO", "Azul");
    final var status1 = new Status(NORMAL_OPERATION, "Operando normalmente");
    final var status2 = new Status(OPERATION_INTERRUPTED, "Operação interrompida");

    final List<LineStatus> expected =
        List.of(
            new LineStatus(line1, status1, TestConstants.NOW),
            new LineStatus(line2, status2, TestConstants.NOW));

    Assertions.assertEquals(expected, result);
  }

  @Test
  void findByStatus_emptyDatabase_returnEmptyList() throws SQLException {
    clearDatabase();

    final List<LineStatus> result =
        subject.findByStatus(
            List.of(NORMAL_OPERATION, OPERATION_INTERRUPTED), TestConstants.NOW.plusHours(1));

    Assertions.assertTrue(result.isEmpty());
  }
}
