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
    private Library library;
    private Book book1;
    private Book book2;
    private List<Book> initListsOfBooks;

    @Before
    public void beforeEach() {
        mockIO = mock(IO.class);
        book1 = new Book("Book1", "K.", 1994);
        book2 = new Book("Book2", "K.", 1990);
        initListsOfBooks = new ArrayList<>(Arrays.asList(book1, book2));
        library = new Library(initListsOfBooks);
        app = new App(mockIO, library);
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
        app.printList();
        verify(mockIO).display("name, author, year published");
        verify(mockIO).display("Book1 | K. | 1994");
        verify(mockIO).display("Book2 | K. | 1990");
    }

    @Test
    public void shouldShowMenuAndOptionListBooks() {
        when(mockIO.input()).thenReturn("");
        app.menu();
        verify(mockIO).display("Menu:");
        verify(mockIO).display("List Books -> type list and enter");
        verify(mockIO).display("Quit app -> type quit and enter");
        verify(mockIO).display("Checkout book -> type checkout and enter");
        verify(mockIO).display("Return book -> type return and enter");
    }

    @Test
    public void shouldPrintListOfBookWhenUserInput1() {
        App spyApp = spy(app);
        when(mockIO.input()).thenReturn("list");
        spyApp.menu();
        verify(spyApp).printList();
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
        when(mockIO.input()).thenReturn("Book1");
        app.checkoutMenu();
        verify(mockIO).display("Thank you! Enjoy the book");
    }

    @Test
    public void shouldDisplayFailMessageWhenCheckoutTheBookThatNotExist() {
        when(mockIO.input()).thenReturn("Book10");
        app.checkoutMenu();
        verify(mockIO).display("That book is not available.");
    }

    @Test
    public void shouldDisplayFailMessageWhenCheckoutTheBookThatNotAvailable() {
        when(mockIO.input()).thenReturn("Book1");
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
        when(mockIO.input()).thenReturn("Book1");
        app.checkoutMenu();
        app.returnMenu();
        verify(mockIO).display("Thank you for returning the book.");
    }

    @Test
    public void shouldDisplayFailMessageWhenReturnTheBookThatNotBelongToLibrary() {
        when(mockIO.input()).thenReturn("noNameBook");
        app.returnMenu();
        verify(mockIO).display("That is not a valid book to return.");
    }

    @Test
    public void shouldDisplayFailMessageWhenReturnTheBookThatAlreadyAvailableInLibrary() {
        when(mockIO.input()).thenReturn("Book1");
        app.returnMenu();
        verify(mockIO).display("That is not a valid book to return.");
    }
}
