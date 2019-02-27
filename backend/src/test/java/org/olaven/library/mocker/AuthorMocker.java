package org.olaven.library.mocker;

import org.olaven.library.entities.Author;

import java.util.ArrayList;

public class AuthorMocker extends Mocker<Author> {

    public Author getOne() {

        Author author = new Author();
        author.setGivenName("given name");
        author.setFamilyName("family name");
        author.setBooks(new ArrayList<>());

        return author;
    }
}
