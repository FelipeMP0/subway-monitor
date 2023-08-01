package com.subwaymonitor.datastore.sql;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@AutoConfigurationPackage
@ContextConfiguration(
    classes = {
      DataSourceConfig.class,
      BaseSqlRepositoryTest.class,
      VerificationRepositorySqlImpl.class,
      LineRepositorySqlImpl.class,
      StatusRepositorySqlImpl.class,
      LineStatusRepositorySqlImpl.class,
    })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
abstract class BaseSqlRepositoryTest {

  private static final List<String> TABLES = List.of("line_status_history", "verification");
  private static EmbeddedPostgres embeddedPostgres;

  @BeforeAll
  public static void init() throws Exception {
    if (embeddedPostgres == null) {
      embeddedPostgres = EmbeddedPostgres.builder().setPort(5433).start();
    }
  }

  public void runNativeQuery(final String sqlQuery) throws SQLException {
    try (final Connection connection = DataSourceConfig.DATA_SOURCE.getConnection()) {
      try (final Statement statement = connection.createStatement()) {
        statement.execute(sqlQuery);
      }
    }
  }

  public void clearDatabase() throws SQLException {
    try (final Connection connection = DataSourceConfig.DATA_SOURCE.getConnection()) {
      for (final var table : TABLES) {
        try (final Statement statement = connection.createStatement()) {
          statement.executeUpdate(String.format("DELETE FROM %S;", table));
        }
      }
    }
  }
}
