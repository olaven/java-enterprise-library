package org.olaven.library.services;

import org.olaven.library.entities.Author;
import org.olaven.library.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void persistAuthor(Author author) {
        entityManager.persist(author);
    }

    @Transactional
    public Author getAuthor(long id) {
        return entityManager.find(Author.class, id);
    }

    @Transactional
    public List<Author> getAllAuthors() {

        List<Author> authors = entityManager.createNamedQuery(Author.GET_ALL_AUTHORS, Author.class)
                .getResultList();

        return authors;
    }

    @Transactional
    public List<Book> getBooks(long authorId) {

        Query query = entityManager.createNamedQuery(Author.GET_BOOKS_BY_AUTHOR_ID, Book.class);
        query.setParameter("id", authorId);

        return query.getResultList(); 
    }
}
