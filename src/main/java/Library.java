import java.util.List;

public class Library {

    private List<Book> listOfBooks;

    public Library(List<Book> initListsOfBooks) {
        this.listOfBooks = initListsOfBooks;
    }

    public List<Book> list() {
        return listOfBooks;
    }
}
