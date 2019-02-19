package org.olaven.library.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = Customer.GET_CUSTOMER_BY_ID, query = "select customer from Customer customer where id = :id"),
        @NamedQuery(name = Customer.GET_BORROWED_BOOKS, query = "select book from Book book where exists (select b from Book b where b.borrower.id = :id)")
})
@Entity
public class Customer extends Person {

    public static final String GET_CUSTOMER_BY_ID = "GET_CUSTOMER_BY_ID";
    public static final String GET_BORROWED_BOOKS = "GET_BORROWED_BOOKS";
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getId() == customer.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getBorrowedBooks());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "email='" + email + '\'' +
                ", id=" + id +
                ", givenName='" + givenName + '\'' +
                ", familyName='" + familyName + '\'' +
                '}';
    }
}
