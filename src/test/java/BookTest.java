import Model.LibraryItem.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BookTest {

    private Book book;

    @BeforeEach
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
        assertTrue(book.matchAvailable("Book1"));
    }

    @Test
    public void shouldReturnFalseWhenCheckTheBookIsNotAvailable() {
        book.setAvailability(false);
        assertFalse(book.matchAvailable("Book1"));
    }

    @Test
    public void shouldReturnFalseWhenCheckTheBookFromNameThatIsNotEqual() {
        assertFalse(book.matchAvailable("noNameBook"));
    }

    @Test
    public void shouldReturnTrueWhenReturnTheBookThatBelongToLibraryAndNotAvailable() {
        book.setAvailability(false);
        assertTrue(book.matchNonAvailable("Book1"));
    }

    @Test
    public void shouldReturnFalseWhenReturnTheBookThatNotBelongToLibrary() {
        assertFalse(book.matchNonAvailable("noNameBook"));
    }

    @Test
    public void shouldReturnFalseWhenReturnTheBookThatAlreadyAvailableInLibrary() {
        assertFalse(book.matchNonAvailable("Book1"));
    }
}
