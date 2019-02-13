package org.olaven.library.entities;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @Valid // make sure that validation is run on embeddable as well
    private Person person;

    @OneToMany(mappedBy = "lender")
    private List<Book> lendedBooks;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Book> getLendedBooks() {
        return lendedBooks;
    }

    public void setLendedBooks(List<Book> lendedBooks) {
        this.lendedBooks = lendedBooks;
    }
}
