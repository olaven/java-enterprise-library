package org.olaven.library.backend.services;

import org.olaven.library.backend.entities.Author;
import org.olaven.library.backend.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    @Autowired
    EntityManager entityManager;

    public List<Book> getAllBooks() {

        Query query = entityManager.createNamedQuery(Book.GET_ALL_BOOKS, Book.class);
        return query.getResultList();
    }

    @Transactional
    public Book getBookById(long id, boolean withAuthors) {

        Query query = entityManager.createNamedQuery(Book.GET_BOOK_BY_ID, Book.class);
        query.setParameter("id", id);
        List<Book> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }

        Book book = results.get(0);

        if (withAuthors) {
            book.getAuthors().size(); // invoking to load, as fetchtype = lazy
        }

        return book;
    }

    @Transactional
    public long persistBook(String title, String isbn, List<Author> authors) {

        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setAuthors(authors);

        entityManager.persist(book);
        return book.getId();
    }
}
