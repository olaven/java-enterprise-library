package org.olaven.library.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BookToCustomer {

    @Id
    @GeneratedValue
    private Long Id;

    @ManyToOne
    private Book book;
    @ManyToOne
    private Customer customer;
}
