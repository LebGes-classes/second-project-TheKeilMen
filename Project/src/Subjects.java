public abstract class Subjects {
    private String ICON;
    private int HP;  // hp
    private int DMG; // damage
    private int X;
    private int Y;

    public void setDMG(int DMG) {
        this.DMG = DMG;
    }
    public int getDMG() {
        return DMG;
    }
    Subjects(int X, int Y, int DMG, int HP, String ICON) {
        this.DMG = DMG;
        this.X = X;
        this.Y = Y;
        this.HP = HP;
        this.ICON = ICON;
    }

    public String getICON() {
        return ICON;
    };
    public int getHP() {
        return HP;
    }
    public void setHP() {
        this.HP = HP;
    }
    public void setX(int x) {
        this.X = x;
    }

    public void setY(int y) {
        this.Y = y;
    }
    public int getX() {
        return X;
    }
    public int getY() {
        return Y;
    }
    protected void damage(String[][] dungeonField, int X, int Y, Subjects subject) {
        if (dungeonField[Y][X-1].equals(subject.getICON())) {
            this.HP -= 10;
        }
        if (dungeonField[Y][X+1].equals(subject.getICON())) {
            this.HP -= 10;
        }
        if (dungeonField[Y-1][X].equals(subject.getICON())) {
            this.HP -= 10;
        }
        if (dungeonField[Y+1][X].equals(subject.getICON())) {
            this.HP -= 10;
        }
    }
}
