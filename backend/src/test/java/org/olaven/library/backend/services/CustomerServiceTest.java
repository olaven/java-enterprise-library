package org.olaven.library.backend.services;

import org.junit.jupiter.api.Test;
import org.olaven.library.backend.entities.Customer;

import static org.assertj.core.api.AssertionsForClassTypes.*;


class CustomerServiceTest extends ServiceTestBase {

    @Test
    public void testCanPersistCustomer() {

        long id = persistRandomCustomer();

        Customer retrieved = customerService.getCustomerById(id);
        assertThat(retrieved)
                .isNotNull();
    }

    @Test
    public void testEmailMustBeUnique() {

        String mail = "duplicate@mail.com";
        customerService.persistCustomer("some_given_name", "some_family_name", mail);

        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            // Note: different except email!
            customerService.persistCustomer("other_given_name", "other_family_name", mail);
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

}