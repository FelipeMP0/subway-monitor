package com.subwaymonitor.datastore.sql;

import com.google.common.truth.Truth;
import com.subwaymonitor.sharedmodel.ImmutableStatus;
import com.subwaymonitor.sharedmodel.Status;
import java.util.UUID;
import javax.persistence.EntityManager;
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
    final String slug = UUID.randomUUID().toString();
    final String name = UUID.randomUUID().toString();
    final Status expected = ImmutableStatus.builder().slug(slug).name(name).build();
    final StatusEntity statusEntity = new StatusEntity(expected);
    entityManager.persist(statusEntity);
    final Status result = subject.getBySlug(slug);
    Truth.assertThat(result).isEqualTo(expected);
  }
}
