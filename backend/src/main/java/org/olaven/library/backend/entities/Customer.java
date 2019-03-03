package org.olaven.library.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Email;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = Customer.GET_CUSTOMER_BY_ID, query = "select customer from Customer customer where id = :id")
})
@Entity
public class Customer extends Person {

    public static final String GET_CUSTOMER_BY_ID = "GET_CUSTOMER_BY_ID";
    public static final String GET_BORROWED_BOOKS = "GET_BORROWED_BOOKS";

    @Email
    @Column(unique = true)
    private String email;

    public Customer() {
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
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getEmail(), customer.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getEmail());
    }
}
