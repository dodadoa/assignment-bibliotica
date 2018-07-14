import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {

    private List<Book> listOfBooks;

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

    public boolean checkout(String bookNameInput) {
        for (Book book : this.listOfBooks) {
            if (book.checkExistenceFromBookName(bookNameInput)) {
                book.setAvailability(false);
                return true;
            }
        }
        return false;
    }

    public boolean checkin(String bookNameInput) {
        for (Book book : this.listOfBooks) {
            if (book.isBookBelongToLibrary(bookNameInput)) {
                book.setAvailability(true);
                return true;
            }
        }
        return false;
    }
}
