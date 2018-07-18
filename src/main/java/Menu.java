import Utils.IO;
import View.AuthenticationView;
import View.LibraryItemView;

import java.util.Optional;
import java.util.function.Consumer;

public class Menu {

    private App app;
    private IO io;
    private LibraryItemView libraryItemView;
    private AuthenticationView authenticationView;
    private String currentUserLibraryNumber;

    public Menu(App app, IO io, LibraryItemView libraryItemView, AuthenticationView authenticationView) {
        this.app = app;
        this.io = io;
        this.libraryItemView = libraryItemView;
        this.authenticationView = authenticationView;
    }

    public void authorizedMenu() {
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
                    this.libraryItemView.checkoutBranch(this.currentUserLibraryNumber);
                    break;
                case RETURN:
                    this.libraryItemView.checkinBranch(this.currentUserLibraryNumber);
                    break;
                case USER_INFORMATION:
                    this.authenticationView.showInformation();
                    break;
                case LOGOUT:
                    this.authenticationView.logout();
                    break;
                case QUIT:
                    app.quit();
                    break;
                default:
                    io.display("Select a valid option!");
                    break;
            }
        } else {
            io.display("Select a valid option!");
        }
    }

    public void unauthorizedMenu() {
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
                    Consumer<String> afterLoginSuccess = (currentUserLibraryNumber) -> this.currentUserLibraryNumber = currentUserLibraryNumber;
                    this.authenticationView.login(afterLoginSuccess);
                    break;
                case QUIT:
                    app.quit();
                    break;
                default:
                    io.display("Select a valid option!");
                    break;
            }
        } else {
            io.display("Select a valid option!");
        }
    }

    public void start() {
        io.display("Menu:");
        if (this.authenticationView.isAuth()) {
            this.authorizedMenu();
        } else {
            this.unauthorizedMenu();
        }
    }
}
