import java.util.List;

public class Library {

    private List<Book> listOfBooks;
    private IO io;

    public Library(List<Book> initListsOfBooks, IO io) {
        this.listOfBooks = initListsOfBooks;
        this.io = io;
    }

    public void list() {
        io.displayColumn();
        this.listOfBooks.forEach(book -> this.io.display(book.getInformation()));
    }
}
