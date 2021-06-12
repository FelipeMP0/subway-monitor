package com.subwaymonitor.datastore.sql;

import com.google.common.truth.Truth;
import com.subwaymonitor.sharedmodel.ImmutableLine;
import com.subwaymonitor.sharedmodel.Line;
import java.util.UUID;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class LineRepositorySqlImplTest extends BaseSqlRepositoryTest {

  private final LineRepositorySqlImpl subject;

  private final EntityManager entityManager;

  @Autowired
  LineRepositorySqlImplTest(
      final LineRepositorySqlImpl lineRepositorySqlImpl, final EntityManager entityManager) {
    subject = lineRepositorySqlImpl;
    this.entityManager = entityManager;
  }

  @Test
  void getBySlug_success() {
    final String slug = UUID.randomUUID().toString();
    final String name = UUID.randomUUID().toString();
    final int number = 1;
    final Line expected = ImmutableLine.builder().slug(slug).number(number).name(name).build();
    final LineEntity lineEntity = new LineEntity(expected);
    entityManager.persist(lineEntity);
    final Line result = subject.getByNumber(number);
    Truth.assertThat(result).isEqualTo(expected);
  }
}
