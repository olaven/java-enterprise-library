package org.olaven.library.mocker;

import org.olaven.library.entities.Customer;
import org.olaven.library.entities.Person;

import java.util.ArrayList;

public class CustomerMocker extends Mocker {

    @Override
    public Customer getOne() {

        Customer customer = new Customer();

        customer.setGivenName("given name");
        customer.setFamilyName("family name");
        customer.setLendedBooks(new ArrayList<>());

        return customer;
    }
}
