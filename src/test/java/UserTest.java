import Model.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("User")
public class UserTest {
    private User user;

    @BeforeEach
    public void beforeEach() {
        user = new User("111-1111", "dodadoa", "K.", "k@mail.com", "0813871226");
    }

    @Nested
    @DisplayName("Match User")
    class MatchUserTest {

        @DisplayName("should be True when libraryNumber and password correct")
        @Test
        public void successfulMatch() {
            assertTrue(user.matchUser("111-1111", "dodadoa"));
        }

        @DisplayName("should be False When libraryNumber is wrong")
        @Test
        public void failMatch() {
            assertFalse(user.matchUser("111-0000", "dodadoa"));
        }
    }

    @Nested
    @DisplayName("display User information")
    class displayUserInformationTest {
        @DisplayName("should get 'K., k@mail.com, 0813871226'")
        @Test
        public void getUserInformation() {
            assertEquals(
                    "K., k@mail.com, 0813871226",
                    user.getUserInformation()
            );
        }
    }

}
