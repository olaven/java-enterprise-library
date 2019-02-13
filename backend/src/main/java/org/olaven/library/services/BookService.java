package org.olaven.library.services;

import org.olaven.library.entities.Author;
import org.olaven.library.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
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
    public Book getBookById(long id) {

        Query query = entityManager.createNamedQuery(Book.GET_BOOK_BY_ID, Book.class);
        query.setParameter("id", id);
        List<Book> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

    @Transactional
    public long insertBook(String title, String isbn, ArrayList<Author> authors) {

        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setAuthors(authors);

        entityManager.persist(book);
        return book.getId();
    }
}
