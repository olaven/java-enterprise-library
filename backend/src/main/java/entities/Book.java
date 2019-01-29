package entities;

import org.hibernate.annotations.Cascade;
import static org.hibernate.annotations.CascadeType.PERSIST;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Book.GET_BOOKS, query = "select book from Book book")
})

@Entity
public class Book {

    public final static String GET_BOOKS = "GET_BOOKS";

    @Id
    @GeneratedValue
    private int id; //isbn cannot be ID, as more books will have same isbn

    @NotNull
    private int isbn;

    @NotBlank
    @Size(max = 120)
    private String title;

    @ManyToMany
    @NotNull
    @Cascade(value = PERSIST)
    private List<Author> authors;

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
