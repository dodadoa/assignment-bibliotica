import java.util.Optional;

public enum MenuOption {
    QUIT("quit"),
    LIST("list"),
    CHECKOUT("checkout"),
    RETURN("return");

    private String option;

    MenuOption(String option) {
        this.option = option;
    }

    public static Optional<MenuOption> getEnumByString(String inputOption) {
        for (MenuOption menuOption : MenuOption.values()) {
            if (inputOption.equals(menuOption.option)) {
                return Optional.of(menuOption);
            }
        }
        return Optional.empty();
    }

}
