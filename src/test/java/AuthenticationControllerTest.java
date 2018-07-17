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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
        @DisplayName("Should return true when user input correctly with library number '111-1111' and password 'dodadoa'")
        public void loginSuccessTest() {
            authenticationController.login("111-1111", "dodadoa");
            verify(mockOperationObserver).setOperationStatus(true);
        }

        @Test
        @DisplayName("Should return False when user input library number which is not exist")
        public void loginFailWithWrongUserTest() {
            authenticationController.login("000-1111", "dodadoa");
            verify(mockOperationObserver).setOperationStatus(false);
        }

        @Test
        @DisplayName("Should return False when user input 111-1111 but wrong password '0'")
        public void loginFailWithWrongPasswordTest() {
            authenticationController.login("111-1111", "0");
            verify(mockOperationObserver).setOperationStatus(false);
        }
    }
}
