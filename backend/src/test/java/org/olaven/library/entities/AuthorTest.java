package org.olaven.library.entities;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.olaven.library.AuthorMocker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AuthorTest {

    @Autowired
    private EntityManager entityManager; //nullpointerexception -> check if it works in service

    @Test
    public void testCanPersistAuthor() {

        Author author = AuthorMocker.getValidAuthor();
        entityManager.persist(author);

        Author retrieved = entityManager.find(Author.class, author.getId());

        assertNotNull(retrieved);
    }



}