package org.olaven.library.backend.services;

import org.junit.jupiter.api.BeforeAll;
import org.olaven.library.MySpringTestConfiguration;
import org.olaven.library.backend.entities.Book;
import org.olaven.library.backend.entities.Customer;
import org.olaven.library.mocker.AuthorMocker;
import org.olaven.library.mocker.BookMocker;
import org.olaven.library.mocker.CustomerMocker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@MySpringTestConfiguration
@SpringBootTest
public class ServiceTestBase {

    protected BookMocker bookMocker;
    protected CustomerMocker customerMocker;
    protected AuthorMocker authorMocker;

    @Autowired
    protected BookService bookService;
    @Autowired
    protected CustomerService customerService;
    @Autowired
    protected AuthorService authorService;
    @Autowired
    protected BorrowingService borrowingService;

    @BeforeAll
    public void initBeforeAll() {
        bookMocker = new BookMocker();
        customerMocker = new CustomerMocker();
        authorMocker = new AuthorMocker();
    }

    protected long persistRandomBook() {

        Book mockBook = bookMocker.getOne();
        long bookId = bookService.persistBook(mockBook.getTitle(), mockBook.getIsbn(), mockBook.getAuthors());

        return bookId;
    }

    protected long persistRandomCustomer() {

        Customer mockCustomer = customerMocker.getOne();
        long customerId = customerService.persistCustomer(mockCustomer.getGivenName(), mockCustomer.getFamilyName(), mockCustomer.getEmail());

        return customerId;
    }

    protected void persistCustomerWithEmail(String email) {

        Customer mockCustomer = customerMocker.getOne();
        customerService.persistCustomer(mockCustomer.getGivenName(), mockCustomer.getFamilyName(), email);
    }

}
