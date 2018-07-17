package Controller;

import Model.LibraryItem.LibraryItem;
import Utils.OperationObserver;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LibraryItemController {

    private List<LibraryItem> libraryItemList;
    private OperationObserver operationObserver;

    public LibraryItemController(List<LibraryItem> initItemsList) {
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
        Optional<LibraryItem> optionalItem = this.libraryItemList.stream()
                .filter(item -> item.matchAvailable(itemNameInput))
                .findFirst();
        if (optionalItem.isPresent()) {
            optionalItem.get().setAvailability(false);
            operationObserver.setOperationStatus(true);
        } else {
            operationObserver.setOperationStatus(false);
        }
    }

    public void checkin(String itemNameInput) {
        Optional<LibraryItem> optionalItem = this.libraryItemList.stream()
                .filter(item -> item.matchNonAvailable(itemNameInput))
                .findFirst();
        if (optionalItem.isPresent()) {
            optionalItem.get().setAvailability(true);
            operationObserver.setOperationStatus(true);
        } else {
            operationObserver.setOperationStatus(false);
        }
    }

    public void addOperationObserver(OperationObserver app) {
        this.operationObserver = app;
    }
}
