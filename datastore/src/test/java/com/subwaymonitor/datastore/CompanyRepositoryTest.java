package com.subwaymonitor.datastore;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CompanyRepositoryTest extends BaseRepositoryTest {

  private final CompanyRepository companyRepository;

  @Autowired
  public CompanyRepositoryTest(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  @Test
  public void basic_insert_success() {
    CompanyEntity companyEntity = new CompanyEntity();

    companyEntity.setName(UUID.randomUUID().toString());
    companyEntity.setCreatedAt(ZonedDateTime.now());
    companyEntity.setUpdatedAt(ZonedDateTime.now());

    CompanyEntity savedCompany = companyRepository.save(companyEntity);

    Optional<CompanyEntity> foundCompany = companyRepository.findById(savedCompany.getId());

    Assertions.assertEquals(savedCompany, foundCompany.get());
  }
}
