package Controller;

import Model.User.User;
import Utils.OperationObserver;

import java.util.List;
import java.util.Optional;

public class AuthenticationController {

    private List<User> userList;
    private OperationObserver operationObserver;

    public AuthenticationController(List<User> usersList) {
        this.userList = usersList;
    }

    public void addOperationObserver(OperationObserver app) {
        this.operationObserver = app;
    }

    public void login(String libraryNumber, String password) {
        boolean isMatch = userList.stream()
                .anyMatch(user -> user.matchUser(libraryNumber, password));
        operationObserver.setOperationStatus(isMatch);
    }

    public Optional<String> getUserInformationByLibraryNumber(String libraryNumber) {
        return this.userList.stream()
                .filter(user -> user.matchLibraryNumber(libraryNumber))
                .map(User::getUserInformation)
                .findFirst();
    }
}
