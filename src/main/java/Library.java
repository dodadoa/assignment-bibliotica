import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {

    private List<Book> listOfBooks;
    private OperationObserver operationObserver;

    public Library(List<Book> initListsOfBooks) {
        this.listOfBooks = initListsOfBooks;
    }

    public List<String> list() {
        return this.listOfBooks.stream()
                .map(Book::getInformation)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public void checkout(String bookNameInput) {
        for (Book book : this.listOfBooks) {
            if (book.matchAvailableBook(bookNameInput)) {
                book.setAvailability(false);
                operationObserver.setOperationStatus(true);
                return;
            }
        }
        operationObserver.setOperationStatus(false);
    }

    public void checkin(String bookNameInput) {
        for (Book book : this.listOfBooks) {
            if (book.matchNonAvailableBook(bookNameInput)) {
                book.setAvailability(true);
                operationObserver.setOperationStatus(true);
                return;
            }
        }
        operationObserver.setOperationStatus(false);
    }

    public void addOperationObserver(OperationObserver app) {
        this.operationObserver = app;
    }
}
