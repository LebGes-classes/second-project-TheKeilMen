import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Field extends Game {
    private static final String FILE_PATH = "/Users/codeinium/programming/javagitclass/algem/RogueGame/src/maps/level1.txt";
    private static final String FILE_PATH2 = "/Users/codeinium/programming/javagitclass/algem/RogueGame/src/maps/level2.txt";
    private int MAX_X;
    private int MAX_Y;
    boolean spawnFlag = false;
    private int levelOfField = 1;
    protected static String[][] dungeonField;
    public Field() {

    }

    public void defaultSpawn(){
        dungeonField[player.getY()][player.getX()] = player.getICON(); // spawn hero
    }

    public void loadDungeonLevels(String filepath) {
        List<Character[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String s;
            while ((s = reader.readLine()) != null) {
                Character[] line = new Character[s.length()];
                for (int j = 0; j < s.length(); j++) {
                    line[j] = s.charAt(j);
                }
                data.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format("File: %s was not found.", filepath), e);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Can't read from file: %s", filepath), e);
        }

        MAX_Y = data.size();
        MAX_X = data.get(0).length;

        Character[][] result = new Character[MAX_Y][MAX_X];
        for (int i = 0; i < data.size(); i++) {
            result[i] = data.get(i);
        }
        String[][] levelField = new String[MAX_Y][MAX_X];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                levelField[i][j] = String.valueOf(result[i][j]);
            }
            System.out.print("\n");
        }
