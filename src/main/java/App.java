import java.util.Optional;

public class App {
    private IO io;
    private Library library;
    private boolean isRunning;

    public App(IO io, Library library) {
        this.io = io;
        this.library = library;
        this.isRunning = true;
    }

    public void greeting() {
        io.display("Hello");
    }

    public void printList() {
        io.display("name, author, year published");
        library.list().forEach(book -> io.display(book));
    }

    public void quit() {
        this.isRunning = false;
    }

    public void menu() {
        io.display("Menu:");
        io.display("List Books -> type list and enter");
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
                    this.printList();
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
        io.display("please type the name of the book you want to checkout:");
        String bookNameInput = io.input();
        boolean isCheckoutSuccessful = library.checkout(bookNameInput);
        if (isCheckoutSuccessful) {
            io.display("Thank you! Enjoy the book");
        } else {
            io.display("That book is not available.");
        }
    }

    public void returnMenu() {
        io.display("please type the name of the book you want to return:");
        String bookNameInput = io.input();
        boolean isReturnSuccessful = library.checkin(bookNameInput);
        if (isReturnSuccessful) {
            io.display("Thank you for returning the book.");
        } else {
            io.display("That is not a valid book to return.");
        }
    }

    public void run() {
        greeting();
        while (this.isRunning) {
            menu();
        }
    }

}
