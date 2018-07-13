import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BookTest {

    private Book book;

    @Before
    public void beforeEach(){
        book = new Book("Book1", "K.", 1994);
    }

    @Test
    public void shouldNotGetBookInformationThatHaveAlreadyCheckout(){
        book.setAvailability(false);
        Optional<String> optionalBook = book.getInformation();
        assertFalse(optionalBook.isPresent());
    }

    @Test
    public void shouldReturnTrueWhenCheckTheExistBookAndAvailable(){
        assertTrue(book.isBookExist("Book1"));
    }

    @Test
    public void shouldReturnFalseWhenCheckTheBookIsNotAvailable(){
        book.setAvailability(false);
        assertFalse(book.isBookExist("Book1"));
    }

    @Test
    public void shouldReturnFalseWhenCheckTheBookFromNameThatIsNotEqual(){
        assertFalse(book.isBookExist("noNameBook"));
    }
}
