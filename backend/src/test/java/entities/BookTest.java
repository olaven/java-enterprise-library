package entities;

import entities.mocker.BookMocker;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookTest  extends EntityTestBase {

    private BookMocker bookMocker = new BookMocker();

    @Test
    void canPersistValidBook() {
        Book book = bookMocker.getOne();
        canPersist(book);
    }

    @Test
    void cannotPersistInvalidBook() {
        Book book = bookMocker.getOne();
        book.setTitle(""); // cannot be blank

        assertFalse(canPersist(book));
    }

    @Test
    void retrievesSameAmountOfBooks() {
        int count = 25;
        List<Book> books = bookMocker.getMany(count);

        books.forEach(book -> {
            book.getAuthors().forEach(author -> {
                assertTrue(canPersist(author));
            });
        });

        assertTrue(canPersist(books.toArray()));

        executeInTransaction((entityManager -> {

            int retrieved = entityManager.createNamedQuery(Book.GET_BOOKS, Book.class).getResultList().size();

            assertEquals(count, retrieved);

        }));
    }

    @Test
    void authorIsCascadedWhenPersisted() {
        Book book = bookMocker.getOne();
        assertTrue(book.getAuthors().size() > 0); // Authors present

        // no authors are present BEFORE persisting book
        assertFalse(firstAuthorIsPersisted(book));

        assertTrue(canPersist(book));

        // author is present AFTER persisting book
        assertTrue(firstAuthorIsPersisted(book));
    }

    @Test
    void authorsAreLazilyLoaded() {
        Book book = bookMocker.getOne();
        assertTrue(canPersist(book));
        throw new NotImplementedException();
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

}