package org.olaven.library.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Customer extends Person {

    @OneToMany
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
