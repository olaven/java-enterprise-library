package entities.mocker;

import entities.Author;
import entities.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookMocker extends Mocker<Book> {

    private String[] titles = {
            "The book of thunks", "Kotlin for android programming", "What's with this",
            "Lord of the rings", "The Hobbit", "The Computing Univsere", "How does it know?",
            "Children of Hurin", "ES6 & Beyond", "Guns, Germs and Steel"
    };

    @Override
    public Book getOne() {
        List<Author> authors = new AuthorMocker().getMany(2);
        Random random = new Random();

        Book book = new Book();
        book.setAuthors(authors);
        book.setIsbn(random.nextInt() + 9999999);
        book.setTitle(getRandomFrom(titles));

        return book;
    }
}
