package org.olaven.library.services;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.olaven.library.entities.Book;
import org.olaven.library.mocker.AuthorMocker;
import org.olaven.library.entities.Author;
import org.olaven.library.mocker.BookMocker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    private AuthorMocker authorMocker = new AuthorMocker();


    @Test
    public void testCanPersistAuthor() {

        Author author = authorMocker.getOne();
        authorService.persistAuthor(author);
    }

    @Test
    public void testCanRetrieveAuthor() {

        Author author = authorMocker.getOne();
        authorService.persistAuthor(author);

        Author retrieved = authorService.getAuthor(author.getId());
        assertNotNull(retrieved);
    }

    @Test
    public void testCanRetrieveAll() {

        int n = 10;
        persistSeveral(n);

        List<Author> retrieved = authorService.getAllAuthors();
        assertEquals(n, retrieved.size());
    }

    @Test
    public void testAuthorWithInvalidNamesAreNotpersisted() {
        Author author = authorMocker.getOne();
        author.setGivenName("x"); // has to be >=2

        assertThrows(Exception.class, () -> {
            authorService.persistAuthor(author);
        });
    }

    @Test @Ignore //TODO: tar ikke høyde for lazy loading
    public void testCanGetBook() {
        BookMocker bookMocker = new BookMocker();
        List<Book> books = bookMocker.getMany(20);

        Author author = authorMocker.getOne();
        author.setBooks(books);

        authorService.persistAuthor(author);

        Author retrieved = authorService.getAuthor(author.getId());

        for (int i = 0; i < author.getBooks().size(); i++) {
            assertEquals(author.getBooks().get(i), retrieved.getBooks().get(i));
        }
    }

    private void persistSeveral(int n) {
        for (Author author : authorMocker.getMany(n)) {
            authorService.persistAuthor(author);
        }
    }
}