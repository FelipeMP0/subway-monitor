package com.subwaymonitor.datastore;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = {BaseRepositoryTest.Initializer.class})
abstract class BaseRepositoryTest {

  private static EmbeddedPostgres embeddedPostgres;

  @BeforeAll
  public static void init() throws Exception {
    if (embeddedPostgres == null) {
      embeddedPostgres = EmbeddedPostgres.builder().setPort(5433).start();
    }
  }

  static class Initializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues.of(
              "spring.datasource.url=jdbc:postgresql://localhost:5433/",
              "spring.datasource.username=postgres",
              "spring.datasource.password=password")
          .applyTo(configurableApplicationContext.getEnvironment());
    }
  }
}
