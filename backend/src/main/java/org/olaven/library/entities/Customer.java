package org.olaven.library.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Email;

@NamedQueries({
        @NamedQuery(name = Customer.GET_CUSTOMER_BY_ID, query = "select customer from Customer customer where id = :id")
})
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends Person {

    public static final String GET_CUSTOMER_BY_ID = "GET_CUSTOMER_BY_ID";
    public static final String GET_BORROWED_BOOKS = "GET_BORROWED_BOOKS";

    @Email
    @Column(unique = true)
    private String email;
}
