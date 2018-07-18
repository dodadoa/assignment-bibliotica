package View;

import Controller.AuthenticationController;
import Utils.IO;
import Utils.OperationObserver;

import java.util.Optional;

public class AuthenticationView {
    private final IO io;
    private final AuthenticationController authenticationController;
    private OperationObserver operationObserver = new OperationObserver();

    public AuthenticationView(IO io, AuthenticationController authenticationController) {
        this.io = io;
        this.authenticationController = authenticationController;
        this.authenticationController.addOperationObserver(operationObserver);
    }

    public void login() {
        io.display("Please login");

        io.display("library number:");
        String libraryNumber = io.input();

        io.display("password:");
        String password = io.input();

        authenticationController.login(libraryNumber, password);

        if (operationObserver.isSuccess()) {
            io.display("login successfully!");
            return;
        }

        io.display("login fail!");
    }

    public void logout() {
        authenticationController.logout();

        if (operationObserver.isSuccess()) {
            io.display("logout successfully!");
            return;
        }

        io.display("logout fail!");
    }

    public void showInformation() {
        Optional<String> userInformation = authenticationController.getUserInformation();
        userInformation.ifPresent(io::display);
    }
}
