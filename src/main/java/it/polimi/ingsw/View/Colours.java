package it.polimi.ingsw.View;

public enum Colours {

    ANSI_BLACK("\u001B[30m"),
    ANSI_RED ("\u001B[31m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_YELLOW("\u001B[33m"),
    ANSI_BLUE("\u001B[34m"),
    ANSI_PURPLE("\u001B[35m"),
    ANSI_CYAN("\u001B[36m");

    public static final String RESET = "\u001B[0m";

    private final String escape;

    Colours(String escape) {
        this.escape = escape;
    }

    public String escape() {
        return escape;
    }

    public static String RESET() {
        return RESET;
    }
}
