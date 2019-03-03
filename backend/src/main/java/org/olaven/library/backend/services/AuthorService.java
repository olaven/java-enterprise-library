package org.olaven.library.backend.services;

import org.olaven.library.backend.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
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
    public Author getAuthor(long id, boolean withBooks) {

        Author author = entityManager.find(Author.class, id);

        if (!withBooks) {
            return author;
        }

        // calling something to fetch, as fetctype is lazy
        author.getBooks().size();

        return author;
    }

    @Transactional
    public List<Author> getAllAuthors() {

        List<Author> authors = entityManager.createNamedQuery(Author.GET_ALL_AUTHORS, Author.class)
                .getResultList();

        return authors;
    }
}
