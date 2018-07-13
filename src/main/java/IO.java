import java.util.Scanner;

public class IO {
    public void display(String message) {
        System.out.println(message);
    }

    public String input() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        return input;
    }
}
