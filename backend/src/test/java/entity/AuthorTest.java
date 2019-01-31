package entity;

import entity.mocker.AuthorMocker;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class AuthorTest extends EntityTestBase {

    private AuthorMocker authorMocker = new AuthorMocker();

    @Test
    public void testCanPersistAuthor() {
        Author author = authorMocker.getOne();
        assertTrue(doPersist(author));

    }

    @Test
    public void testCannotPersistInvalidAuthor() {
        Author author = authorMocker.getOne();
        // no book-list added
        author.setBooks(null);
        assertFalse(doPersist(author));
    }

    @Test
    public void testCanPersistSeveral() {
        List<Author> authors = authorMocker.getMany(20);
        boolean persisted = doPersist(authors.toArray());
        assertTrue(persisted);
    }

}