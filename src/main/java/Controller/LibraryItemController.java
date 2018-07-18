package Controller;

import Model.LibraryItem.LibraryItem;
import Utils.OperationObserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class LibraryItemController {

    private List<LibraryItem> libraryItemList;
    private OperationObserver operationObserver;
    private Map<String, String> userAndLibraryItemRelationshipMap;

    public LibraryItemController(List<LibraryItem> initItemsList) {
        this.libraryItemList = initItemsList;
        this.userAndLibraryItemRelationshipMap = new HashMap<>();
    }

    public List<String> list() {
        return this.libraryItemList.stream()
                .map(LibraryItem::getInformation)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public void checkout(String itemNameInput, String currentUserLibraryNumber) {
        Optional<LibraryItem> optionalItem = this.libraryItemList.stream()
                .filter(item -> item.matchAvailable(itemNameInput))
                .findFirst();

        if (optionalItem.isPresent()) {
            this.userAndLibraryItemRelationshipMap.put(itemNameInput, currentUserLibraryNumber);

            optionalItem.get().setAvailability(false);
            operationObserver.setOperationStatus(true);
        } else {
            operationObserver.setOperationStatus(false);
        }
    }

    public void checkin(String itemNameInput, String currentUserLibraryNumber) {
        if (!this.userAndLibraryItemRelationshipMap.containsKey(itemNameInput)) {
            operationObserver.setOperationStatus(false);
            return;
        }

        Optional<LibraryItem> optionalItem = this.libraryItemList.stream()
                .filter(item -> item.matchNonAvailable(itemNameInput))
                .findFirst();

        String userBelongedToItemNameInput = this.userAndLibraryItemRelationshipMap.get(itemNameInput);
        boolean isUserTheSameAsCurrentUser = userBelongedToItemNameInput.equals(currentUserLibraryNumber);

        if (optionalItem.isPresent() && isUserTheSameAsCurrentUser) {
            this.userAndLibraryItemRelationshipMap.remove(itemNameInput);
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
