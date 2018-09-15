package Model.User;

public class User {
    private final String phoneNumber;
    private final String name;
    private final String email;
    private final String libraryNumber;
    private final String password;

    public User(String libraryNumber, String password, String name, String email, String phoneNumber) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public boolean matchUser(String inputLibraryNumber, String password) {
        return this.libraryNumber.equals(inputLibraryNumber) && this.password.equals(password);
    }

    public String getUserInformation() {
        return String.format("%s, %s, %s", this.name, this.email, this.phoneNumber);
    }

    public boolean matchLibraryNumber(String libraryNumber) {
        return this.libraryNumber.equals(libraryNumber);
    }
}
