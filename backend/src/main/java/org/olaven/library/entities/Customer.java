package org.olaven.library.entities;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Entity
public class Customer extends Person {

    @OneToMany(mappedBy = "lender")
    private List<Book> lendedBooks;

    public Customer() {
    }

    public List<Book> getLendedBooks() {
        return lendedBooks;
    }

    public void setLendedBooks(List<Book> lendedBooks) {
        this.lendedBooks = lendedBooks;
    }
}
