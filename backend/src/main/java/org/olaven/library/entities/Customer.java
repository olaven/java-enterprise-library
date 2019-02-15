package org.olaven.library.entities;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
