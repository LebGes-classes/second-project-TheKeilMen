import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    final static Introduction intro = new Introduction();
    static Game game = new Game();

    public static void main(String[] args) {
       intro.Intro();
       game.runGame(sc);
    }
}