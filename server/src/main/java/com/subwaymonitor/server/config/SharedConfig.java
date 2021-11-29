package com.subwaymonitor.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subwaymonitor.common.JacksonMapperFactory;
import java.time.Clock;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/** Contains shared configurations and objects to be used by the spring application. */
@Configuration
public class SharedConfig {

  @Bean
  @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
  public Clock clock() {
    return Clock.systemDefaultZone();
  }

  @Bean
  @Primary
  public ObjectMapper objectMapper() {
    return JacksonMapperFactory.create();
  }
}
