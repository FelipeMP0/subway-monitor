package com.subwaymonitor.datastore.sql;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
class VerificationRepositorySqlImpl {

  @PersistenceContext private EntityManager entityManager;
}
