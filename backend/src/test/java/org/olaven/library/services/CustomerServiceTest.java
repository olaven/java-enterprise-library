package org.olaven.library.services;

import org.junit.jupiter.api.Test;
import org.olaven.library.entities.Book;
import org.olaven.library.entities.Customer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


class CustomerServiceTest extends ServiceTestBase {

    @Test
    public void testBookCanBeLended() {

        Book book = bookMocker.getOne();
        Customer customer = customerMocker.getOne();

        long bookId = bookService.persistBook(book.getTitle(), book.getIsbn(), book.getAuthors());
        long customerId = customerService.persistCustomer(customer.getGivenName(), customer.getFamilyName(), customer.getLendedBooks());
    }

    @Test
    public void testLendedBooksCannotBeLended() {
        throw new NotImplementedException();
    }

    @Test
    public void testAvailableBooksMayBeLended() {
        throw new NotImplementedException();
    }
}