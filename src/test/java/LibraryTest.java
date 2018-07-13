import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class LibraryTest {
    private Library library;
    private Book book1;
    private Book book2;
    private List<Book> initListsOfBooks;

    @Before
    public void beforeEach() {
        book1 = new Book("Book1", "K.", 1994);
        book2 = new Book("Book2", "K.", 1990);
        initListsOfBooks = new ArrayList<>(Arrays.asList(book1, book2));
        library = new Library(initListsOfBooks);
    }

    @Test
    public void shouldGetListOfStringOfBookWhenListingBooksFromLibrary() {
        List<String> booksInformation = new ArrayList<>(Arrays.asList("Book1 | K. | 1994", "Book2 | K. | 1990"));
        assertEquals(booksInformation, library.list());
    }

    @Test
    public void shouldGetListOfStringOfBookWhichIsAvailableWhenListingBooksFromLibrary() {
        List<String> booksInformation = new ArrayList<>(Arrays.asList("Book1 | K. | 1994", "Book2 | K. | 1990"));
        Book notAvailableBook = new Book("book3", "K.", 1000);
        notAvailableBook.setAvailability(false);

        initListsOfBooks.add(notAvailableBook);
        assertEquals(booksInformation, library.list());
    }

    @Test
    public void shouldBeTrueWhenCheckoutBookThatExistAndAvailable() {
        boolean isCheckoutSuccessful = library.checkout("Book1");
        assertTrue(isCheckoutSuccessful);
    }

}
