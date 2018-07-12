import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LibraryTest {
    private Library library;
    private Book book;
    private IO mockIO;
    private List<Book> initListsOfBooks;

    @Before
    public void beforeEach(){
        book = new Book("Book1", "K.", 1994);
        initListsOfBooks = new ArrayList<>(Arrays.asList(book));
        mockIO = mock(IO.class);
        library = new Library(initListsOfBooks, mockIO);
    }

    @Test
    public void shouldListAlBookInTheLibraryWithInformationOfBook(){
        library.list();
        verify(mockIO).displayColumn();
        verify(mockIO).display("Book1 | K. | 1994");
    }
}
