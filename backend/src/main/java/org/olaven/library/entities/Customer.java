package org.olaven.library.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = Customer.GET_CUSTOMER_BY_ID, query = "select customer from Customer customer where id = :id"),
        @NamedQuery(name = Customer.GET_BORROWED_BOOKS, query = "select book from Book book where exists (select b from Book b where b.borrower.id = :id)")
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

    @OneToMany(mappedBy = "borrower")
    private List<Book> borrowedBooks;
}
