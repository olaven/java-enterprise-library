package org.olaven.library.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class LendingInfo {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Book book;
    @ManyToOne
    private Customer customer;
}
