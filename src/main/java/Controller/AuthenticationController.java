package Controller;

import Model.User.User;
import Utils.OperationObserver;

import java.util.List;
import java.util.Optional;

public class AuthenticationController {

    private List<User> userList;
    private OperationObserver operationObserver;
    private Optional<String> currentUserLibraryNumber;

    public AuthenticationController(List<User> usersList) {
        this.userList = usersList;
        this.currentUserLibraryNumber = Optional.empty();
    }

    public void addOperationObserver(OperationObserver app) {
        this.operationObserver = app;
    }

    public void login(String libraryNumber, String password) {
        boolean isMatch = userList.stream()
                .anyMatch(user -> user.matchUser(libraryNumber, password));
        operationObserver.setOperationStatus(isMatch);
        if (isMatch) {
            this.currentUserLibraryNumber = Optional.of(libraryNumber);
        }
    }

    public Optional<String> getCurrentUserLibraryNumber() {
        return this.currentUserLibraryNumber;
    }

    public void logout() {
        if (this.currentUserLibraryNumber.isPresent()) {
            operationObserver.setOperationStatus(true);
            this.currentUserLibraryNumber = Optional.empty();
            return;
        }
        operationObserver.setOperationStatus(false);
    }

    public boolean isAuth() {
        return this.currentUserLibraryNumber.isPresent();
    }

    public Optional<String> getUserInformation() {
        if (this.currentUserLibraryNumber.isPresent()) {
            return this.userList.stream()
                    .filter(user -> user.matchLibraryNumber(this.currentUserLibraryNumber.get()))
                    .map(User::getUserInformation)
                    .findFirst();
        }
        return Optional.empty();
    }
}
