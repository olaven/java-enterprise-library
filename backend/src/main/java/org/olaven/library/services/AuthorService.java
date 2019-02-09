package org.olaven.library.services;

import org.olaven.library.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
}
