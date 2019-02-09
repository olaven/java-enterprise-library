package org.olaven.library;

import org.olaven.library.entities.Author;
import org.olaven.library.entities.Book;

import java.util.ArrayList;

public class AuthorMocker {
    public static Author getValidAuthor() {
        Author author = new Author();

        author.setGivenName("given name");
        author.setFamilyName("family name");
        author.setBooks(new ArrayList<Book>());

        return author;
    }
}
