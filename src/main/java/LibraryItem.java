import java.util.Optional;

public interface LibraryItem {
    Optional<String> getInformation();
    boolean matchAvailable(String inputName);
    void setAvailability(boolean availability);
    boolean matchNonAvailable(String inputName);
}
