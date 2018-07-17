import Controller.AuthenticationController;
import Controller.LibraryItemController;
import Model.LibraryItem.Book;
import Model.LibraryItem.LibraryItem;
import Model.LibraryItem.Movie;
import Model.User.User;
import Utils.IO;
import View.AuthenticationView;
import View.LibraryItemView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("Book1", "K.", 1994);
        Book book2 = new Book("Book2", "K.", 1990);
        List<LibraryItem> initBooksList = new ArrayList<>(Arrays.asList(book1, book2));

        Movie movie1 = new Movie("Movie1", "L.", 2000, 10);
        Movie movie2 = new Movie("Movie2", "G.", 3000, 5);
        List<LibraryItem> initMoviesList = new ArrayList<>(Arrays.asList(movie1, movie2));

        List<User> initUsersList = new ArrayList<>(Arrays.asList(
                new User("111-1111", "dodadoa", "K.", "k@mail.com", "0801112222"),
                new User("111-0000", "kkk", "Y.", "Y@mail.com", "0802221111")
        ));

        IO io = new IO();

        LibraryItemController bookController = new LibraryItemController(initBooksList);
        LibraryItemController movieController = new LibraryItemController(initMoviesList);
        AuthenticationController authenticationController = new AuthenticationController(initUsersList);

        LibraryItemView libraryItemView = new LibraryItemView(io, bookController, movieController);
        AuthenticationView authenticationView = new AuthenticationView(io, authenticationController);

        App app = new App(io, libraryItemView, authenticationView);
        app.run();
    }
}
