import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

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

}
