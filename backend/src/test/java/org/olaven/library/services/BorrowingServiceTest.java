package org.olaven.library.services;


import org.junit.jupiter.api.Test;
import org.olaven.library.entities.Book;
import org.olaven.library.entities.Record;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


class BorrowingServiceTest extends ServiceTestBase {

    @Test
    public void testCustomerMayBorrowBook() throws Exception {

        long customerId = persistRandomCustomer();
        long bookId = persistRandomBook();

        List<Book> booksBefore = borrowingService.getBooksBorrowedBy(customerId);
        assertThat(booksBefore).asList()
                .isEmpty();

        borrowingService.borrowBook(bookId, customerId);

        List<Book> booksAfter = borrowingService.getBooksBorrowedBy(customerId);
        assertThat(booksAfter).asList()
                .isNotEmpty();

    }

    @Test
    public void testCustomerMayBorrowMultipleBooks() throws Exception {

        long customerId = persistRandomCustomer();

        long firstBookId = persistRandomBook();
        long secondBookId = persistRandomBook();
        long thirdBookId = persistRandomBook();

        borrowingService.borrowBook(firstBookId, customerId);
        borrowingService.borrowBook(secondBookId, customerId);
        borrowingService.borrowBook(thirdBookId, customerId);

        List<Book> borrowed = borrowingService.getBooksBorrowedBy(customerId);
        assertThat(borrowed).asList()
                .size()
                .isEqualTo(3);
    }

    @Test
    public void canDeliverBook() throws Exception {

        long customerId = persistRandomCustomer();
        long bookId = persistRandomBook();

        borrowingService.borrowBook(bookId, customerId);

        List<Book> borrowedBefore = borrowingService.getBooksBorrowedBy(customerId);
        assertThat(borrowedBefore).asList()
                .size()
                .isEqualTo(1);

        borrowingService.deliverBook(bookId, customerId);
        Thread.sleep(2000);

        List<Book> borrowedAfter = borrowingService.getBooksBorrowedBy(customerId);
        assertThat(borrowedAfter).asList()
                .size()
                .isEqualTo(0);

    }

    @Test
    public void canOnlyDeliverBorrowedBooks() throws Exception {

        long bookId = persistRandomBook();
        long customerId = persistRandomCustomer();

        // notice: the book is never borrowed

        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            borrowingService.deliverBook(bookId, customerId);
        });
    }

    @Test
    public void canOnlyDeliverBooksBorrowedBySameCustomer() throws Exception {

        long bookId = persistRandomBook();
        long firstCustomer = persistRandomCustomer();
        long otherCustomer = persistRandomCustomer();

        borrowingService.borrowBook(bookId, firstCustomer);
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            borrowingService.deliverBook(bookId, otherCustomer);
        });
    }

    @Test
    public void datesMatchDateBorrowed() throws Exception {

        Date currentDate = new Date();

        persistMultipleRecords(25);
        List<Record> records = borrowingService.getAllRecords();
        for(Record record: records) {
            assertThat(record.getDate().getDay())
                    .isEqualTo(currentDate.getDay());
        }
    }


    @Test
    public void testBooksCannotBeBorrowedTwice() throws Exception {

        long bookId = persistRandomBook();
        long customerId = persistRandomCustomer();

        borrowingService.borrowBook(bookId, customerId);// No exception first time.
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            borrowingService.borrowBook(bookId, customerId);
        });

    }



    private void persistMultipleRecords(int n) throws Exception {

        Random random = new Random();
        long customerId = persistRandomCustomer();

        for (int i = 0; i < n; i++) {
            if (random.nextInt() == 0) {
                customerId = persistRandomCustomer();
            }

            long bookid = persistRandomBook();
            borrowingService.borrowBook(bookid, customerId);
        }
    }

}