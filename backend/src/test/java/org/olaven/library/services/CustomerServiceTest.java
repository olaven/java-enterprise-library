package org.olaven.library.services;

import org.junit.jupiter.api.Test;
import org.olaven.library.entities.Book;
import org.olaven.library.entities.Customer;

import java.util.ArrayList;

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
    public void testBorrowedBooksAreRegistered() throws InterruptedException {

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
    public void testBooksCannotBeBorrowedTwice() throws InterruptedException {

        long bookId = persistRandomBook();
        long customerId = persistRandomCustomer();

        customerService.borrowBook(bookId, customerId);
        Thread.sleep(200);

        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() -> {
            customerService.borrowBook(bookId, customerId);
        });
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