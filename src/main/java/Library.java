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
            if(book.isBookExist(bookNameInput)){
                book.setAvailability(false);
                return true;
            }
        }
        return false;
    }
}
