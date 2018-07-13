public class Book {
    private final String name;
    private final String author;
    private final int yearPublished;

    public Book(String name, String author, int yearPublished) {
        this.name = name;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String getInformation() {
        return String.format("%s | %s | %s", this.name, this.author, this.yearPublished);
    }
}
