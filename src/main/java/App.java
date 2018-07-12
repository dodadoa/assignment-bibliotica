public class App {
    private IO io;

    public App(IO io){
        this.io = io;
    }

    public void greeting() {
        io.display("Hello");
    }
}
