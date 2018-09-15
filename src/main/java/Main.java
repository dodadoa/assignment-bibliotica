import Utils.IO;

public class Main {
    public static void main(String[] args) {
        IO io = new IO();
        App app = new App(io);
        app.run();
    }
}
