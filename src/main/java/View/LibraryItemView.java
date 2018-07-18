package View;

import Controller.AuthenticationController;
import Controller.LibraryItemController;
import Utils.IO;
import Utils.OperationObserver;

import java.util.Optional;

public class LibraryItemView {

    private final IO io;
    private final LibraryItemController bookController;
    private final LibraryItemController movieController;
    private final AuthenticationController authenticationController;
    private OperationObserver operationObserver = new OperationObserver();

    public LibraryItemView(IO io, LibraryItemController bookController, LibraryItemController movieController, AuthenticationController authenticationController) {
        this.io = io;
        this.bookController = bookController;
        this.movieController = movieController;
        this.bookController.addOperationObserver(operationObserver);
        this.movieController.addOperationObserver(operationObserver);
        this.authenticationController = authenticationController;
    }

    public void listBranch() {
        io.display("List menu:");
        io.display("List Book -> type book and enter");
        io.display("List Movie -> type movie and enter");

        String input = io.input();
        Optional<LibraryItemBranchOption> option = LibraryItemBranchOption.getEnumByString(input);

        if (option.isPresent()) {
            switch (option.get()) {
                case BOOK:
                    io.display("name, author, year published");
                    bookController.list().forEach(io::display);
                    break;
                case MOVIE:
                    io.display("name, year, director, movie rating");
                    movieController.list().forEach(io::display);
                    break;
            }
        } else {
            io.display("Select a valid option!");
        }
    }

    public void checkoutBranch() {
        io.display("Checkout menu:");
        io.display("Checkout Book -> type book and enter");
        io.display("Checkout Movie -> type movie and enter");

        String input = io.input();
        Optional<LibraryItemBranchOption> option = LibraryItemBranchOption.getEnumByString(input);

        if (option.isPresent()) {

            Optional<String> currentUserLibraryNumber = authenticationController.getCurrentUserLibraryNumber();

            switch (option.get()) {
                case BOOK:
                    io.display("please type the name of the book you want to checkout:");
                    String bookNameInput = io.input();

                    this.operationObserver.setOperationStatus(false);
                    currentUserLibraryNumber.ifPresent(optional -> bookController.checkout(bookNameInput, optional));

                    io.display(this.operationObserver.isSuccess() ? "Thank you! Enjoy the book" : "That book is not available.");
                    break;
                case MOVIE:
                    io.display("please type the name of the movie you want to checkout:");
                    String movieNameInput = io.input();

                    this.operationObserver.setOperationStatus(false);
                    currentUserLibraryNumber.ifPresent(optional -> movieController.checkout(movieNameInput, optional));

                    io.display(this.operationObserver.isSuccess() ? "Thank you! Enjoy the movie" : "That movie is not available.");
                    break;
            }
        } else {
            io.display("Select a valid option!");
        }
    }

    public void checkinBranch() {
        io.display("Return menu:");
        io.display("Return Book -> type book and enter");
        io.display("Return Movie -> type movie and enter");

        String input = io.input();
        Optional<LibraryItemBranchOption> option = LibraryItemBranchOption.getEnumByString(input);

        if (option.isPresent()) {

            Optional<String> optionalCurrentUserLibraryNumber = authenticationController.getCurrentUserLibraryNumber();

            switch (option.get()) {
                case BOOK:
                    io.display("please type the name of the book you want to return:");
                    String bookNameInput = io.input();

                    this.operationObserver.setOperationStatus(false);
                    optionalCurrentUserLibraryNumber.ifPresent(currentUserLibraryNumber -> bookController.checkin(bookNameInput, currentUserLibraryNumber));

                    io.display(this.operationObserver.isSuccess() ? "Thank you for returning the book." : "That is not a valid book to return.");
                    break;
                case MOVIE:
                    io.display("please type the name of the movie you want to return:");
                    String movieNameInput = io.input();

                    this.operationObserver.setOperationStatus(false);
                    optionalCurrentUserLibraryNumber.ifPresent(currentUserLibraryNumber -> movieController.checkin(movieNameInput, currentUserLibraryNumber));

                    io.display(this.operationObserver.isSuccess() ? "Thank you for returning the movie." : "That is not a valid movie to return.");
                    break;
            }
        } else {
            io.display("Select a valid option!");
        }
    }
}
