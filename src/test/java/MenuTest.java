import Controller.AuthenticationController;
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
    private AuthenticationController mockAuthenticationController;
    private IO mockIO;
    private Menu menu;
    private Menu spyMenu;

    @BeforeEach
    public void beforeEach() {
        mockApp = mock(App.class);
        mockIO = mock(IO.class);
        mockLibraryItemView = mock(LibraryItemView.class);
        mockAuthenticationView = mock(AuthenticationView.class);
        mockAuthenticationController = mock(AuthenticationController.class);
        menu = new Menu(mockApp, mockIO, mockLibraryItemView, mockAuthenticationView, mockAuthenticationController);
        spyMenu = spy(menu);
    }

    @Nested
    @DisplayName("Start")
    class StartTest {

        @DisplayName("should go to authorized menu when it's authorized")
        @Test
        public void testAuthorizedMenu() {
            when(mockIO.input()).thenReturn("");
            when(mockAuthenticationController.isAuth()).thenReturn(true);
            spyMenu.start();
            verify(spyMenu).authorizedMenu();
        }

        @DisplayName("should go to unauthorized menu when it's unauthorized")
        @Test
        public void testUnauthorizedMenu() {
            when(mockIO.input()).thenReturn("");
            when(mockAuthenticationController.isAuth()).thenReturn(false);
            spyMenu.start();
            verify(spyMenu).unauthorizedMenu();
        }
    }

    @Nested
    @DisplayName("Authorized Menu")
    class AuthorizedMenu {

        @DisplayName("input = list -> go to library view list")
        @Test
        public void libraryViewWork() {
            when(mockIO.input()).thenReturn("list");
            menu.authorizedMenu();
            verify(mockLibraryItemView).listBranch();
        }

        @DisplayName("input = info -> go to authentication show user information")
        @Test
        public void authenticationViewWork() {
            when(mockIO.input()).thenReturn("info");
            menu.authorizedMenu();
            verify(mockAuthenticationView).showInformation();
        }

        @DisplayName("wrong input display 'Select a valid option!'")
        @Test
        public void wrongInput() {
            when(mockIO.input()).thenReturn("wrongdoing");
            menu.authorizedMenu();
            verify(mockIO).display("Select a valid option!");
        }
    }

    @Nested
    @DisplayName("Unauthorized Menu")
    class UnauthorizedMenu {

        @DisplayName("input = list -> go to library view list")
        @Test
        public void libraryViewWork() {
            when(mockIO.input()).thenReturn("list");
            menu.unauthorizedMenu();
            verify(mockLibraryItemView).listBranch();
        }

        @DisplayName("input = login -> go to authentication view login")
        @Test
        public void authenticationViewWork() {
            when(mockIO.input()).thenReturn("login");
            menu.unauthorizedMenu();
            verify(mockAuthenticationView).login();
        }

        @DisplayName("wrong input display 'Select a valid option!'")
        @Test
        public void wrongInput() {
            when(mockIO.input()).thenReturn("wrongdoing");
            menu.unauthorizedMenu();
            verify(mockIO).display("Select a valid option!");
        }
    }
}
