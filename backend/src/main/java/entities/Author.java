package entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Author.GET_AUTHORS, query = "select author from Author author"),
        @NamedQuery(name = Author.GET_WITH_ID, query = "select author from Author author where author.id = :id")
})

@Entity
public class Author extends Person {

    public static final String GET_AUTHORS = "GET_AUTHORS";
    public static final String GET_WITH_ID = "GET_WITH_ID";

    @ManyToMany(mappedBy = "authors")
    @NotNull
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
