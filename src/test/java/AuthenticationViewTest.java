import Controller.AuthenticationController;
import Model.User.User;
import Utils.IO;
import View.AuthenticationView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class AuthenticationViewTest {
    AuthenticationView authenticationView;
    AuthenticationController authenticationController;
    IO mockIO;

    @BeforeEach
    public void beforeEach() {
        mockIO = mock(IO.class);
        List<User> usersList = new ArrayList<>(Arrays.asList(
                new User("111-1111", "dodadoa", "K.", "k@mail.com", "0801112222"),
                new User("111-0000", "kkk", "Y.", "Y@mail.com", "0802221111")
        ));
        authenticationController = new AuthenticationController(usersList);
        authenticationView = new AuthenticationView(mockIO, authenticationController);
    }

    @Nested
    @DisplayName("Login")
    class LoginTest {
        @DisplayName("login with correct library number and password will display 'login success!'")
        @Test
        public void loginWithCorrectLibraryNumberAndPassword() {
            when(mockIO.input()).thenReturn("111-1111").thenReturn("dodadoa");
            authenticationView.login();
            verify(mockIO).display("login successfully!");
            verify(mockIO, never()).display("login fail!");
        }

        @DisplayName("login with wrong library number will display 'login fail!'")
        @Test
        public void loginWithWrongLibraryNumberAndPassword() {
            when(mockIO.input()).thenReturn("111-0000").thenReturn("dodadoa");
            authenticationView.login();
            verify(mockIO).display("login fail!");
            verify(mockIO, never()).display("login successfully!");
        }
    }

    @Nested
    @DisplayName("Logout")
    class LogoutTest {

        @DisplayName("logout from login status")
        @Test
        public void logoutFromLogin() {
            when(mockIO.input()).thenReturn("111-1111").thenReturn("dodadoa");
            authenticationView.login();

            verify(mockIO).display("login successfully!");
            reset(mockIO);

            authenticationView.logout();
            verify(mockIO).display("logout successfully!");
        }

        @DisplayName("logout fail display fail message")
        @Test
        public void logoutFail() {
            authenticationView.logout();
            verify(mockIO).display("logout fail!");
        }

    }

    @Nested
    @DisplayName("Show information")
    class ShowInformationTest {

        @DisplayName("show information for current user")
        @Test
        public void showInformationForCurrentUser() {
            when(mockIO.input()).thenReturn("111-1111").thenReturn("dodadoa");
            authenticationView.login();
            reset(mockIO);

            authenticationView.showInformation();
            verify(mockIO).display("K., k@mail.com, 0801112222");
        }
    }

}
