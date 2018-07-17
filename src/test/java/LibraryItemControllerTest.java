import Controller.LibraryItemController;
import Model.LibraryItem.Book;
import Model.LibraryItem.LibraryItem;
import Utils.OperationObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LibraryItemControllerTest {
    private LibraryItemController libraryItemController;
    private Book book1;
    private Book book2;
    private List<LibraryItem> initListsOfBooks;
    private OperationObserver mockOperationObserver;

    @BeforeEach
    public void beforeEach() {
        book1 = new Book("Book1", "K.", 1994);
        book2 = new Book("Book2", "K.", 1990);
        initListsOfBooks = new ArrayList<>(Arrays.asList(book1, book2));
        mockOperationObserver = mock(OperationObserver.class);
        libraryItemController = new LibraryItemController(initListsOfBooks);
        libraryItemController.addOperationObserver(mockOperationObserver);
    }

    @Test
    public void shouldGetListOfStringOfBookWhenListingBooksFromLibrary() {
        List<String> booksInformation = new ArrayList<>(Arrays.asList("Book1 | K. | 1994", "Book2 | K. | 1990"));
        assertEquals(booksInformation, libraryItemController.list());
    }

    @Test
    public void shouldGetListOfStringOfBookWhichIsAvailableWhenListingBooksFromLibrary() {
        List<String> booksInformation = new ArrayList<>(Arrays.asList("Book1 | K. | 1994"));
        book2.setAvailability(false);
        assertEquals(booksInformation, libraryItemController.list());
    }

    @Test
    public void shouldBeTrueWhenCheckoutBookThatExistAndAvailable() {
        libraryItemController.checkout("Book1");
        verify(mockOperationObserver).setOperationStatus(true);
    }

    @Test
    public void shouldBeFalseWhenCheckoutBookThatNotExist() {
        libraryItemController.checkout("Book-not-exists");
        verify(mockOperationObserver).setOperationStatus(false);
    }

    @Test
    public void shouldBeFalseWhenCheckoutBookThatNotAvailable() {
        book1.setAvailability(false);
        libraryItemController.checkout("Book1");
        verify(mockOperationObserver).setOperationStatus(false);
    }

    @Test
    public void shouldBeTrueWhenReturnBookThatBelongsToLibraryAndNotAvailable() {
        book1.setAvailability(false);
        libraryItemController.checkin("Book1");
        verify(mockOperationObserver).setOperationStatus(true);
    }

    @Test
    public void shouldBeFalseWhenReturnBookThatNotBelongsToLibrary() {
        libraryItemController.checkin("Book-not-exists");
        verify(mockOperationObserver).setOperationStatus(false);
    }

    @Test
    public void shouldBeFalseWhenReturnBookThatAlreadyAvailableInLibrary() {
        libraryItemController.checkin("Book1");
        verify(mockOperationObserver).setOperationStatus(false);
    }

}
