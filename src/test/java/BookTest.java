import Model.LibraryItem.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Book")
public class BookTest {

    private Book book;

    @BeforeEach
    public void beforeEach() {
        book = new Book("Book1", "K.", 1994);
    }

    @Nested
    @DisplayName("Match Name")
    class MatchNameTest {
        @Test
        @DisplayName("when input = Book1 -> true")
        public void successMatchName() {
            assertTrue(book.matchName("Book1"));
        }

        @Test
        @DisplayName("when input = BookWhat? -> false")
        public void failMatchName() {
            assertFalse(book.matchName("BookWhat?"));
        }
    }

    @Nested
    @DisplayName("Get information test")
    class GetInformationTest {
        @Test
        @DisplayName("when input = Book1 -> true")
        public void successMatchName() {
            assertEquals("Book1 | K. | 1994",book.getInformation());
        }
    }

}
