package org.olaven.library.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.olaven.library.entities.Author;
import org.olaven.library.entities.Book;
import org.olaven.library.mocker.AuthorMocker;
import org.olaven.library.mocker.BookMocker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    private AuthorMocker authorMocker = new AuthorMocker();


    @Test
    public void testCanPersistAndRetrieveAuthor() {

        Author author = authorMocker.getOne();
        authorService.persistAuthor(author);

        Author retrieved = authorService.getAllAuthors().get(0);

        assertThat(retrieved)
                .isNotNull();
    }


    @Test
    public void testCanRetrieveAll() {

        int n = 10;
        persistSeveral(n);

        List<Author> retrieved = authorService.getAllAuthors();
        assertEquals(n, retrieved.size());
        assertThat(retrieved.size())
                .isEqualTo(n);
    }

    @Test
    public void testAuthorWithInvalidNamesAreNotpersisted() {

        Author author = authorMocker.getOne();
        author.getPerson().setGivenName("x"); // has to be >=2

        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            authorService.persistAuthor(author);
        });
    }

    @Test
    public void testBooksAreLoadedLazily() {
        BookMocker bookMocker = new BookMocker();
        List<Book> books = bookMocker.getMany(20);

        Author author = authorMocker.getOne();
        author.setBooks(books);

        authorService.persistAuthor(author);

        Author withoutBooks = authorService.getAuthor(author.getId(), false);
        Author withBooks = authorService.getAuthor(author.getId(), true);

        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            withoutBooks.getBooks().size();
        });

        // NOTE: no exception
        withBooks.getBooks().size();

    }


    private void persistSeveral(int n) {
        for (Author author : authorMocker.getMany(n)) {
            authorService.persistAuthor(author);
        }
    }
}