package org.olaven.library.services;

import org.olaven.library.entities.Book;
import org.olaven.library.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private BookService bookService;

    @Autowired
    private CustomerService customerService;

    @Async
    @Transactional
    public void borrowBook(long bookId, long customerId) throws Exception {

        Book book = bookService.getBookById(bookId, false);
        Customer customer = customerService.getCustomerById(customerId, true);

        if (book.getBorrower() != null)
            throw new Exception("Book already borrowed.");

        // have to update book (not customer), as book is the owner (i.e. "mapped by")
        book.setBorrower(customer); // update reflected in db, as method is transactional
    }

    @Async
    @Transactional
    public void deliverBook(long bookId, long customerId) throws Exception {

        Book book = bookService.getBookById(bookId, false);
        Customer customer = getCustomerById(customerId, true);

        if (!customer.getBorrowedBooks().contains(book)) {
            throw new Exception("Customer must borrow book to deliver it.");
        }

        book.setBorrower(null);
    }

    @Transactional
    public long persistCustomer(String givenName, String familyName, List<Book> borrowedBooks, String email) {

        Customer customer = new Customer();


        // NOTE: showcasing that this still points to the same author, which is not _actually_ persisted(commited) yet
        customer.setGivenName(givenName);
        customer.setFamilyName(familyName);
        customer.setBorrowedBooks(borrowedBooks);
        customer.setEmail(email);

        entityManager.persist(customer);

        return customer.getId();
    }

    @Transactional
    public Customer getCustomerById(long id, boolean withBorrowedBooks) {

        Query query = entityManager.createNamedQuery(Customer.GET_CUSTOMER_BY_ID, Customer.class);
        query.setParameter("id", id);

        Customer customer = (Customer) query.getSingleResult();

        if (withBorrowedBooks) {
            customer.getBorrowedBooks().size();
        }

        return customer;
    }

    public List<Book> getBorrowedBooksByCustomerId(long id) {

        Query query = entityManager.createNamedQuery(Customer.GET_BORROWED_BOOKS);
        query.setParameter("id", id);
        List<Book> books = query.getResultList();

        return books;
    }
}
