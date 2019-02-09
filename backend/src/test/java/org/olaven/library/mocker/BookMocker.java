package org.olaven.library.mocker;

import org.olaven.library.entities.Author;
import org.olaven.library.entities.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookMocker extends Mocker<Book> {

    @Override
    public Book getOne() {
        Book book = new Book();

        book.setAuthors(new ArrayList<Author>());
        book.setIsbn(randomString(10));
        book.setTitle(randomString(5));

        return book;
    }

    private String randomString(int n) {

        Random random = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String string = "";
        for (int i = 0; i < n; i++) {
            int index = random.nextInt(alphabet.length());
            string= string + alphabet.charAt(index);
        }

        return string;
    }
}


