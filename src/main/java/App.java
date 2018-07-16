import java.util.Optional;

public class App {
    private final Library bookLibrary;
    private final Library movieLibrary;
    private IO io;

    private boolean isRunning;
    private OperationObserver operationObserver = new OperationObserver();

    public App(IO io, Library bookLibrary, Library movieLibrary) {
        this.io = io;
        this.bookLibrary = bookLibrary;
        this.movieLibrary = movieLibrary;
        this.isRunning = true;
        this.movieLibrary.addOperationObserver(this.operationObserver);
        this.bookLibrary.addOperationObserver(this.operationObserver);
    }

    public void greeting() {
        io.display("Hello");
    }

    public void list(Library library) {
        library.list().forEach(libraryItem -> io.display(libraryItem));
    }

    public void listMenu() {
        io.display("List menu:");
        io.display("List Book -> type book and enter");
        io.display("List Movie -> type movie and enter");
        String input = io.input();
        if ("book".equals(input)){
            io.display("name, author, year published");
            list(bookLibrary);
        } else if ("movie".equals(input)){
            io.display("name, year, director, movie rating");
            list(movieLibrary);
        } else {
            io.display("Select a valid option!");
        }
    }

    public void quit() {
        this.isRunning = false;
    }

    public void menu() {
        io.display("Menu:");
        io.display("List -> type list and enter");
        io.display("Quit app -> type quit and enter");
        io.display("Checkout book -> type checkout and enter");
        io.display("Return book -> type return and enter");
        String input = io.input();
        Optional<MenuOption> option = MenuOption.getEnumByString(input);
        if (option.isPresent()) {
            switch (option.get()) {
                case QUIT:
                    this.quit();
                    break;
                case LIST:
                    this.listMenu();
                    break;
                case CHECKOUT:
                    this.checkoutMenu();
                    break;
                case RETURN:
                    this.returnMenu();
                    break;
            }
        } else {
            io.display("Select a valid option!");
        }
    }

    public void checkoutMenu() {
        io.display("Checkout menu:");
        io.display("Checkout Book -> type book and enter");
        io.display("Checkout Movie -> type book and enter");
        String input = io.input();
        if ("book".equals(input)) {
            io.display("please type the name of the book you want to checkout:");
            String bookNameInput = io.input();
            bookLibrary.checkout(bookNameInput);
            io.display(this.operationObserver.isSuccess() ? "Thank you! Enjoy the book" : "That book is not available.");
        } else if ("movie".equals(input)) {
            io.display("please type the name of the movie you want to checkout:");
            String movieNameInput = io.input();
            movieLibrary.checkout(movieNameInput);
            io.display(this.operationObserver.isSuccess() ? "Thank you! Enjoy the book" : "That book is not available.");
        } else {
            io.display("Select a valid option!");
        }
    }

    public void returnMenu() {
        io.display("Return menu:");
        io.display("Return Book -> type book and enter");
        io.display("Return Movie -> type book and enter");
        String input = io.input();
        if ("book".equals(input)) {
            io.display("please type the name of the book you want to return:");
            String bookNameInput = io.input();
            bookLibrary.checkin(bookNameInput);
            io.display(this.operationObserver.isSuccess() ? "Thank you for returning the book." : "That is not a valid book to return.");
        } else if ("movie".equals(input)) {
            io.display("please type the name of the movie you want to checkout:");
            String movieNameInput = io.input();
            movieLibrary.checkin(movieNameInput);
            io.display(this.operationObserver.isSuccess() ? "Thank you for returning the movie." : "That is not a valid movie to return.");
        } else {
            io.display("Select a valid option!");
        }
    }

    public void run() {
        greeting();
        while (this.isRunning) {
            menu();
        }
    }

}
