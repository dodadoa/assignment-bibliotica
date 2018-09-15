import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Menu Option")
public class MenuOptionTest {

    @DisplayName("get QUIT from input 'quit'")
    @Test
    public void shouldGetQuitEnumFromStringQuit() {
        Optional<MenuOption> option = MenuOption.getEnumByString("quit");
        assertEquals(MenuOption.QUIT, option.get());
    }

    @DisplayName("get Nothing when input 'not-exist'")
    @Test
    public void shouldNotGetAnythingIfTheInputStringIsNotValid() {
        Optional<MenuOption> option = MenuOption.getEnumByString("not-exist");
        assertFalse(option.isPresent());
    }
}
