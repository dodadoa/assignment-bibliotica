import Controller.AuthenticationController;
import Model.User.User;
import Utils.OperationObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@DisplayName("Authentication Controller")
public class AuthenticationControllerTest {

    private AuthenticationController authenticationController;
    private OperationObserver mockOperationObserver;

    @BeforeEach
    public void beforeEach() {
        List<User> usersList = new ArrayList<>(Arrays.asList(
                new User("111-1111", "dodadoa", "K.", "k@mail.com", "0801112222"),
                new User("111-0000", "kkk", "Y.", "Y@mail.com", "0802221111")
        ));
        authenticationController = new AuthenticationController(usersList);
        mockOperationObserver = mock(OperationObserver.class);
        authenticationController.addOperationObserver(mockOperationObserver);
    }

    @Nested
    @DisplayName("Login")
    class LoginTest {
        @Test
        @DisplayName("Should set operation status = true, and isAuth is true when input is correct")
        public void loginSuccessTest() {
            authenticationController.login("111-1111", "dodadoa");
            verify(mockOperationObserver).setOperationStatus(true);
            assertTrue(authenticationController.isAuth());
        }

        @Test
        @DisplayName("Should set operation status = False and isAuth is false when user input library number which is not exist")
        public void loginFailWithWrongUserTest() {
            authenticationController.login("000-1111", "dodadoa");
            verify(mockOperationObserver).setOperationStatus(false);
            assertFalse(authenticationController.isAuth());
        }

        @Test
        @DisplayName("Should set operation status = False, and isAuth is false when user input 111-1111 but wrong password '0'")
        public void loginFailWithWrongPasswordTest() {
            authenticationController.login("111-1111", "0");
            verify(mockOperationObserver).setOperationStatus(false);

        }
    }

    @Nested
    @DisplayName("Logout")
    class LogoutTest {

        @BeforeEach
        public void loginBeforeEachLogout() {
            authenticationController.login("111-1111", "dodadoa");
            reset(mockOperationObserver);
        }

        @Test
        @DisplayName("Should set operation = True and isAuth is false when logout success")
        public void logoutSuccess() {
            authenticationController.logout();
            verify(mockOperationObserver).setOperationStatus(true);
            assertFalse(authenticationController.isAuth());
        }
    }

    @Nested
    @DisplayName("Get User Information")
    class GetUserInformationTest {

        @DisplayName("should get user information for authorized user")
        @Test
        public void getUserInformationSuccessfully() {
            authenticationController.login("111-1111", "dodadoa");
            String userInformation = "K., k@mail.com, 0801112222";
            assertEquals(userInformation, authenticationController.getUserInformation().get());
        }

        @DisplayName("should get empty for unauthorized user")
        @Test
        public void getEmpty() {
            assertFalse(authenticationController.getUserInformation().isPresent());
        }
    }
}
