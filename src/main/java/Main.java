import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Book book1 = new Book("Book1", "K.", 1994);
        Book book2 = new Book("Book2", "K.", 1990);
        List<Book> initListsOfBooks = new ArrayList<>(Arrays.asList(book1, book2));
        IO io = new IO();
        Library library = new Library(initListsOfBooks);

        App app = new App(io, library);
        app.run();
    }
}
