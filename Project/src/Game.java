import java.util.Scanner;

public class Game {
    private final String FILE_PATH = "/Users/codeinium/programming/javagitclass/algem/RogueGame/src/maps/level1.txt";
    private String playerName;
    static Player player;
    static Enemy goblin;
    protected boolean isRunning = false;

    static Field dungeonField = new Field();

    protected boolean isAction = false;

    public void runGame(Scanner sc) {

        isRunning = true;

        System.out.print("Rogue's name? " + "\u001B[40m");
        playerName = sc.nextLine();

        player = new Player(playerName, 10, 100, 0, 18, 4, Icons.HERO.getIcon(), 0, 0);
        goblin = new Enemy(10, 40, 0,  0, Icons.GOBLIN.getIcon());

        dungeonField.loadDungeonLevels(FILE_PATH);

        System.out.println("\n");
        System.out.println("Hello dungeonmaster, " + player.getName());

        dungeonField.defaultSpawn();

        while(isRunning) {
            if (player.getHP() <= 0) {
                System.out.println("/start to start new game");
                System.out.println("/exit to exit the game");
                System.out.print("You're dead, start new game? " + "\u001B[40m");
                String menuVariants = sc.nextLine();

                if (menuVariants.equals("/start")) {
                    defaultStart();
                } else if (menuVariants.equals("/exit")) {
                    break;
                } else {
                    System.out.print("Something wrong: ");
                    menuVariants = sc.nextLine();
                }
            }
            process(sc);
        }
    }


    private void defaultStart() {
        dungeonField = new Field();
        dungeonField.loadDungeonLevels(FILE_PATH);

        System.out.println("\n");
        System.out.println("Hello Dungeonmaster, " + playerName);

        player = new Player(playerName, 10, 100, 0, 18, 4, Icons.HERO.getIcon(), 0, 0);
        goblin = new Enemy(10, 40, 0,  0, Icons.GOBLIN.getIcon());

        dungeonField.defaultSpawn();

    }

    private void process(Scanner sc) {
        System.out.print("\033[H\033[J");

        framePrint(dungeonField.getDungeonField(), dungeonField.getMAX_X(), dungeonField.getMAX_Y(), player.getHP(), player.getMONEY(), player.getX(), player.getY(), goblin.getX(), goblin.getY(), goblin.getHP());

        System.out.print("What's move: ");
        String move = sc.nextLine();

        dungeonField.makeMoves(move, player, goblin);

    }
    private static void framePrint(String[][] dungeonField, int MAX_X, int MAX_Y, int HP, int MONEY, int X, int Y, int HOBLIN_X, int HOBLIN_Y, int HOBLIN_HP) {
        for (int i = 0; i < MAX_Y; i++) {
            for (int j = 0; j < MAX_X; j++) {
                System.out.print(dungeonField[i][j]);
            }
            System.out.print("\n");
        }
        if (!(HOBLIN_X == 0 && HOBLIN_Y == 0) && HOBLIN_HP > 0 ) {
            System.out.printf("Health: %d  Gold: %d Hoblin Health: %d \n", HP, MONEY, HOBLIN_HP);
        } else if (HOBLIN_HP <= 0) {
            System.out.printf("Health: %d  Gold: %d X %d Y %d\n", HP, MONEY, X, Y);
        } else {
            System.out.printf("Health: %d  Gold: %d X %d Y %d\n", HP, MONEY,  X, Y);
        }
    }
}
