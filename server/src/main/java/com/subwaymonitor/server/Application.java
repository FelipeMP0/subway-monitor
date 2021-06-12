package com.subwaymonitor.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EntityScan(basePackages = {"com.subwaymonitor.*"})
@EnableJpaRepositories(basePackages = {"com.subwaymonitor.*"})
@SpringBootApplication(scanBasePackages = "com.subwaymonitor.*")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
