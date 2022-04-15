package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.sharedmodel.ImmutableStatus;
import com.subwaymonitor.sharedmodel.Status;
import com.subwaymonitor.sharedmodel.StatusEnum;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class StatusRepositorySqlImplTest extends BaseSqlRepositoryTest {

  private final StatusRepositorySqlImpl subject;

  private final EntityManager entityManager;

  @Autowired
  StatusRepositorySqlImplTest(
      final StatusRepositorySqlImpl statusRepositorySqlImpl, final EntityManager entityManager) {
    subject = statusRepositorySqlImpl;
    this.entityManager = entityManager;
  }

  @Test
  void getBySlug_success() {
    final StatusEnum slug = StatusEnum.NORMAL_OPERATION;
    final String name = "Operando normalmente";
    final Status expected = ImmutableStatus.builder().status(slug).name(name).build();
    final Status result = subject.getBySlug(slug);
    Assertions.assertEquals(expected, result);
  }
}
