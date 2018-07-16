import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class AppTest {

    private IO mockIO;
    private App app;
    private Library bookLibrary;
    private Library movieLibrary;
    private Book book1;
    private Book book2;
    private List<LibraryItem> initBooksList;
    private Movie movie1;
    private Movie movie2;
    private List<LibraryItem> initMoviesList;

    @Before
    public void beforeEach() {
        mockIO = mock(IO.class);

        book1 = new Book("Book1", "K.", 1994);
        book2 = new Book("Book2", "K.", 1990);
        movie1 = new Movie("Movie1", "L.", 2000, 10);
        movie2 = new Movie("Movie2", "G.", 3000, 5);

        initBooksList = new ArrayList<>(Arrays.asList(book1, book2));
        initMoviesList = new ArrayList<>(Arrays.asList(movie1, movie2));

        bookLibrary = new Library(initBooksList);
        movieLibrary = new Library(initMoviesList);

        app = new App(mockIO, bookLibrary, movieLibrary);
    }

    @After
    public void afterEach() {
        reset(mockIO);
    }

    @Test
    public void shouldWelcomeUserByGreetingHello() {
        app.greeting();
        verify(mockIO).display("Hello");
    }

    @Test
    public void shouldListAlBookInTheLibraryWithInformationOfBook() {
        app.list(bookLibrary);
        verify(mockIO).display("Book1 | K. | 1994");
        verify(mockIO).display("Book2 | K. | 1990");
    }

    @Test
    public void shouldPrintListOfBookWhenUserInputBook() {
        App spyApp = spy(app);
        when(mockIO.input()).thenReturn("book");
        spyApp.listMenu();
        verify(spyApp).list(bookLibrary);
    }

    @Test
    public void shouldShowInvalidOptionMessageWhenInputIsNotlist() {
        when(mockIO.input()).thenReturn("not list, not quit");
        app.menu();
        verify(mockIO).display("Select a valid option!");
    }

    @Test
    public void shouldQuitApplicationWhenUserInputFromMenuIsQuit() {
        App spyApp = spy(app);
        when(mockIO.input()).thenReturn("quit");
        spyApp.menu();
        verify(spyApp).quit();
    }

    @Test
    public void shouldGoToCheckoutMenuWhenInputFromMenuIsCheckout() {
        App spyApp = spy(app);
        when(mockIO.input()).thenReturn("checkout");
        spyApp.menu();
        verify(spyApp).checkoutMenu();
    }

    @Test
    public void shouldDisplaySuccessfulMessageWhenCheckoutTheBookThatExistAndAvailable() {
        when(mockIO.input()).thenReturn("book").thenReturn("Book1");
        app.checkoutMenu();
        verify(mockIO).display("Thank you! Enjoy the book");
    }

    @Test
    public void shouldDisplayFailMessageWhenCheckoutTheBookThatNotExist() {
        when(mockIO.input()).thenReturn("book").thenReturn("Book10");
        app.checkoutMenu();
        verify(mockIO).display("That book is not available.");
    }

    @Test
    public void shouldDisplayFailMessageWhenCheckoutTheBookThatNotAvailable() {
        when(mockIO.input()).thenReturn("book").thenReturn("Book1").thenReturn("book").thenReturn("Book1");
        app.checkoutMenu();
        app.checkoutMenu();
        verify(mockIO).display("That book is not available.");
    }

    @Test
    public void shouldGoToReturnMenuWhenInputFromMainMenuIsReturn() {
        App spyApp = spy(app);
        when(mockIO.input()).thenReturn("return");
        spyApp.menu();
        verify(spyApp).returnMenu();
    }

    @Test
    public void shouldDisplaySuccessfulMessageWhenReturnTheBookThatBelongToLibraryAndNotAvailable() {
        when(mockIO.input()).thenReturn("book").thenReturn("Book1").thenReturn("book").thenReturn("Book1");
        app.checkoutMenu();
        app.returnMenu();
        verify(mockIO).display("Thank you for returning the book.");
    }

    @Test
    public void shouldDisplayFailMessageWhenReturnTheBookThatNotBelongToLibrary() {
        when(mockIO.input()).thenReturn("book").thenReturn("noNameBook");
        app.returnMenu();
        verify(mockIO).display("That is not a valid book to return.");
    }

    @Test
    public void shouldDisplayFailMessageWhenReturnTheBookThatAlreadyAvailableInLibrary() {
        when(mockIO.input()).thenReturn("book").thenReturn("Book1");
        app.returnMenu();
        verify(mockIO).display("That is not a valid book to return.");
    }
}
