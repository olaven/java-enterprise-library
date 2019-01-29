package entities;

import entities.mocker.AuthorMocker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest extends EntityTestBase {

    private AuthorMocker authorMocker = new AuthorMocker();

    @Test
    void canPersistAuthor() {
        Author author = authorMocker.getOne();
        assertTrue(canPersist(author));

    }

    @Test
    void cannotPersistInvalidAuthor() {
        Author author = authorMocker.getOne();
        // no book-list added
        author.setBooks(null);
        assertFalse(canPersist(author));
    }

    @Test
    void canPersistSeveral() {
        List<Author> authors = authorMocker.getMany(20);
        boolean persisted = canPersist(authors.toArray());
        assertTrue(persisted);
    }

}