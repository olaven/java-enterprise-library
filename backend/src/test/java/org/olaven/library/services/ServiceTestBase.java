package org.olaven.library.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.olaven.library.mocker.AuthorMocker;
import org.olaven.library.mocker.BookMocker;
import org.olaven.library.mocker.CustomerMocker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // needed for @BeforeAll
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // cleaning db
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
