package org.olaven.library.mocker;

import org.olaven.library.entities.Author;
import org.olaven.library.entities.Book;
import org.olaven.library.entities.Person;

import java.util.ArrayList;

public class AuthorMocker extends Mocker<Author> {

    public Author getOne() {

        Author author = new Author();

        author.setGivenName("given name");
        author.setFamilyName("family name");
        author.setBooks(new ArrayList<Book>());

        return author;
    }
}
