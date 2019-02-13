package org.olaven.library.entities;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Author.GET_ALL_AUTHORS, query = "select author from Author author")
})
@Entity
public class Author{

    public static final String GET_ALL_AUTHORS = "GET_ALL_AUTHORS";

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @Valid // make sure that validation is run on embeddable as well
    private Person person;

    @NotNull
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Author() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
