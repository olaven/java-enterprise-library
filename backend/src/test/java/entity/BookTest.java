package entity;

import entity.mocker.BookMocker;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.*;

public class BookTest extends EntityTestBase{

    private BookMocker bookMocker = new BookMocker();

    @Test
    public void testCanPersistValidBook() {
        Book book = bookMocker.getOne();
        doPersist(book);
    }

    @Test
    public void testCannotPersistInvalidBook() {
        Book book = bookMocker.getOne();
        book.setTitle(""); // cannot be blank

        assertFalse(doPersist(book));
    }

    @Test
    public void testRetrievesSameAmountOfBooks() {
        int count = 25;
        List<Book> books = bookMocker.getMany(count);

        books.forEach(book -> {
            book.getAuthors().forEach(author -> {
                assertTrue(doPersist(author));
            });
        });

        assertTrue(doPersist(books.toArray()));

        executeInTransaction((entityManager -> {

            int retrieved = entityManager.createNamedQuery(Book.GET_BOOKS, Book.class).getResultList().size();

            assertEquals(count, retrieved);

        }));
    }

    @Test
    public void testAuthorIsCascadedWhenPersisted() {
        Book book = bookMocker.getOne();
        assertTrue(book.getAuthors().size() > 0); // Authors present

        // no authors are present BEFORE persisting book
        assertFalse(firstAuthorIsPersisted(book));

        assertTrue(doPersist(book));

        // author is present AFTER persisting book
        assertTrue(firstAuthorIsPersisted(book));
    }

    @Test(expected = Exception.class)
    public void testAuthorsAreLazilyLoaded() {
        Book book = bookMocker.getOne();
        assertTrue(doPersist(book));

        Book retrieved = getById(book.getId());

        retrieved.getAuthors().size();
    }

    @Test(expected = Exception.class)
    public void testAreAuthorsLoadedWhenDoingManually() {
        Book book = bookMocker.getOne();

        doPersist(book);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(book);
        transaction.commit();


        Book retrieved = getById(book.getId());

        retrieved.getAuthors().size();
    }


    private boolean firstAuthorIsPersisted(Book book) {

        AtomicBoolean found = new AtomicBoolean(false);

        executeInTransaction(entityManager -> {
            Author author = book.getAuthors().get(0);
            TypedQuery<Author> query = entityManager.createNamedQuery(
                    Author.GET_WITH_ID,
                    Author.class
            );
            query.setParameter("id", author.getId());

            found.set(query.getResultList().size() == 1);
        });

        return found.get();
    }

    private Book getById(int id) {
        final Book[] result = new Book[1];

        executeInTransaction(entityManager -> {
            Query query = entityManager.createNamedQuery(Book.GET_BOOK_BY_ID, Book.class);
            query.setParameter("id", id);

            result[0] = (Book) query.getResultList().get(0);
        });

        return result[0];
    }


}
