package org.olaven.library.services;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.olaven.library.entities.Book;
import org.olaven.library.entities.Customer;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.*;


class CustomerServiceTest extends ServiceTestBase {

    @Test
    public void testCanPersistCustomer() {

        long id = persistRandomCustomer();

        Customer retrieved = customerService.getCustomerById(id, false);
        assertThat(retrieved)
                .isNotNull();
    }

    @Test
    public void testBorrowedBooksAreLazilyLoaded() {

        long id = persistRandomCustomer();

        Customer without = customerService.getCustomerById(id, false);
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            without.getBorrowedBooks().size();
        });

        Customer with = customerService.getCustomerById(id, true);
        try {
            with.getBorrowedBooks().size();
        } catch (Exception e) {
            fail("Exception was thrown");
        }
    }

    @Test
    public void testBorrowedBooksAreRegistered() throws Exception {

        long bookId = persistRandomBook();
        long customerId = persistRandomCustomer();

        customerService.borrowBook(bookId, customerId);
        Thread.sleep(200); // as borrowing is async, waiting to be sure

        Customer customer = customerService.getCustomerById(customerId, true);

        long found = customer.getBorrowedBooks().stream()
                .filter(book -> book.getId() == bookId)
                .count();

        assertThat(found)
                .isGreaterThan(0);
    }

    @Test
    public void testBooksCannotBeBorrowedTwice() throws Exception {

        long bookId = persistRandomBook();
        long customerId = persistRandomCustomer();

        customerService.borrowBook(bookId, customerId);
        Thread.sleep(200);

        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            customerService.borrowBook(bookId, customerId);
        });
    }

    @Test
    public void testCanDeliverBook() throws Exception {

        long bookId = persistRandomBook();
        long customerId = persistRandomCustomer();

        customerService.borrowBook(bookId, customerId);

        Customer beforeDelivery = customerService.getCustomerById(customerId, true);
        assertThat(beforeDelivery.getBorrowedBooks()).asList()
                .filteredOn("id", bookId)
                .isNotEmpty();

        customerService.deliverBook(bookId, customerId);
        Customer afterDelivery = customerService.getCustomerById(customerId, true);
        assertThat(afterDelivery.getBorrowedBooks()).asList()
                .filteredOn("id", bookId)
                .isEmpty();
    }

    @Test
    public void testCannotDeliverBookWithoutBorrowing() {

        long bookId = persistRandomBook();
        long customerId = persistRandomCustomer();

        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            customerService.deliverBook(bookId, customerId);
        });
    }

    @Test
    public void testDeliveringUpdatesBothCustomerAndBook() throws Exception {

        long bookId = persistRandomBook();
        long customerId = persistRandomCustomer();

        // BEFORE BORROWING:
        Book bookBefore = bookService.getBookById(bookId, false);
        Customer customerBefore = customerService.getCustomerById(customerId, true);
        assertThat(customerBefore.getBorrowedBooks()).asList()
                .doesNotContain(bookBefore);
        assertThat(bookBefore.getBorrower())
                .isNull();

        customerService.borrowBook(bookId, customerId);

        // AFTER BORROWING:
        Book bookAfter = bookService.getBookById(bookId, false);
        Customer customerAfter = customerService.getCustomerById(customerId, true);

        assertThat(customerAfter.getBorrowedBooks()).asList()
                .contains(bookAfter);

        assertThat(bookAfter.getBorrower())
                .isNotNull();
    }

    @Test @Ignore
    public void testCustomerMayBorrowMultipleBooks() throws Exception {

        long customerId = persistRandomCustomer();

        long firstBook = persistRandomBook();
        long secondBook = persistRandomBook();
        long thirdBook = persistRandomBook();

        customerService.borrowBook(firstBook, customerId);
        customerService.borrowBook(secondBook, customerId);
        customerService.borrowBook(thirdBook, customerId);

        List<Book> borrowed = customerService.getBorrowedBooksByCustomerId(customerId);
        assertThat(borrowed).asList()
                .size()
                .isEqualTo(3);
    }


    @Test
    public void canGetAllBorrowedBooks() throws Exception {


        long customerId = persistRandomCustomer();

        List<Book> beforeBorrow = customerService.getBorrowedBooksByCustomerId(customerId);
        assertThat(beforeBorrow).asList()
                .size()
                .isEqualTo(0);

        long bookOneId = persistRandomBook();
        long bookTwoId = persistRandomBook();
        long bookThreeId = persistRandomBook();

        customerService.borrowBook(bookOneId, customerId);
        customerService.borrowBook(bookTwoId, customerId);
        customerService.borrowBook(bookThreeId, customerId);

        List<Book> afterBorrow = customerService.getBorrowedBooksByCustomerId(customerId);
        assertThat(afterBorrow).asList()
                .size()
                .isEqualTo(3);
    }


    @Test
    public void testEmailMustBeUnique() {

        String mail = "duplicate@mail.com";
        customerService.persistCustomer("some_given_name", "some_family_name", new ArrayList<>(), mail);

        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            // Note: different except email!
            customerService.persistCustomer("other_given_name", "other_family_name", new ArrayList<>(), mail);
        });
    }

    @Test
    public void testEmailMustBeValid() {

        try {
            persistCustomerWithEmail("valid@mail.com");
        } catch (Exception e) {
            fail("exception was thrown");
        }

        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            persistCustomerWithEmail("invalid-email");
        });

        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            persistCustomerWithEmail("anotherInvalid@");
        });

        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            persistCustomerWithEmail("@nother@invalid.com");
        });
    }

    private long persistRandomBook() {

        Book mockBook = bookMocker.getOne();
        long bookId = bookService.persistBook(mockBook.getTitle(), mockBook.getIsbn(), mockBook.getAuthors());

        return bookId;
    }

    private long persistRandomCustomer() {

        Customer mockCustomer = customerMocker.getOne();
        long customerId = customerService.persistCustomer(mockCustomer.getGivenName(), mockCustomer.getFamilyName(), mockCustomer.getBorrowedBooks(), mockCustomer.getEmail());

        return customerId;
    }

    private void persistCustomerWithEmail(String email) {

        Customer mockCustomer = customerMocker.getOne();
        customerService.persistCustomer(mockCustomer.getGivenName(), mockCustomer.getFamilyName(), mockCustomer.getBorrowedBooks(), email);
    }

}