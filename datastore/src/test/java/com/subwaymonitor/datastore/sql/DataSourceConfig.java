package com.subwaymonitor.datastore.sql;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DataSourceConfig {

  public static final DataSource DATA_SOURCE = createDataSource();

  @Bean
  public DataSource getDataSource() {
    return DATA_SOURCE;
  }

  private static DataSource createDataSource() {
    final var dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName("org.postgresql.Driver");
    dataSourceBuilder.url("jdbc:postgresql://localhost:5433/");
    dataSourceBuilder.username("postgres");
    dataSourceBuilder.password("password");
    return dataSourceBuilder.build();
  }
}
