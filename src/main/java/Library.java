import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {

    private List<LibraryItem> libraryItemList;
    private OperationObserver operationObserver;

    public Library(List<LibraryItem> initItemsList) {
        this.libraryItemList = initItemsList;
    }

    public List<String> list() {
        return this.libraryItemList.stream()
                .map(LibraryItem::getInformation)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public void checkout(String itemNameInput) {
        for (LibraryItem item : this.libraryItemList) {
            if (item.matchAvailable(itemNameInput)) {
                item.setAvailability(false);
                operationObserver.setOperationStatus(true);
                return;
            }
        }
        operationObserver.setOperationStatus(false);
    }

    public void checkin(String bookNameInput) {
        for (LibraryItem item : this.libraryItemList) {
            if (item.matchNonAvailable(bookNameInput)) {
                item.setAvailability(true);
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
