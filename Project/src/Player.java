public class Player extends Subjects{
    private String NAME; // name
    private int LVL = 1; // level
    private int EXP = 0; // experience
    private int MONEY = 0; // money

    Player(String name, int DMG,  int HP, int MONEY, int X, int Y, String ICON, int EXP, int LVL) {
        super(X, Y, DMG, HP, ICON);
        this.NAME = name;
        this.MONEY = MONEY;
        this.LVL = LVL;
        this.EXP = EXP;
    }
    Player(int DMG,  int HP, int MONEY, int X, int Y, String ICON, int EXP, int LVL) {
        super(X, Y, DMG, HP, ICON);
        this.NAME = "Rogue";
        this.MONEY = MONEY;
        this.LVL = LVL;
        this.EXP = EXP;
    }


    public void setRogueName(String name) {
        this.NAME = name;
    }

    public String getName() {
        return NAME;
    }

    public int getLVL() {
        return LVL;
    }

    public void setLVL(int LVL) {
        this.LVL = LVL;
    }

    public int getEXP() {
        return EXP;
    }

    public void setEXP(int EXP) {
        this.EXP = EXP;
    }

    public int getMONEY() {
        return MONEY;
    }

    public void setMONEY(int MONEY) {
        this.MONEY = MONEY;
    }


}
