package org.olaven.library.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Customer.GET_CUSTOMER_BY_ID, query = "select customer from Customer customer where id = :id")
})
@Entity
public class Customer extends Person {

    public static final String GET_CUSTOMER_BY_ID = "GET_CUSTOMER_BY_ID";
    @Email
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "borrower")
    private List<Book> borrowedBooks;

    public Customer() {
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
