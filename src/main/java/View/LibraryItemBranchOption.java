package View;

import java.util.Optional;

public enum LibraryItemBranchOption {
    BOOK("book"),
    MOVIE("movie");

    private String option;

    LibraryItemBranchOption(String option) {
        this.option = option;
    }

    public static Optional<LibraryItemBranchOption> getEnumByString(String inputOption) {
        for (LibraryItemBranchOption libraryItemBranchOption : LibraryItemBranchOption.values()) {
            if (inputOption.equals(libraryItemBranchOption.option)) {
                return Optional.of(libraryItemBranchOption);
            }
        }
        return Optional.empty();
    }
}
