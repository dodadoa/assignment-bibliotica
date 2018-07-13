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
        menu();
    }

    public void menu() {
        io.display("Menu:");
        io.display("List Books -> press 1 and enter");
        io.display("Quit app -> press quit and enter");
        String input = io.input();
        if(input.equals("quit")){
            io.quit();
        }

        if(input.equals("1")){
            this.printList();
        } else {
            io.display("Select a valid option!");
        }
    }
}
