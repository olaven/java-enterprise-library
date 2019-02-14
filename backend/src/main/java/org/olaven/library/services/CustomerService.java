package org.olaven.library.services;

import org.olaven.library.entities.Book;
import org.olaven.library.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

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
        Book book = bookService.getBookById(bookId, false);
    }

    @Transactional
    public long persistCustomer(String givenName, String familyName, List<Book> lendedBooks) {

        Customer customer = new Customer();
        entityManager.persist(customer);

        // NOTE: showcasing that this still points to the same author, which is not _actually_ persisted(commited) yet
        customer.setGivenName(givenName);
        customer.setFamilyName(familyName);
        customer.setLendedBooks(lendedBooks);

        return customer.getId();
    }
}
