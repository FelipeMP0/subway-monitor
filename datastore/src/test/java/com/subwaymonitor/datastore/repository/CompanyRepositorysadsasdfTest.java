package com.subwaymonitor.datastore.repository;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(initializers = {CompanyRepositorysadsasdfTest.Initializer.class})
public class CompanyRepositorysadsasdfTest {

  @Autowired private CompanyRepositorysadsasdf companyRepository;

  @ClassRule
  public static PostgreSQLContainer postgreSQLContainer =
      new PostgreSQLContainer("postgres:9.6")
          .withDatabaseName("subway_monitor")
          .withUsername("sa")
          .withPassword("sa");

  static class Initializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues.of(
              "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
              "spring.datasource.username=" + postgreSQLContainer.getUsername(),
              "spring.datasource.password=" + postgreSQLContainer.getPassword())
          .applyTo(configurableApplicationContext.getEnvironment());
    }
  }

  @Test
  public void name() {
    insertCompany();
  }

  private void insertCompany() {
    CompanyEntity companyEntity = new CompanyEntity();

    companyEntity.setName("fasdfasdf");

    companyRepository.save(companyEntity);
  }
}
