package Model.LibraryItem;

public class Movie implements LibraryItem {
    private final String name;
    private final String director;
    private final int year;
    private final int movieRating;

    public Movie(String name, String director, int year, int movieRating) {
        this.name = name;
        this.director = director;
        this.year = year;
        this.movieRating = movieRating;
    }

    public boolean matchName(String inputMovieName) {
        return inputMovieName.equals(this.name);
    }

    public String getInformation() {
        return String.format("%s | %s | %s | %s", this.name, this.year, this.director, this.movieRating);
    }
}
