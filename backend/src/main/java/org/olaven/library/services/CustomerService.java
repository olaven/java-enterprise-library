package org.olaven.library.services;

import org.olaven.library.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Service
public class CustomerService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private BookService bookService;

    @Autowired
    private CustomerService customerService;

    @Transactional
    public long persistCustomer(String givenName, String familyName, String email) {

        Customer customer = new Customer();

        customer.setGivenName(givenName);
        customer.setFamilyName(familyName);
        customer.setEmail(email);

        entityManager.persist(customer);

        return customer.getId();
    }

    @Transactional
    public Customer getCustomerById(long id) {

        Query query = entityManager.createNamedQuery(Customer.GET_CUSTOMER_BY_ID, Customer.class);
        query.setParameter("id", id);

        Customer customer = (Customer) query.getSingleResult();


        return customer;
    }

}
