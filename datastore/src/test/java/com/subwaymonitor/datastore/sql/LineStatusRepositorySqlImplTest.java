package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.sharedmodel.LineStatus;
import java.util.Collections;
import java.util.Set;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class LineStatusRepositorySqlImplTest extends BaseSqlRepositoryTest {

  private final LineStatusRepositorySqlImpl subject;

  @Autowired
  LineStatusRepositorySqlImplTest(
      final LineStatusRepositorySqlImpl repository,
      final EntityManagerFactory entityManagerFactory) {
    super(entityManagerFactory);
    subject = repository;
  }

  @Test
  void getCurrentState_success() {
    final Set<LineStatus> result = subject.getCurrentState();
    final Set<LineStatus> expected = Collections.emptySet();
    Assertions.assertEquals(expected, result);
  }
}
