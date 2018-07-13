import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class AppTest {

    private IO mockIO;
    private App app;
    private Library library;
    private Book book1;
    private Book book2;
    private List<Book> initListsOfBooks;

    @Before
    public void beforeEach(){
        mockIO = mock(IO.class);
        book1 = new Book("Book1", "K.", 1994);
        book2 = new Book("Book2", "K.", 1990);
        initListsOfBooks = new ArrayList<>(Arrays.asList(book1, book2));
        library = new Library(initListsOfBooks);
        app = new App(mockIO, library);
    }

    @Test
    public void shouldWelcomeUserByGreetingHello(){
        app.greeting();
        verify(mockIO).display("Hello");
    }

    @Test
    public void shouldListAlBookInTheLibraryWithInformationOfBook(){
        app.printList();
        verify(mockIO).display("name, author, year published");
        verify(mockIO).display("Book1 | K. | 1994");
        verify(mockIO).display("Book2 | K. | 1990");
    }

    @Test
    public void shouldShowMenuAndOptionListBooks(){
        when(mockIO.input()).thenReturn("");
        app.menu();
        verify(mockIO).display("Menu:");
        verify(mockIO).display("List Books -> type list and enter");
        verify(mockIO).display("Quit app -> type quit and enter");
    }

    @Test
    public void shouldPrintListOfBookWhenUserInput1(){
        App spyApp = spy(app);
        when(mockIO.input()).thenReturn("list");
        spyApp.menu();
        verify(spyApp).printList();
    }

    @Test
    public void shouldShowInvalidOptionMessageWhenInputIsNotlist(){
        when(mockIO.input()).thenReturn("not list, not quit");
        app.menu();
        verify(mockIO).display("Select a valid option!");
    }

    @Test
    public void shouldQuitApplicationWhenUserInputQuit(){
        when(mockIO.input()).thenReturn("quit");
        app.menu();
        verify(mockIO).quit();
    }
}
