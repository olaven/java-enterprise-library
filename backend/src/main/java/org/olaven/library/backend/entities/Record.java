package org.olaven.library.backend.entities;

import org.olaven.library.backend.BookStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = Record.GET_ALL_RECORDS, query = "select record from Record record"),
        @NamedQuery(name = Record.BOOKS_BORROWED_BY_CUSTOMER, query = "select record.book from Record record where record.customer.id = :id and record.status = 'BORROWED'"),
        @NamedQuery(name = Record.BOOK_WITH_STATUS, query = "select record from Record record where record.book.id = :bookId and record.status = :status"),
        @NamedQuery(name = Record.BORROWED_RECORD_BY_BOOK_AND_CUSTOMER, query = "select record from Record record where record.book.id = :bookId and record.customer.id = :customerId and record.status = 'BORROWED'")
})
@Entity
public class Record {

    //TODO: Implement locks to prevent race conditions

    public static final String GET_ALL_RECORDS = "GET_ALL_RECORDS";
    public static final String BOOKS_BORROWED_BY_CUSTOMER = "BOOKS_BORROWED_BY_CUSTOMER ";
    public static final String BOOK_WITH_STATUS = "BOOK_WITH_STATUS";
    public static final String BORROWED_RECORD_BY_BOOK_AND_CUSTOMER = "BORROWED_RECORD_BY_BOOK_AND_CUSTOMER";

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Customer customer;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    public Record() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Record)) return false;
        Record record = (Record) o;
        return Objects.equals(getId(), record.getId()) &&
                Objects.equals(getBook(), record.getBook()) &&
                Objects.equals(getCustomer(), record.getCustomer()) &&
                Objects.equals(getDate(), record.getDate()) &&
                getStatus() == record.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBook(), getCustomer(), getDate(), getStatus());
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}
