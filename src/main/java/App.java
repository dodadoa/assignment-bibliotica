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
import java.util.Optional;

public class App {
    private IO io;
    private boolean isRunning;
    private LibraryItemView libraryItemView;
    private AuthenticationView authenticationView;

    public App() {
        this.io = new IO();
        this.isRunning = true;
    }

    public void greeting() {
        io.display("Hello");
    }

    public void quit() {
        this.isRunning = false;
    }

    public void init() {
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

        LibraryItemController bookController = new LibraryItemController(initBooksList);
        LibraryItemController movieController = new LibraryItemController(initMoviesList);
        AuthenticationController authenticationController = new AuthenticationController(initUsersList);

        libraryItemView = new LibraryItemView(io, bookController, movieController);
        authenticationView = new AuthenticationView(io, authenticationController);
    }

    private void authorizedMenu() {
        io.display("List -> type list and enter");
        io.display("Quit app -> type quit and enter");
        io.display("Checkout Item -> type checkout and enter");
        io.display("Return Item -> type return and enter");
        io.display("Get your information -> type info and enter");
        io.display("Logout -> type logout and enter");

        String input = io.input();
        Optional<MenuOption> option = MenuOption.getEnumByString(input);

        if (option.isPresent()) {
            switch (option.get()) {
                case LIST:
                    this.libraryItemView.listBranch();
                    break;
                case CHECKOUT:
                    this.libraryItemView.checkoutBranch();
                    break;
                case RETURN:
                    this.libraryItemView.checkinBranch();
                    break;
                case USER_INFORMATION:
                    this.authenticationView.showInformation();
                    break;
                case LOGOUT:
                    this.authenticationView.logout();
                    break;
                case QUIT:
                    this.quit();
                    break;
                default:
                    io.display("Select a valid option!");
                    break;
            }
        } else {
            io.display("Select a valid option!");
        }
    }

    private void unauthorizedMenu() {
        io.display("List -> type list and enter");
        io.display("Quit app -> type quit and enter");
        io.display("Login -> type login and enter");

        String input = io.input();
        Optional<MenuOption> option = MenuOption.getEnumByString(input);

        if (option.isPresent()) {
            switch (option.get()) {
                case LIST:
                    this.libraryItemView.listBranch();
                    break;
                case LOGIN:
                    this.authenticationView.login();
                    break;
                case QUIT:
                    this.quit();
                    break;
                default:
                    io.display("Select a valid option!");
                    break;
            }
        } else {
            io.display("Select a valid option!");
        }
    }

    public void menu() {
        io.display("Menu:");
        if (this.authenticationView.isAuth()) {
            this.authorizedMenu();
        } else {
            this.unauthorizedMenu();
        }
    }

    public void run() {
        init();
        greeting();
        while (this.isRunning) {
            menu();
        }
    }

}
