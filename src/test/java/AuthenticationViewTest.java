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
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
            Consumer mockConsumer = mock(Consumer.class);
            authenticationView.login(mockConsumer);
            verify(mockIO).display("login success!");
            verify(mockIO, never()).display("login fail!");
        }

        @DisplayName("login with wrong library number will display 'login fail!'")
        @Test
        public void loginWithWrongLibraryNumberAndPassword() {
            when(mockIO.input()).thenReturn("111-0000").thenReturn("dodadoa");
            Consumer mockConsumer = mock(Consumer.class);
            authenticationView.login(mockConsumer);
            verify(mockIO).display("login fail!");
            verify(mockIO, never()).display("login success!");
        }

        @DisplayName("login with correct library number and password will invoke afterLoginSuccess with current library number")
        @Test
        public void loginWithCorrectLibraryNumberAndPasswordWillInvokeAfterLoginSuccessConsumer() {
            when(mockIO.input()).thenReturn("111-1111").thenReturn("dodadoa");
            Consumer mockConsumer = mock(Consumer.class);
            authenticationView.login(mockConsumer);
            verify(mockConsumer).accept("111-1111");
        }

        @DisplayName("isAuth is true when login successfully")
        @Test
        public void logoutChangeIsAuthStatus() {
            when(mockIO.input()).thenReturn("111-1111").thenReturn("dodadoa");
            Consumer mockConsumer = mock(Consumer.class);
            authenticationView.login(mockConsumer);
            assertTrue(authenticationView.isAuth());
        }
    }

    @Nested
    @DisplayName("Logout")
    class LogoutTest {

        @DisplayName("logout from login status")
        @Test
        public void logoutFromLogin() {
            when(mockIO.input()).thenReturn("111-1111").thenReturn("dodadoa");
            Consumer mockConsumer = mock(Consumer.class);
            authenticationView.login(mockConsumer);
            authenticationView.logout();
            verify(mockIO).display("logout successfully!");
        }

        @DisplayName("isAuth is false when logout after login")
        @Test
        public void logoutChangeIsAuthStatus() {
            when(mockIO.input()).thenReturn("111-1111").thenReturn("dodadoa");
            Consumer mockConsumer = mock(Consumer.class);
            authenticationView.login(mockConsumer);
            authenticationView.logout();
            assertFalse(authenticationView.isAuth());
        }
    }

}
