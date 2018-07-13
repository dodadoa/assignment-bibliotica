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

    public void run() {
        greeting();
        while (this.isRunning) {
            menu();
        }
    }

    public void menu() {
        io.display("Menu:");
        io.display("List Books -> type list and enter");
        io.display("Quit app -> type quit and enter");
        String input = io.input();
        if (input.equals("quit")) {
            this.quit();
        } else if (input.equals("list")) {
            this.printList();
        } else {
            io.display("Select a valid option!");
        }
    }
}
