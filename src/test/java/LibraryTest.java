import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;

public class LibraryTest {
    private Library library;
    private Book book;
    private IO io;
    private List<Book> initListsOfBooks;

    @Before
    public void beforeEach(){
        initListsOfBooks = new ArrayList<>(Arrays.asList(book));
        library = new Library(initListsOfBooks);
        io = new IO();
    }

    @Test
    public void shouldListAllBookInTheLibrary(){
        library.list();
        verify(io).display("book information");
    }
}
