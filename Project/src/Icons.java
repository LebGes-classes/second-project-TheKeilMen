public enum Icons {
    WALL("#"),
    FLOOR("."),
    HERO("*"),
    GOBLIN("h"),
    POISON("p"),
    SWORD("s"),
    STAIRS("="),
    COIN("c");
    public final String icon;
    Icons(String string){
        this.icon = string;
    }
    public String getIcon() {
        return icon;
    }
}
