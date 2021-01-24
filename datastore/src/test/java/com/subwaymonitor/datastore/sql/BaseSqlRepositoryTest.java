package com.subwaymonitor.datastore.sql;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@AutoConfigurationPackage
@ContextConfiguration(classes = {BaseSqlRepositoryTest.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
abstract class BaseSqlRepositoryTest {

  private static EmbeddedPostgres embeddedPostgres;

  @BeforeAll
  public static void init() throws Exception {
    if (embeddedPostgres == null) {
      embeddedPostgres = EmbeddedPostgres.builder().setPort(5433).start();
    }
  }
}
