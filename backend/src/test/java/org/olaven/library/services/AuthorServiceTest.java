package org.olaven.library.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.olaven.library.AuthorMocker;
import org.olaven.library.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Test
    public void testCanPersistAuthor() {

        Author author = AuthorMocker.getValidAuthor();
        authorService.persistAuthor(author);
    }

    @Test
    public void testCanRetrieveAuthor() {

        Author author = AuthorMocker.getValidAuthor();
        authorService.persistAuthor(author);

        Author retrieved = authorService.getAuthor(author.getId());
        assertNotNull(retrieved);
    }

}