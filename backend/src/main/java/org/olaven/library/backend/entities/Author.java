package org.olaven.library.backend.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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

    public static String getGetAllAuthors() {
        return GET_ALL_AUTHORS;
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
        if (!super.equals(o)) return false;
        Author author = (Author) o;
        return Objects.equals(getBooks(), author.getBooks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBooks());
    }
}
