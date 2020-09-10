package com.subwaymonitor.monitors.metro;

import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class HttpClient {

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplateBuilder().setReadTimeout(Duration.ofSeconds(30)).build();
  }
}
