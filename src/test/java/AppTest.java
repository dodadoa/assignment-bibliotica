import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AppTest {

    private IO mockIO;
    private App app;

    @Before
    public void beforeEach(){
        mockIO = mock(IO.class);
        app = new App(mockIO);
    }

    @Test
    public void shouldWelcomeUserByGreetingHello(){
        app.greeting();
        verify(mockIO).display("Hello");
    }
}
