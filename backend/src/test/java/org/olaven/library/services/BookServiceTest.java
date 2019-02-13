package org.olaven.library.services;

import org.junit.Ignore;
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
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testCanInsertBook() {

        String title = "book title";
        String isbn = "isbnisbn";

        long id = bookService.insertBook(title, isbn, new ArrayList<>());
        Book retrieved = bookService.getBookById(id);

        assertEquals(title, retrieved.getTitle());
        assertEquals(isbn, retrieved.getIsbn());
        assertEquals(id, retrieved.getId());
    }


    @Test
    public void testCanInsertMultipleBooks() {

        int n = 20;

        for (int i = 0; i < n; i++) {
            bookService.insertBook("title", "isbn", new ArrayList<>());
        }

        List<Book> retrieved = bookService.getAllBooks();

        assertEquals(n, retrieved.size());

    }


    //TODO: WAY MORE TESTS
}