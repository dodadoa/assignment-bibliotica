package View;

import Controller.AuthenticationController;
import Utils.IO;
import Utils.OperationObserver;

import java.util.Optional;
import java.util.function.Consumer;

public class AuthenticationView {
    private final IO io;
    private final AuthenticationController authenticationController;
    private OperationObserver operationObserver = new OperationObserver();
    private Optional<String> currentUserLibraryNumber;

    public AuthenticationView(IO io, AuthenticationController authenticationController) {
        this.io = io;
        this.authenticationController = authenticationController;
        this.authenticationController.addOperationObserver(operationObserver);
        this.currentUserLibraryNumber = Optional.empty();
    }

    public boolean isAuth() {
        return this.currentUserLibraryNumber.isPresent();
    }

    public void login(Consumer<String> afterLoginSuccess) {
        io.display("Please login");

        io.display("library number:");
        String libraryNumber = io.input();

        io.display("password:");
        String password = io.input();

        authenticationController.login(libraryNumber, password);

        if (operationObserver.isSuccess()) {
            this.currentUserLibraryNumber = Optional.of(libraryNumber);
            afterLoginSuccess.accept(this.currentUserLibraryNumber.get());
            io.display("login success!");
            return;
        }

        io.display("login fail!");
    }

    public void logout() {
        this.currentUserLibraryNumber = Optional.empty();
        io.display("logout successfully!");
    }

    public void showInformation() {
        if (this.currentUserLibraryNumber.isPresent()) {
            Optional<String> optionalUserInformation = authenticationController
                    .getUserInformationByLibraryNumber(this.currentUserLibraryNumber.get());
            optionalUserInformation.ifPresent(io::display);
        }
    }
}
