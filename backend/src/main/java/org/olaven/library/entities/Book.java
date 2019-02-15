package org.olaven.library.entities;

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

    public Book() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getBorrower() {
        return borrower;
    }

    public void setBorrower(Customer borrower) {
        this.borrower = borrower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getIsbn(), book.getIsbn()) &&
                Objects.equals(getTitle(), book.getTitle()) &&
                Objects.equals(getAuthors(), book.getAuthors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIsbn(), getTitle(), getAuthors());
    }
}
