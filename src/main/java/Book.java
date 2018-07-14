import java.util.Optional;

public class Book {
    private final String name;
    private final String author;
    private final int yearPublished;
    private boolean availability;

    public Book(String name, String author, int yearPublished) {
        this.name = name;
        this.author = author;
        this.yearPublished = yearPublished;
        this.availability = true;
    }

    public boolean checkExistenceFromBookName(String bookName){
        return bookName.equals(this.name) && this.availability;
    }

    public boolean isBookBelongToLibrary(String bookName){
        return bookName.equals(this.name) && !this.availability;
    }

    public Optional<String> getInformation() {
        if(this.availability){
            return Optional.of(String.format("%s | %s | %s", this.name, this.author, this.yearPublished));
        }
        return Optional.empty();
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
