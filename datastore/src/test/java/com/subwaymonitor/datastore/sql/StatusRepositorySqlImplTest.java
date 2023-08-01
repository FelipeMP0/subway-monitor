package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.sharedmodel.Status;
import com.subwaymonitor.sharedmodel.StatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class StatusRepositorySqlImplTest extends BaseSqlRepositoryTest {

  private final StatusRepositorySqlImpl subject;

  @Autowired
  StatusRepositorySqlImplTest(final StatusRepositorySqlImpl statusRepositorySqlImpl) {
    subject = statusRepositorySqlImpl;
  }

  @Test
  void getBySlug_success() {
    final StatusEnum slug = StatusEnum.NORMAL_OPERATION;
    final String name = "Operando normalmente";
    final Status expected = new Status(slug, name);
    final Status result = subject.getBySlug(slug);
    Assertions.assertEquals(expected, result);
  }
}
