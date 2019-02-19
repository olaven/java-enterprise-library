package org.olaven.library.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = Book.GET_ALL_BOOKS, query = "select book from Book book"),
        @NamedQuery(name = Book.GET_BOOK_BY_ID, query = "select book from Book book where book.id = :id")
})
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {

    public static final String GET_ALL_BOOKS = "GET_ALL_BOOKS";
    public static final String GET_BOOK_BY_ID = "GET_BOOK_BY_ID";

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String isbn;

    @NotBlank
    @Size(min = 2, max = 40)
    private String title;

    @NotNull
    @ManyToMany
    private List<Author> authors;

    @ManyToOne
    private Customer borrower;
}
