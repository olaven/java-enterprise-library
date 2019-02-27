package org.olaven.library.services;

import org.olaven.library.BookStatus;
import org.olaven.library.entities.Book;
import org.olaven.library.entities.Customer;
import org.olaven.library.entities.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class BorrowingService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    BookService bookService;
    @Autowired
    CustomerService customerService;

    @Async
    @Transactional
    public void borrowBook(long bookId, long customerId) throws Exception {

        if (bookIsBorrowed(bookId)) {
            throw new Exception("book is already borrowed");
        }

        Book book = bookService.getBookById(bookId, false);
        Customer customer = customerService.getCustomerById(customerId);

        Record record = new Record();
        record.setBook(book);
        record.setCustomer(customer);
        record.setDate(new Date());
        record.setStatus(BookStatus.BORROWED);

        entityManager.persist(record);
    }

    @Async
    @Transactional
    public void deliverBook(long bookId, long customerId) throws Exception {

        Record record  = getRecordByBookAndCustomer(bookId, customerId);
        if (record == null) {
            throw new Exception("Cannot deliver book. It is not registered as borrowed.");
        }

        long borrowerId = record.getCustomer().getId();
        if (borrowerId != customerId) {
            throw new Exception("Book can not be delivered. Borrowed by someone else");
        }


        record.setStatus(BookStatus.AVAILABLE);
        entityManager.merge(record);
    }

    public List<Record> getAllRecords() {

        List<Record> records = entityManager.createNamedQuery(Record.GET_ALL_RECORDS, Record.class).getResultList();
        return records;
    }

    public List<Book> getBooksBorrowedBy(long customerId) {

        Query query = entityManager.createNamedQuery(Record.BOOKS_BORROWED_BY_CUSTOMER, Book.class);
        query.setParameter("id", customerId);

        return query.getResultList();
    }


    private Record getRecordByBookAndCustomer(long bookId, long customerId) {
        Query query = entityManager.createNamedQuery(Record.BORROWED_RECORD_BY_BOOK_AND_CUSTOMER, Record.class);
        query.setParameter("bookId", bookId);
        query.setParameter("customerId", bookId);

        return (Record) query.getSingleResult();
    }

    private boolean bookIsBorrowed(long bookId) {

        Query query = entityManager.createNamedQuery(Record.BOOK_WITH_STATUS, Record.class);
        query.setParameter("bookId", bookId);
        query.setParameter("status", BookStatus.BORROWED);

        return query.getResultList().size() > 0;
    }

    public List<Record> getRecordsPastDelivery() {
        throw new NotImplementedException();
    }
}
