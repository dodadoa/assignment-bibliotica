import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BookTest {

    private Book book;

    @Before
    public void beforeEach() {
        book = new Book("Book1", "K.", 1994);
    }

    @Test
    public void shouldNotGetBookInformationThatHaveAlreadyCheckout() {
        book.setAvailability(false);
        Optional<String> optionalBook = book.getInformation();
        assertFalse(optionalBook.isPresent());
    }

    @Test
    public void shouldReturnTrueWhenCheckTheExistBookAndAvailable() {
        assertTrue(book.matchAvailableBook("Book1"));
    }

    @Test
    public void shouldReturnFalseWhenCheckTheBookIsNotAvailable() {
        book.setAvailability(false);
        assertFalse(book.matchAvailableBook("Book1"));
    }

    @Test
    public void shouldReturnFalseWhenCheckTheBookFromNameThatIsNotEqual() {
        assertFalse(book.matchAvailableBook("noNameBook"));
    }

    @Test
    public void shouldReturnTrueWhenReturnTheBookThatBelongToLibraryAndNotAvailable() {
        book.setAvailability(false);
        assertTrue(book.matchNonAvailableBook("Book1"));
    }

    @Test
    public void shouldReturnFalseWhenReturnTheBookThatNotBelongToLibrary() {
        assertFalse(book.matchNonAvailableBook("noNameBook"));
    }

    @Test
    public void shouldReturnFalseWhenReturnTheBookThatAlreadyAvailableInLibrary() {
        assertFalse(book.matchNonAvailableBook("Book1"));
    }
}
