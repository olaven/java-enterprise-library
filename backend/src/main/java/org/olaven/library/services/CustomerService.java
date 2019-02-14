package org.olaven.library.services;

import org.olaven.library.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class CustomerService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    BookService bookService;

    @Async
    public void lendBook(long bookId) {

        // get the book
        // make sure that book is not lended (throw checked exception if is)
        // lend book to user
        Book book = bookService.getBookById(bookId);
    }
}
