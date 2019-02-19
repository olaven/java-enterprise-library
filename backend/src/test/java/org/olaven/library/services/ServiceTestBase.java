package org.olaven.library.services;

import org.junit.jupiter.api.BeforeAll;
import org.olaven.library.MySpringTestConfiguration;
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

    @BeforeAll
    public void initBeforeAll() {
        bookMocker = new BookMocker();
        customerMocker = new CustomerMocker();
        authorMocker = new AuthorMocker();
    }

}
