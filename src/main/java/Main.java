import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("Book1", "K.", 1994);
        Book book2 = new Book("Book2", "K.", 1990);
        List<LibraryItem> initBooksList = new ArrayList<>(Arrays.asList(book1, book2));
        Movie movie1 = new Movie("Movie1", "L.", 2000, 10);
        Movie movie2 = new Movie("Movie2", "G.", 3000, 5);
        List<LibraryItem> initMoviesList = new ArrayList<>(Arrays.asList(movie1, movie2));
        IO io = new IO();
        Library bookLibrary = new Library(initBooksList);
        Library movieLibrary = new Library(initMoviesList);

        App app = new App(io, bookLibrary, movieLibrary);
        app.run();
    }
}
