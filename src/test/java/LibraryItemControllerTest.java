import Controller.LibraryItemController;
import Model.LibraryItem.Book;
import Model.LibraryItem.LibraryItem;
import Utils.OperationObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Library Item Controller")
public class LibraryItemControllerTest {
    private LibraryItemController libraryItemController;
    private Book book1;
    private Book book2;
    private String user1;
    private String user2;
    private List<LibraryItem> initListsOfBooks;
    private OperationObserver mockOperationObserver;

    @BeforeEach
    public void beforeEach() {
        book1 = new Book("Book1", "K.", 1994);
        book2 = new Book("Book2", "K.", 1990);
        user1 = "111-1111";
        user2 = "111-0000";
        initListsOfBooks = new ArrayList<>(Arrays.asList(book1, book2));
        mockOperationObserver = mock(OperationObserver.class);
        libraryItemController = new LibraryItemController(initListsOfBooks);
        libraryItemController.addOperationObserver(mockOperationObserver);
    }

    @Nested
    @DisplayName("get list of String")
    class ListLibraryItemTest {
        @DisplayName("get list of Available Item")
        @Test
        public void shouldGetListOfStringOfBookWhichIsAvailableWhenListingBooksFromLibrary() {
            libraryItemController.checkout("Book2", user1);
            List<String> booksInformation = new ArrayList<>(Arrays.asList("Book1 | K. | 1994"));
            assertEquals(booksInformation, libraryItemController.list());
        }
    }

    @Nested
    @DisplayName("Checkout")
    class CheckoutTest {

        @DisplayName("checkout exist Item -> set operation true (success)")
        @Test
        public void shouldBeTrueWhenCheckoutBookThatExistAndAvailable() {
            libraryItemController.checkout("Book1", user1);
            verify(mockOperationObserver).setOperationStatus(true);
        }

        @DisplayName("checkout not exist Item -> set operation false (fail)")
        @Test
        public void shouldBeFalseWhenCheckoutBookThatNotExist() {
            libraryItemController.checkout("Book-not-exists", user1);
            verify(mockOperationObserver).setOperationStatus(false);
        }

        @DisplayName("When Item exists but not available -> set operation false")
        @Test
        public void shouldBeFalseWhenCheckoutBookThatNotAvailable() {
            libraryItemController.checkout("Book1", user1);
            reset(mockOperationObserver);
            libraryItemController.checkout("Book1", user1);
            verify(mockOperationObserver).setOperationStatus(false);
        }
    }

    @Nested
    @DisplayName("Checkin")
    class CheckinTest {
        @DisplayName("user return item -> set operation true (success)")
        @Test
        public void theSameUserReturnItem() {
            libraryItemController.checkout("Book1", user1);
            libraryItemController.checkin("Book1", user1);
            verify(mockOperationObserver, times(2)).setOperationStatus(true);
        }

        @DisplayName("user which is not checkout that item return that item -> set operation false (fail)")
        @Test
        public void notTheSameUserReturnItem() {
            libraryItemController.checkout("Book1", user1);
            reset(mockOperationObserver);

            libraryItemController.checkin("Book1", user2);
            verify(mockOperationObserver).setOperationStatus(false);
        }

        @DisplayName("user return the already exist item -> set operation false (fail)")
        @Test
        public void returnItemAlreadyExist() {
            libraryItemController.checkin("Book1", user1);
            verify(mockOperationObserver, times(1)).setOperationStatus(false);
        }

        @DisplayName("user return different item they not checkout for -> set operation false (fail)")
        @Test
        public void returnItemUserNotCheckoutFor() {
            libraryItemController.checkout("Book1", user1);
            libraryItemController.checkout("Book2", user2);
            reset(mockOperationObserver);

            libraryItemController.checkin("Book1", user2);
            verify(mockOperationObserver).setOperationStatus(false);
        }
    }


}
