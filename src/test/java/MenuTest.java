import Utils.IO;
import View.AuthenticationView;
import View.LibraryItemView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

@DisplayName("Menu")
public class MenuTest {

    private App mockApp;
    private LibraryItemView mockLibraryItemView;
    private AuthenticationView mockAuthenticationView;
    private IO mockIO;
    private Menu menu;
    private Menu spyMenu;

    @BeforeEach
    public void beforeEach() {
        mockApp = mock(App.class);
        mockIO = mock(IO.class);
        mockLibraryItemView = mock(LibraryItemView.class);
        mockAuthenticationView = mock(AuthenticationView.class);
        menu = new Menu(mockApp, mockIO, mockLibraryItemView, mockAuthenticationView);
        spyMenu = spy(menu);
    }

    @Nested
    @DisplayName("Start")
    class StartTest {

        @DisplayName("should go to authorized menu when it's authorized")
        @Test
        public void testAuthorizedMenu() {
            when(mockIO.input()).thenReturn("");
            when(mockAuthenticationView.isAuth()).thenReturn(true);
            spyMenu.start();
            verify(spyMenu).authorizedMenu();
        }

        @DisplayName("should go to unauthorized menu when it's unauthorized")
        @Test
        public void testUnauthorizedMenu() {
            when(mockIO.input()).thenReturn("");
            when(mockAuthenticationView.isAuth()).thenReturn(false);
            spyMenu.start();
            verify(spyMenu).unauthorizedMenu();
        }
    }
}
