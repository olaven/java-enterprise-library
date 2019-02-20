package org.olaven.library;


import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.*;


@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // needed for @BeforeAll
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // cleaning db
@Target(
        ElementType.TYPE // can use on classes and interfaces
)
@Retention(RetentionPolicy.RUNTIME) //specify it should end up in the bytecode and be readable using reflection
@Documented
public @interface MySpringTestConfiguration {}
