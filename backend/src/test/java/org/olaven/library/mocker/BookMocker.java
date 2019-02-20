package org.olaven.library.mocker;

import org.olaven.library.entities.Book;

import java.util.ArrayList;

import static org.olaven.library.StringUtil.randomString;

public class BookMocker extends Mocker<Book> {

    @Override
    public Book getOne() {

        return Book.builder()
                .authors(new ArrayList<>())
                .isbn(randomString(10))
                .title(randomString(5))
                .build();
    }
}


