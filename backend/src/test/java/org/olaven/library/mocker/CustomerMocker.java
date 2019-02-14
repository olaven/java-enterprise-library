package org.olaven.library.mocker;

import org.olaven.library.StringUtil;
import org.olaven.library.entities.Customer;
import org.olaven.library.entities.Person;

import java.util.ArrayList;

import static org.olaven.library.StringUtil.randomString;

public class CustomerMocker extends Mocker {

    @Override
    public Customer getOne() {

        Customer customer = new Customer();

        customer.setGivenName("given name");
        customer.setFamilyName("family name");
        customer.setLendedBooks(new ArrayList<>());
        customer.setEmail(randomString(30));

        return customer;
    }
}
