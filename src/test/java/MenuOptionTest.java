import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;

public class MenuOptionTest {
    @Test
    public void shouldGetQuitEnumFromStringQuit(){
        Optional<MenuOption> option = MenuOption.getEnumByString("quit");
        assertEquals(MenuOption.QUIT, option.get());
    }

    @Test
    public void shouldNotGetAnythingIfTheInputStringIsNotValid(){
        Optional<MenuOption> option = MenuOption.getEnumByString("not-exist");
        assertFalse(option.isPresent());
    }
}
