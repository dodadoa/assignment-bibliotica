import java.util.List;
import java.util.stream.Collectors;

public class Library {

    private List<Book> listOfBooks;

    public Library(List<Book> initListsOfBooks) {
        this.listOfBooks = initListsOfBooks;
    }

    public List<String> list() {
        return this.listOfBooks.stream()
                .map(book -> book.getInformation())
                .collect(Collectors.toList());
    }
}
