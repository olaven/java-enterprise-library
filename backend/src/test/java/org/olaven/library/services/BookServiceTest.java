package org.olaven.library.services;

import org.junit.jupiter.api.Test;
import org.olaven.library.entities.Author;
import org.olaven.library.entities.Book;
import org.olaven.library.mocker.AuthorMocker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class BookServiceTest extends ServiceTestBase {

    @Test
    public void testCanInsertBook() {

        String title = "book title";
        String isbn = "isbnisbn";

        long id = bookService.insertBook(title, isbn, new ArrayList<>());
        Book retrieved = bookService.getBookById(id, false);


        assertThat(retrieved)
                .hasFieldOrPropertyWithValue("title", title);
        assertThat(retrieved)
                .hasFieldOrPropertyWithValue("id", id);
        assertThat(retrieved)
                .hasFieldOrPropertyWithValue("isbn", isbn);
    }


    @Test
    public void testCanInsertMultipleBooks() {

        int n = 20;

        for (int i = 0; i < n; i++) {
            bookService.insertBook("title", "isbn", new ArrayList<>());
        }

        List<Book> retrieved = bookService.getAllBooks();

        assertThat(retrieved.size())
                .isEqualTo(n);
    }


    @Test
    public void testAuthorsAreLoadedLazily() {

        List<Author> authors = new AuthorMocker().getMany(10);
        for(Author author: authors) {
            authorService.persistAuthor(author);
        }

        long id = bookService.insertBook("some book", "isbn", authors);

        Book retrieved = bookService.getBookById(id, false);
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            retrieved.getAuthors().size();
        });

        Book withAuthors = bookService.getBookById(id, true);
        withAuthors.getAuthors().size(); // NOTE: no exception!
    }

    //TODO: WAY MORE TESTS
}