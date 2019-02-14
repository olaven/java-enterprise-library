package org.olaven.library.services;

import org.junit.jupiter.api.Test;
import org.olaven.library.entities.Author;
import org.olaven.library.entities.Book;
import org.olaven.library.mocker.BookMocker;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorServiceTest extends ServiceTestBase {

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
        author.setGivenName("x"); // has to be >=2

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