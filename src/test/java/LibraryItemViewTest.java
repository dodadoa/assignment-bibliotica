import Controller.LibraryItemController;
import Model.LibraryItem.Book;
import Model.LibraryItem.LibraryItem;
import Model.LibraryItem.Movie;
import Utils.IO;
import View.LibraryItemView;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@DisplayName("Library Item View")
public class LibraryItemViewTest {

    private LibraryItemView libraryItemView;
    private LibraryItemController bookController;
    private LibraryItemController movieController;
    private String libraryNumber;
    IO mockIO;

    @BeforeEach
    public void beforeEach() {
        mockIO = mock(IO.class);
        Book book1 = new Book("Book1", "K.", 1994);
        Book book2 = new Book("Book2", "K.", 1990);
        List<LibraryItem> initBooksList = new ArrayList<>(Arrays.asList(book1, book2));

        Movie movie1 = new Movie("Movie1", "L.", 2000, 10);
        Movie movie2 = new Movie("Movie2", "G.", 3000, 5);
        List<LibraryItem> initMoviesList = new ArrayList<>(Arrays.asList(movie1, movie2));

        bookController = new LibraryItemController(initBooksList);
        movieController = new LibraryItemController(initMoviesList);

        libraryNumber = "111-1111";

        libraryItemView = new LibraryItemView(mockIO, bookController, movieController);
    }

    @AfterEach
    public void afterEach() {
        reset(mockIO);
    }

    @Nested
    @DisplayName("List Branch")
    class ListBranchTest {

        @DisplayName("should list all books when input book")
        @Test
        public void listAllBooksWhenInputBook() {
            when(mockIO.input()).thenReturn("book");
            libraryItemView.listBranch();
            verify(mockIO).display("name, author, year published");
            verify(mockIO).display("Book1 | K. | 1994");
            verify(mockIO).display("Book2 | K. | 1990");
        }

        @DisplayName("should list all movies when input movie")
        @Test
        public void listAllMoviesWhenInputBook() {
            when(mockIO.input()).thenReturn("movie");
            libraryItemView.listBranch();
            verify(mockIO).display("name, year, director, movie rating");
            verify(mockIO).display("Movie1 | 2000 | L. | 10");
            verify(mockIO).display("Movie2 | 3000 | G. | 5");
        }
    }

    @Nested
    @DisplayName("Checkout Branch")
    class CheckoutBranchTest {

        @DisplayName("should display successful message when checkout available Book")
        @Test
        public void checkoutSuccessfullyWhenCheckoutAvailableBook() {
            when(mockIO.input()).thenReturn("book").thenReturn("Book1");
            libraryItemView.checkoutBranch(libraryNumber);
            verify(mockIO).display("Thank you! Enjoy the book");
        }

        @DisplayName("should Display Successful message when checkout available movie")
        @Test
        public void checkoutSuccessfullyWhenCheckoutAvaialbleMovie() {
            when(mockIO.input()).thenReturn("movie").thenReturn("Movie1");
            libraryItemView.checkoutBranch(libraryNumber);
            verify(mockIO).display("Thank you! Enjoy the movie");
        }

        @DisplayName("should display fail message when checkout invalid book")
        @Test
        public void checkoutFailWhenCheckoutInvalidBook() {
            when(mockIO.input()).thenReturn("book").thenReturn("invalid Book");
            libraryItemView.checkoutBranch(libraryNumber);
            verify(mockIO).display("That book is not available.");
        }

        @DisplayName("should display fail message when checkout non-available book")
        @Test
        public void checkoutFailWhenCheckoutNotAvailableBook() {
            when(mockIO.input()).thenReturn("book").thenReturn("Book1");
            libraryItemView.checkoutBranch(libraryNumber);
            verify(mockIO, times(1)).display("Thank you! Enjoy the book");

            reset(mockIO);

            when(mockIO.input()).thenReturn("book").thenReturn("Book1");
            libraryItemView.checkoutBranch(libraryNumber);
            verify(mockIO, times(1)).display("That book is not available.");
        }
    }

    @Nested
    @DisplayName("Checkin Branch")
    class CheckinBranchTest {
        @DisplayName("should display successful message when return Book from the same user")
        @Test
        public void returnSuccessfullyWhenCheckinBookFromTheSameUser() {
            when(mockIO.input()).thenReturn("book").thenReturn("Book1");
            libraryItemView.checkoutBranch(libraryNumber);

            reset(mockIO);

            when(mockIO.input()).thenReturn("book").thenReturn("Book1");
            libraryItemView.checkinBranch(libraryNumber);
            verify(mockIO).display("Thank you for returning the book.");
        }

        @DisplayName("should display fail message when return Book from the different user")
        @Test
        public void returnSuccessfullyWhenCheckinBookFromTheDifferentUser() {
            when(mockIO.input()).thenReturn("book").thenReturn("Book1");
            libraryItemView.checkoutBranch("111-1111");

            reset(mockIO);

            when(mockIO.input()).thenReturn("book").thenReturn("Book1");
            libraryItemView.checkinBranch("000-0000");
            verify(mockIO).display("That is not a valid book to return.");
        }
    }
}
