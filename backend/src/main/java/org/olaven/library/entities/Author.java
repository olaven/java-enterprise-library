package org.olaven.library.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Author.GET_ALL_AUTHORS, query = "select author from Author author")//,
        //@NamedQuery(name = Author.GET_BOOKS_BY_AUTHOR_ID, query = "select book from Book book where book.author.id = :id")// TODO: MANGE TIL MANGE, tror jeg trenger subquery
})
@Entity
public class Author extends Person {

    public static final String GET_ALL_AUTHORS = "GET_ALL_AUTHORS";
    public static final String GET_BOOKS_BY_AUTHOR_ID = "GET_BOOKS_BY_AUTHOR_ID";

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
