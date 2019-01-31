package entity;

import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.function.Consumer;

public class EntityTestBase {

    // these fields are accessed through executeInTransaction by subclassses
    protected EntityManagerFactory factory;
    protected EntityManager entityManager;

    @Before
    public void init() {
        factory = Persistence.createEntityManagerFactory("testDB");
        entityManager = factory.createEntityManager();
    }

    @After
    public void tearDown() {
        factory.close();
        entityManager.close();
    }


    protected boolean doPersist(Object... objects) {

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
