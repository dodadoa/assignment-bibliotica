import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MenuOptionTest {
    @Test
    public void shouldGetQuitEnumFromStringQuit() {
        Optional<MenuOption> option = MenuOption.getEnumByString("quit");
        assertEquals(MenuOption.QUIT, option.get());
    }

    @Test
    public void shouldNotGetAnythingIfTheInputStringIsNotValid() {
        Optional<MenuOption> option = MenuOption.getEnumByString("not-exist");
        assertFalse(option.isPresent());
    }
}
