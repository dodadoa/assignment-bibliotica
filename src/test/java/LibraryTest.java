import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LibraryTest {
    private Library library;
    private Book book1;
    private Book book2;
    private List<Book> initListsOfBooks;
    private OperationObserver mockOperationObserver;

    @Before
    public void beforeEach() {
        book1 = new Book("Book1", "K.", 1994);
        book2 = new Book("Book2", "K.", 1990);
        initListsOfBooks = new ArrayList<>(Arrays.asList(book1, book2));
        mockOperationObserver = mock(OperationObserver.class);
        library = new Library(initListsOfBooks);
        library.addOperationObserver(mockOperationObserver);
    }

    @Test
    public void shouldGetListOfStringOfBookWhenListingBooksFromLibrary() {
        List<String> booksInformation = new ArrayList<>(Arrays.asList("Book1 | K. | 1994", "Book2 | K. | 1990"));
        assertEquals(booksInformation, library.list());
    }

    @Test
    public void shouldGetListOfStringOfBookWhichIsAvailableWhenListingBooksFromLibrary() {
        List<String> booksInformation = new ArrayList<>(Arrays.asList("Book1 | K. | 1994"));
        book2.setAvailability(false);
        assertEquals(booksInformation, library.list());
    }

    @Test
    public void shouldBeTrueWhenCheckoutBookThatExistAndAvailable() {
        library.checkout("Book1");
        verify(mockOperationObserver).setOperationStatus(true);
    }

    @Test
    public void shouldBeFalseWhenCheckoutBookThatNotExist() {
        library.checkout("Book-not-exists");
        verify(mockOperationObserver).setOperationStatus(false);
    }

    @Test
    public void shouldBeFalseWhenCheckoutBookThatNotAvailable() {
        book1.setAvailability(false);
        library.checkout("Book1");
        verify(mockOperationObserver).setOperationStatus(false);
    }

    @Test
    public void shouldBeTrueWhenReturnBookThatBelongsToLibraryAndNotAvailable() {
        book1.setAvailability(false);
        library.checkin("Book1");
        verify(mockOperationObserver).setOperationStatus(true);
    }

    @Test
    public void shouldBeFalseWhenReturnBookThatNotBelongsToLibrary() {
        library.checkin("Book-not-exists");
        verify(mockOperationObserver).setOperationStatus(false);
    }

    @Test
    public void shouldBeFalseWhenReturnBookThatAlreadyAvailableInLibrary() {
        library.checkin("Book1");
        verify(mockOperationObserver).setOperationStatus(false);
    }

}
