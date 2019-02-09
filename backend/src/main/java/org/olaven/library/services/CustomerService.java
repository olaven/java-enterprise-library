package org.olaven.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class CustomerService {

    @Autowired
    EntityManager entityManager;

    public void lendBook() {
        
    }
}
