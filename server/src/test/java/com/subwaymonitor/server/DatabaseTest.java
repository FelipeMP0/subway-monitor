package com.subwaymonitor.server;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(initializers = {DatabaseTest.Initializer.class})
class DatabaseTest {

  private static EmbeddedPostgres embeddedPostgres;

  @BeforeAll
  public static void init() throws Exception {
    if (embeddedPostgres == null) {
      embeddedPostgres = EmbeddedPostgres.builder().setPort(5433).start();
    }
  }

  @Test
  void migrations() {}

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
