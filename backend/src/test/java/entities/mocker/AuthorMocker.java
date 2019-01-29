package entities.mocker;

import entities.Author;
import entities.Book;

import java.util.ArrayList;
import java.util.List;

public class AuthorMocker extends Mocker<Author> {

    private String[] givenNames = {
            "James", "Oliver", "Filip", "Ola", "Jens", "Siv", "Abid", "Lise", "Jonas",
            "Kristian", "Julie", "Guro", "Karsten", "Simen", "Samuel", "Severin", "Ole"
    };

    private String[] familyNames = {
            "Johnson", "Seattle", "Christensen", "Abberdale", "Si", "Okkenheim",
            "Dale", "Rester", "Sestinser", "Igger", "Abberman", "Quent", "Claus"
    };

    @Override
    public Author getOne() {
        Author author = new Author();
        author.setGivenName(getRandomFrom(givenNames));
        author.setFamilyName(getRandomFrom(familyNames));
        author.setBooks(new ArrayList<Book>());

        return author;
    }
}
