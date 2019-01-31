package ejb;

import entity.Author;
import entity.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class BookBean {

    @PersistenceContext // initialized by container. Also provides transaction automatically
    private EntityManager entityManager;

    public long insertBook(int isbn, String title, List<Author> authors) {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthors(authors);

        entityManager.persist(book);

        return book.getId();
    }

    public void removeBook(long id) {
        Query query = entityManager.createNamedQuery(Book.REMOVE_BOOK_BY_ID);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
