package org.olaven.library.entities;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = Author.GET_ALL_AUTHORS, query = "select author from Author author")
})
@Entity
public class Author extends Person {

    public static final String GET_ALL_AUTHORS = "GET_ALL_AUTHORS";

    @NotNull
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Author() {
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
