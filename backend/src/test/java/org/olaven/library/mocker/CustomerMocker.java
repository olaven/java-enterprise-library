package org.olaven.library.mocker;

import org.olaven.library.backend.entities.Customer;

import static org.olaven.library.backend.StringUtil.randomString;

public class CustomerMocker extends Mocker {

    @Override
    public Customer getOne() {

        Customer customer = new Customer();
        customer.setGivenName("given name");
        customer.setFamilyName("family name");
        customer.setEmail(randomString(5) + "@mail.com");

        return customer;
    }
}