//        for (Character[] line :
//                result) {
//            System.out.println(Arrays.toString(line));
//        }

        dungeonField = levelField;
    }
    public int getMAX_X() {
        return MAX_X;
    }
    public int getMAX_Y() {
        return  MAX_Y;
    }

    public String[][] getDungeonField() {
        return dungeonField;
    }
    public int getMaxX() {
        return MAX_X;
    }
    public int getMaxY() {
        return MAX_Y;
    }

    private void takeMoney() {
        if (dungeonField[player.getY()-1][player.getX()].equals(Icons.COIN.getIcon())) {
            player.setMONEY(player.getMONEY() + 10);
            dungeonField[player.getY()-1][player.getX()] = Icons.FLOOR.getIcon();
        }
        if (dungeonField[player.getY()+1][player.getX()].equals(Icons.COIN.getIcon())) {
            player.setMONEY(player.getMONEY() + 10);
            dungeonField[player.getY()+1][player.getX()] = Icons.FLOOR.getIcon();
        }
        if (dungeonField[player.getY()][player.getX()-1].equals(Icons.COIN.getIcon())) {
            player.setMONEY(player.getMONEY() + 10);
            dungeonField[player.getY()][player.getX()-1] = Icons.FLOOR.getIcon();
        }
        if (dungeonField[player.getY()][player.getX()+1].equals(Icons.COIN.getIcon())) {
            player.setMONEY(player.getMONEY() + 10);
            dungeonField[player.getY()][player.getX()+1] = Icons.FLOOR.getIcon();
        }
    }
    public void makeMoves(String move, Player player, Enemy enemy) {
        if (move.equals("a")) {
            if (dungeonField[player.getY()][player.getX() - 1].equals(Icons.STAIRS.getIcon())) {
                if(player.getX() == 1) {
                    loadDungeonLevels(FILE_PATH);
                    player.setX(27);
                    isAction = false;
                }
                if(player.getX() == 28) {
                    loadDungeonLevels(FILE_PATH2);
                    player.setX(2);
                    spawnEnemy(dungeonField, player.getX() + 20, player.getY() + 3, enemy);
                    enemy.setX(player.getX() + 20);
                    enemy.setY(player.getY() + 3);
                    isAction = true;
                    spawnFlag = true;
                }
            }
            takeMoney();
            if (enemy.getHP() <= 0) {
                isAction = false;
                dungeonField[enemy.getY()][enemy.getX()] = Icons.FLOOR.getIcon();
            }
            if (isAction) {
                if (player.getY() == enemy.getY() && player.getX() > enemy.getX()) {
                    dungeonField[enemy.getY()][enemy.getX()] = Icons.FLOOR.getIcon();
                    enemy.setX(enemy.getX() + 1);
                    if (dungeonField[enemy.getY()][enemy.getX()].equals(Icons.WALL.getIcon()) || dungeonField[enemy.getY()][enemy.getX()].equals(Icons.HERO.getIcon())) {
                        enemy.setX(enemy.getX() - 1);
                    }
                    dungeonField[enemy.getY()][enemy.getX()] = enemy.getICON();
                }
                if (player.getY() == enemy.getY() && player.getX() < enemy.getX()) {
                    dungeonField[enemy.getY()][enemy.getX()] = Icons.FLOOR.getIcon();
                    enemy.setX(enemy.getX() - 1);
                    if (dungeonField[enemy.getY()][enemy.getX()].equals(Icons.WALL.getIcon()) || dungeonField[enemy.getY()][enemy.getX()].equals(Icons.HERO.getIcon())) {
                        enemy.setX(enemy.getX() + 1);
                    }
                    dungeonField[enemy.getY()][enemy.getX()] = enemy.getICON();
                }
                if (player.getY() > enemy.getY()) {
                    dungeonField[enemy.getY()][enemy.getX()] = Icons.FLOOR.getIcon();
                    enemy.setY(enemy.getY() + 1);
                    if (dungeonField[enemy.getY()][enemy.getX()].equals(Icons.WALL.getIcon()) || dungeonField[enemy.getY()][enemy.getX()].equals(Icons.HERO.getIcon())) {
                        enemy.setY(enemy.getY() - 1);
                    }
                    dungeonField[enemy.getY()][enemy.getX()] = enemy.getICON();
                }
                if (player.getY() < enemy.getY()) {
                    dungeonField[enemy.getY()][enemy.getX()] = Icons.FLOOR.getIcon();
                    enemy.setY(enemy.getY() - 1);
                    if (dungeonField[enemy.getY()][enemy.getX()].equals(Icons.WALL.getIcon()) || dungeonField[enemy.getY()][enemy.getX()].equals(Icons.HERO.getIcon())) {
                        enemy.setY(enemy.getY() + 1);
                    }
                    dungeonField[enemy.getY()][enemy.getX()] = enemy.getICON();
                }
            }

            dungeonField[player.getY()][player.getX()] = Icons.FLOOR.getIcon();
            player.setX(player.getX() - 1);

            if (dungeonField[player.getY()][player.getX()].equals(Icons.WALL.getIcon()) || dungeonField[player.getY()][player.getX()].equals(Icons.GOBLIN.getIcon())) {
                player.setX(player.getX() + 1);
            }

            dungeonField[player.getY()][player.getX()] = player.getICON();
            if (spawnFlag) {
                player.damage(dungeonField, player.getX(), player.getY(), enemy);
                goblin.damage(dungeonField, goblin.getX(), goblin.getY(), player);
            }
        }


        if (move.equals("w")) {
            takeMoney();
            if (enemy.getHP() <= 0) {
                isAction = false;
                dungeonField[enemy.getY()][enemy.getX()] = Icons.FLOOR.getIcon();
            }
            if (isAction) {
                if (player.getY() == enemy.getY() && player.getX() > enemy.getX()) {
                    dungeonField[enemy.getY()][enemy.getX()] = Icons.FLOOR.getIcon();
                    enemy.setX(enemy.getX() + 1);
                    if (dungeonField[enemy.getY()][enemy.getX()].equals(Icons.WALL.getIcon()) || dungeonField[enemy.getY()][enemy.getX()].equals(Icons.HERO.getIcon())) {
                        enemy.setX(enemy.getX() - 1);
                    }
                    dungeonField[enemy.getY()][enemy.getX()] = enemy.getICON();
                }
                if (player.getY() == enemy.getY() && player.getX() < enemy.getX()) {
                    dungeonField[enemy.getY()][enemy.getX()] = Icons.FLOOR.getIcon();
                    enemy.setX(enemy.getX() - 1);
                    if (dungeonField[enemy.getY()][enemy.getX()].equals(Icons.WALL.getIcon()) || dungeonField[enemy.getY()][enemy.getX()].equals(Icons.HERO.getIcon())) {
                        enemy.setX(enemy.getX() + 1);
                    }
                    dungeonField[enemy.getY()][enemy.getX()] = enemy.getICON();
                }
                if (player.getY() > enemy.getY()) {
                    dungeonField[enemy.getY()][enemy.getX()] = Icons.FLOOR.getIcon();
                    enemy.setY(enemy.getY() + 1);
                    if (dungeonField[enemy.getY()][enemy.getX()].equals(Icons.WALL.getIcon()) || dungeonField[enemy.getY()][enemy.getX()].equals(Icons.HERO.getIcon())) {
                        enemy.setY(enemy.getY() - 1);
                    }
                    dungeonField[enemy.getY()][enemy.getX()] = enemy.getICON();
                }
                if (player.getY() < enemy.getY()) {
                    dungeonField[enemy.getY()][enemy.getX()] = Icons.FLOOR.getIcon();
                    enemy.setY(enemy.getY() - 1);
                    if (dungeonField[enemy.getY()][enemy.getX()].equals(Icons.WALL.getIcon()) || dungeonField[enemy.getY()][enemy.getX()].equals(Icons.HERO.getIcon())) {
                        enemy.setY(enemy.getY() + 1);
                    }
                    dungeonField[enemy.getY()][enemy.getX()] = enemy.getICON();
                }
            }

            dungeonField[player.getY()][player.getX()] = Icons.FLOOR.getIcon();
            player.setY(player.getY() - 1);

            if (dungeonField[player.getY()][player.getX()].equals(Icons.WALL.getIcon()) || dungeonField[player.getY()][player.getX()].equals(Icons.GOBLIN.getIcon())) {
                player.setY(player.getY() + 1);
            }

            dungeonField[player.getY()][player.getX()] = player.getICON();

            if (spawnFlag) {
                player.damage(dungeonField, player.getX(), player.getY(), enemy);
                goblin.damage(dungeonField, goblin.getX(), goblin.getY(), player);
            }
        }
        if (move.equals("d")) {

            if (dungeonField[player.getY()][player.getX() + 1].equals(Icons.STAIRS.getIcon())) {
                if(player.getX() == 1) {
                    loadDungeonLevels(FILE_PATH);
                    player.setX(27);
                    isAction = false;
                }
                if(player.getX() == 28) {
                    loadDungeonLevels(FILE_PATH2);
                    player.setX(2);
                    spawnEnemy(dungeonField, player.getX() + 20, player.getY() + 3, enemy);
                    enemy.setX(player.getX() + 20);
                    enemy.setY(player.getY() + 3);
                    isAction = true;
                    spawnFlag = true;
                }
            }
            takeMoney();
            if (enemy.getHP() <= 0) {
                isAction = false;
                dungeonField[enemy.getY()][enemy.getX()] = Icons.FLOOR.getIcon();
            }
            if (isAction) {
                if (player.getY() == enemy.getY() && player.getX() > enemy.getX()) {
                    dungeonField[enemy.getY()][enemy.getX()] = Icons.FLOOR.getIcon();
                    enemy.setX(enemy.getX() + 1);
                    if (dungeonField[enemy.getY()][enemy.getX()].equals(Icons.WALL.getIcon()) || dungeonField[enemy.getY()][enemy.getX()].equals(Icons.HERO.getIcon())) {
                        enemy.setX(enemy.getX() - 1);
                    }
                    dungeonField[enemy.getY()][enemy.getX()] = enemy.getICON();
                }
                if (player.getY() == enemy.getY() && player.getX() < enemy.getX()) {
                    dungeonField[enemy.getY()][enemy.getX()] = Icons.FLOOR.getIcon();
                    enemy.setX(enemy.getX() - 1);
                    if (dungeonField[enemy.getY()][enemy.getX()].equals(Icons.WALL.getIcon()) || dungeonField[enemy.getY()][enemy.getX()].equals(Icons.HERO.getIcon())) {
                        enemy.setX(enemy.getX() + 1);
                    }
                    dungeonField[enemy.getY()][enemy.getX()] = enemy.getICON();
                }
                if (player.getY() > enemy.getY()) {
                    dungeonField[enemy.getY()][enemy.getX()] = Icons.FLOOR.getIcon();
                    enemy.setY(enemy.getY() + 1);
                    if (dungeonField[enemy.getY()][enemy.getX()].equals(Icons.WALL.getIcon()) || dungeonField[enemy.getY()][enemy.getX()].equals(Icons.HERO.getIcon())) {
                        enemy.setY(enemy.getY() - 1);
                    }
                    dungeonField[enemy.getY()][enemy.getX()] = enemy.getICON();
                }
                if (player.getY() < enemy.getY()) {
                    dungeonField[enemy.getY()][enemy.getX()] = Icons.FLOOR.getIcon();
                    enemy.setY(enemy.getY() - 1);
                    if (dungeonField[enemy.getY()][enemy.getX()].equals(Icons.WALL.getIcon()) || dungeonField[enemy.getY()][enemy.getX()].equals(Icons.HERO.getIcon())) {
                        enemy.setY(enemy.getY() + 1);
                    }
                    dungeonField[enemy.getY()][enemy.getX()] = enemy.getICON();
                }
            }

            dungeonField[player.getY()][player.getX()] = Icons.FLOOR.getIcon();
            player.setX(player.getX() + 1);

            if (dungeonField[player.getY()][player.getX()].equals(Icons.WALL.getIcon()) || dungeonField[player.getY()][player.getX()].equals(Icons.GOBLIN.getIcon())) {
                player.setX(player.getX() - 1);
            }
            dungeonField[player.getY()][player.getX()] = player.getICON();
            if (spawnFlag) {
                player.damage(dungeonField, player.getX(), player.getY(), enemy);
                goblin.damage(dungeonField, goblin.getX(), goblin.getY(), player);
            }
        }
        if (move.equals("s")) {

            takeMoney();
            if (enemy.getHP() <= 0) {
                isAction = false;
                dungeonField[enemy.getY()][enemy.getX()] = Icons.FLOOR.getIcon();
            }
            if (isAction) {
                if (player.getY() == enemy.getY() && player.getX() > enemy.getX()) {
                    dungeonField[enemy.getY()][enemy.getX()] = Icons.FLOOR.getIcon();
                    enemy.setX(enemy.getX() + 1);
                    if (dungeonField[enemy.getY()][enemy.getX()].equals(Icons.WALL.getIcon()) || dungeonField[enemy.getY()][enemy.getX()].equals(Icons.HERO.getIcon())) {
                        enemy.setX(enemy.getX() - 1);
                    }
                    dungeonField[enemy.getY()][enemy.getX()] = enemy.getICON();
                }
                if (player.getY() == enemy.getY() && player.getX() < enemy.getX()) {
                    dungeonField[enemy.getY()][enemy.getX()] = Icons.FLOOR.getIcon();
                    enemy.setX(enemy.getX() - 1);
                    if (dungeonField[enemy.getY()][enemy.getX()].equals(Icons.WALL.getIcon()) || dungeonField[enemy.getY()][enemy.getX()].equals(Icons.HERO.getIcon())) {
                        enemy.setX(enemy.getX() + 1);
                    }
                    dungeonField[enemy.getY()][enemy.getX()] = enemy.getICON();
                }
                if (player.getY() > enemy.getY()) {
                    dungeonField[enemy.getY()][enemy.getX()] = Icons.FLOOR.getIcon();
                    enemy.setY(enemy.getY() + 1);
                    if (dungeonField[enemy.getY()][enemy.getX()].equals(Icons.WALL.getIcon()) || dungeonField[enemy.getY()][enemy.getX()].equals(Icons.HERO.getIcon())) {
                        enemy.setY(enemy.getY() - 1);
                    }
                    dungeonField[enemy.getY()][enemy.getX()] = enemy.getICON();
                }
                if (player.getY() < enemy.getY()) {
                    dungeonField[enemy.getY()][enemy.getX()] = Icons.FLOOR.getIcon();
                    enemy.setY(enemy.getY() - 1);
                    if (dungeonField[enemy.getY()][enemy.getX()].equals(Icons.WALL.getIcon()) || dungeonField[enemy.getY()][enemy.getX()].equals(Icons.HERO.getIcon())) {
                        enemy.setY(enemy.getY() + 1);
                    }
                    dungeonField[enemy.getY()][enemy.getX()] = enemy.getICON();
                }
            }
            dungeonField[player.getY()][player.getX()] = Icons.FLOOR.getIcon();;
            player.setY(player.getY() + 1);

            if (dungeonField[player.getY()][player.getX()].equals(Icons.WALL.getIcon()) || dungeonField[player.getY()][player.getX()].equals(Icons.GOBLIN.getIcon())) {
                player.setY(player.getY() - 1);
            }
            dungeonField[player.getY()][player.getX()] = player.getICON();
            if (spawnFlag) {
                player.damage(dungeonField, player.getX(), player.getY(), enemy);
                goblin.damage(dungeonField, goblin.getX(), goblin.getY(), player);
            }
        }
    }
    private void spawnEnemy(String[][] dungeonField, int X, int Y, Enemy enemy) {
        dungeonField[Y][X] = enemy.getICON();
    }
}
