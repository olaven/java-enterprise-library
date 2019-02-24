package org.olaven.library.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.olaven.library.BookStatus;

import javax.persistence.*;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = Record.GET_ALL_RECORDS, query = "select record from Record record"),
        @NamedQuery(name = Record.BOOKS_BORROWED_BY_CUSTOMER, query = "select record.book from Record record where record.customer.id = :id and record.status = 'BORROWED'"),
        @NamedQuery(name = Record.BOOK_WITH_STATUS, query = "select record from Record record where record.book.id = :bookId and record.status = :status"),
        @NamedQuery(name = Record.BORROWED_RECORD_BY_BOOK_AND_CUSTOMER, query = "select record from Record record where record.book.id = :bookId and record.customer.id = :customerId and record.status = 'BORROWED'")
})
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Record {

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
}
