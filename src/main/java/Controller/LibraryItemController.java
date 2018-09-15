package Controller;

import Model.LibraryItem.LibraryItem;
import Utils.OperationObserver;

import java.util.*;
import java.util.stream.Collectors;

public class LibraryItemController {

    private List<LibraryItem> availableLibraryItemList;
    private List<LibraryItem> checkoutItemList;
    private OperationObserver operationObserver;
    private Map<String, String> userAndLibraryItemRelationshipMap;

    public LibraryItemController(List<LibraryItem> initItemsList) {
        this.availableLibraryItemList = initItemsList;
        this.checkoutItemList = new ArrayList<>();
        this.userAndLibraryItemRelationshipMap = new HashMap<>();
    }

    public List<String> list() {
        return this.availableLibraryItemList.stream()
                .map(LibraryItem::getInformation)
                .collect(Collectors.toList());
    }

    public void checkout(String itemNameInput, String currentUserLibraryNumber) {
        Optional<LibraryItem> optionalItem = this.availableLibraryItemList.stream()
                .filter(item -> item.matchName(itemNameInput))
                .findFirst();

        if (optionalItem.isPresent()) {
            this.userAndLibraryItemRelationshipMap.put(itemNameInput, currentUserLibraryNumber);

            this.checkoutItemList.add(optionalItem.get());
            this.availableLibraryItemList.remove(optionalItem.get());

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

        Optional<LibraryItem> optionalItem = this.checkoutItemList.stream()
                .filter(item -> item.matchName(itemNameInput))
                .findFirst();

        String userBelongedToItemNameInput = this.userAndLibraryItemRelationshipMap.get(itemNameInput);
        boolean isUserTheSameAsCurrentUser = userBelongedToItemNameInput.equals(currentUserLibraryNumber);

        if (optionalItem.isPresent() && isUserTheSameAsCurrentUser) {
            this.userAndLibraryItemRelationshipMap.remove(itemNameInput);

            this.checkoutItemList.remove(optionalItem.get());
            this.availableLibraryItemList.add(optionalItem.get());

            operationObserver.setOperationStatus(true);
        } else {
            operationObserver.setOperationStatus(false);
        }
    }

    public void addOperationObserver(OperationObserver app) {
        this.operationObserver = app;
    }
}
