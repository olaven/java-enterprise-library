package entity.mocker;

import entity.Author;
import entity.Book;

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
        Random random = new Random();
        int authorCount = random.nextInt(3) + 1; // +1 avoids 0 authors
        List<Author> authors = new AuthorMocker().getMany(authorCount);


        Book book = new Book();
        book.setAuthors(authors);
        book.setIsbn(random.nextInt() + 9999999);
        book.setTitle(getRandomFrom(titles));

        return book;
    }
}
