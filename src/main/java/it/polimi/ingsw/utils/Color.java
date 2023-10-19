package it.polimi.ingsw.utils;

import java.io.Serializable;

/**
 * Color class
 */
public enum Color implements Serializable {
    ANSI_RED("\u001B[31m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_YELLOW("\u001B[33m"),
    ANSI_BLUE("\u001B[34m"),
    ANSI_PURPLE("\u001B[35m"),
    ANSI_GREY("\u001B[37m");

    public static final String RESET = "\u001B[0m";
    private String escape;

    Color(String escape) {
        this.escape = escape;
    }

    public String escape() {
        return escape;
    }

    public static String returnASCII(String color) {
        if ("red".equals(color)) {
            return ANSI_RED.escape;
        } else if ("green".equals(color)) {
            return ANSI_GREEN.escape;
        } else if ("purple".equals(color)) {
            return ANSI_PURPLE.escape;
        } else if ("blue".equals(color)) {
            return ANSI_BLUE.escape;
        } else if ("yellow".equals(color)) {
            return ANSI_YELLOW.escape;
        }
        return "";

    }

    public static String returnASCIIStringFormatted(String color, String input){
        if ("red".equals(color)) {
            return ANSI_RED.escape + input + RESET;
        } else if ("green".equals(color)) {
            return ANSI_GREEN.escape + input + RESET;
        } else if ("purple".equals(color)) {
            return ANSI_PURPLE.escape + input + RESET;
        } else if ("blue".equals(color)) {
            return ANSI_BLUE.escape + input + RESET;
        } else if ("yellow".equals(color)) {
            return ANSI_YELLOW.escape + input + RESET;
        }
        return "";
    }
}