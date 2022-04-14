package com.subwaymonitor.datastore.sql;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@AutoConfigurationPackage
@ContextConfiguration(
    classes = {
      BaseSqlRepositoryTest.class,
      VerificationRepositorySqlImpl.class,
      LineStatusRepositorySqlImpl.class,
      LineRepositorySqlImpl.class,
      StatusRepositorySqlImpl.class
    })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
abstract class BaseSqlRepositoryTest {

  private static EmbeddedPostgres embeddedPostgres;

  protected EntityManager entityManager;

  @Autowired
  BaseSqlRepositoryTest(final EntityManagerFactory entityManagerFactory) {
    this.entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager
        .createNativeQuery(
            "INSERT INTO company (id, name) "
                + "VALUES ('57e36b6e-570c-4f67-9052-9f5bfd5dda1f', 'Metrô de São Paulo');")
        .executeUpdate();
    entityManager
        .createNativeQuery(
            "INSERT INTO line (slug, name, number, company_id) "
                + "VALUES ('AZUL', 'Azul', 1, '57e36b6e-570c-4f67-9052-9f5bfd5dda1f'), "
                + "('VERDE', 'Verde', 2, '57e36b6e-570c-4f67-9052-9f5bfd5dda1f'), "
                + "('VERMELHA', 'Vermelha', 3, '57e36b6e-570c-4f67-9052-9f5bfd5dda1f'), "
                + "('PRATA', 'Prata', 15, '57e36b6e-570c-4f67-9052-9f5bfd5dda1f');")
        .executeUpdate();
    entityManager
        .createNativeQuery(
            "INSERT INTO status (slug, name) "
                + "VALUES ('OPERATION_CLOSED', 'Operação encerrada'), "
                + "('NORMAL_OPERATION', 'Operando normalmente'), "
                + "('REDUCED_SPEED', 'Velocidade reduzida'), "
                + "('OPERATION_INTERRUPTED', 'Operação interrompida'), "
                + "('UNKNOWN', 'Desconhecido');")
        .executeUpdate();
    entityManager
        .createNativeQuery(
            "INSERT INTO verification (id) " + "VALUES ('555b0f77-382f-466e-b98d-979127c650b7');")
        .executeUpdate();
    entityManager
        .createNativeQuery(
            "INSERT INTO line_status_history (id, verification_id, line_slug, status_slug) "
                + "VALUES ('5f5262d7-40e3-4748-9f80-865e9f6ee4c1', '555b0f77-382f-466e-b98d-979127c650b7', 'VERMELHA', 'OPERATION_CLOSED'), "
                + "('13455ab7-ae61-46e3-b5e6-8371b1dcd7c8', '555b0f77-382f-466e-b98d-979127c650b7', 'VERDE', 'NORMAL_OPERATION');")
        .executeUpdate();
    entityManager.getTransaction().commit();
  }

  @BeforeAll
  public static void init() throws Exception {
    if (embeddedPostgres == null) {
      embeddedPostgres = EmbeddedPostgres.builder().setPort(5433).start();
    }
  }
}
