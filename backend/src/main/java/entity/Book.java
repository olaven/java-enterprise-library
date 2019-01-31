package entity;

import org.hibernate.annotations.Cascade;
import static org.hibernate.annotations.CascadeType.PERSIST;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Book.GET_BOOKS, query = "select book from Book book"),
        @NamedQuery(name = Book.GET_BOOK_BY_ID, query = "select book from Book book where book.id = :id"),
        @NamedQuery(name = Book.REMOVE_BOOK_BY_ID, query = "delete from Book book where book.id = :id")
})

@Entity
public class Book {

    public final static String GET_BOOKS = "GET_BOOKS";
    public final static String GET_BOOK_BY_ID = "GET_BOOK_BY_ID";
    public final static String REMOVE_BOOK_BY_ID = "REMOVE_BOOK_BY_ID";

    @Id
    @GeneratedValue
    private int id; //isbn cannot be ID, as more books will have same isbn

    @NotNull
    private int isbn;

    @NotBlank
    @Size(max = 120)
    private String title;

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY) // is default
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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn=" + isbn +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                '}';
    }
}
