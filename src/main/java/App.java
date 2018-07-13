public class App {
    private IO io;
    private Library library;

    public App(IO io, Library library){
        this.io = io;
        this.library = library;
    }

    public void greeting() {
        io.display("Hello");
    }

    public void printList(){
        io.display("name, author, year published");
        library.list().forEach(book -> io.display(book));
    }

    public void run(){
        greeting();
        printList();
    }

    public void menu() {
        io.display("Menu:");
        io.display("List Books");
    }
}
