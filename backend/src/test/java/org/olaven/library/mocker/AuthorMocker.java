package org.olaven.library.mocker;

import org.olaven.library.entities.Author;

import java.util.ArrayList;

public class AuthorMocker extends Mocker<Author> {

    public Author getOne() {

        return Author.builder()
                .givenName("given name")
                .familyName("family name")
                .books(new ArrayList<>())
                .build();
    }
}
