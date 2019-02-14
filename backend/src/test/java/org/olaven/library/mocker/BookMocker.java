package org.olaven.library.mocker;

import org.olaven.library.entities.Author;
import org.olaven.library.entities.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.olaven.library.StringUtil.randomString;

public class BookMocker extends Mocker<Book> {

    @Override
    public Book getOne() {
        Book book = new Book();

        book.setAuthors(new ArrayList<Author>());
        book.setIsbn(randomString(10));
        book.setTitle(randomString(5));


        return book;
    }
}


