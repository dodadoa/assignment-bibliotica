import Utils.IO;
import View.AuthenticationView;
import View.LibraryItemView;

import java.util.Optional;

public class App {
    private final LibraryItemView libraryItemView;
    private final AuthenticationView authenticationView;
    private IO io;
    private boolean isRunning;

    public App(IO io, LibraryItemView libraryItemView, AuthenticationView authenticationView) {
        this.io = io;
        this.isRunning = true;
        this.libraryItemView = libraryItemView;
        this.authenticationView = authenticationView;
    }

    public void greeting() {
        io.display("Hello");
    }

    public void quit() {
        this.isRunning = false;
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
        greeting();
        while (this.isRunning) {
            menu();
        }
    }

}
