package entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.function.Consumer;

public class EntityTestBase {

    protected EntityManagerFactory factory;
    protected EntityManager entityManager;

    @BeforeEach
    protected void init() {
        factory = Persistence.createEntityManagerFactory("testDB");
        entityManager = factory.createEntityManager();
    }

    @AfterEach
    protected void tearDown() {
        factory.close();
        entityManager.close();
    }


    protected boolean canPersist(Object... objects) {

        try {
            executeInTransaction(entityManager -> {
                for(Object object: objects) {
                    entityManager.persist(object);
                }
            });
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    protected void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        consumer.accept(entityManager);

        transaction.commit();
    }
}
