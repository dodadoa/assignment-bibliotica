package Model.LibraryItem;

import java.util.Optional;

public class Movie implements LibraryItem {
    private final String name;
    private final String director;
    private final int year;
    private final int movieRating;
    private boolean availability;

    public Movie(String name, String director, int year, int movieRating) {
        this.name = name;
        this.director = director;
        this.year = year;
        this.movieRating = movieRating;
        this.availability = true;
    }

    public boolean matchAvailable(String moveName) {
        return moveName.equals(this.name) && this.availability;
    }

    public boolean matchNonAvailable(String movieName) {
        return movieName.equals(this.name) && !this.availability;
    }

    public Optional<String> getInformation() {
        if (this.availability) {
            return Optional.of(String.format("%s | %s | %s | %s", this.name, this.year, this.director, this.movieRating));
        }
        return Optional.empty();
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
