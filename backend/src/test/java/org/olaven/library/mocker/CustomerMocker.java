package org.olaven.library.mocker;

import org.olaven.library.entities.Customer;

import java.util.ArrayList;

import static org.olaven.library.StringUtil.randomString;

public class CustomerMocker extends Mocker {

    @Override
    public Customer getOne() {

        return Customer.builder()
                .givenName("given name")
                .familyName("family name")
                .borrowedBooks(new ArrayList<>())
                .email(randomString(5) + "@mail.com")
                .build();
    }
}
