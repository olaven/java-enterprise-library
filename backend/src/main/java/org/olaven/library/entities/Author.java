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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(getId(), author.getId()) &&
                Objects.equals(getPerson(), author.getPerson()) &&
                Objects.equals(getBooks(), author.getBooks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPerson(), getBooks());
    }
}
