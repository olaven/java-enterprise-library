package org.olaven.library.mocker;

import org.olaven.library.entities.Customer;

import static org.olaven.library.StringUtil.randomString;

public class CustomerMocker extends Mocker {

    @Override
    public Customer getOne() {

        return Customer.builder()
                .givenName("given name")
                .familyName("family name")
                .email(randomString(5) + "@mail.com")
                .build();
    }
}
