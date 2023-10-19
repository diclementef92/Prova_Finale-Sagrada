package it.polimi.ingsw.model.windowpattern;

import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.player.PlayerBoard;
import it.polimi.ingsw.utils.Color;
import it.polimi.ingsw.utils.Utils;

import static it.polimi.ingsw.utils.Utils.MAX_COLUMN_WINDOW_PATTERN;
import static it.polimi.ingsw.utils.Utils.MAX_ROW_WINDOW_PATTERN;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Window Pattern Card class
 * This class contains the definition of a single Window Pattern card
 */
public class WindowPatternCard implements Serializable {

    /**
     * Name of the Window Pattern card
     */
    private String name;
    /**
     * Difficulty of the Window Pattern card
     * It corresponds to the initial amount of favor tokens
     */
    private int difficulty;

    private ArrayList<ArrayList<Cell>> grid = new ArrayList<>();

    /**
     * Main constructor of the class Window Pattern Card
     * Receives an array of string in order to build a Window Pattern Card
     *
     * @param imported Array of string to be parsed in order to create the object
     */
    public WindowPatternCard(String name, int difficulty, String[] imported) {

        this.name = name;
        this.difficulty = difficulty;
        fillWindow(imported);
    }

    /**
     * Getter of the name of the Window Pattern card
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter of the difficulty of the card
     *
     * @return the difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Method called by the constructor in order to fill the window
     * Receives the array of strings passed originally to the constructor
     *
     * @param imported Array of string used to build the card
     */
    private void fillWindow(String[] imported) {
        int cont = 0;
        for (int i = 0; i < MAX_ROW_WINDOW_PATTERN; i++) {
            ArrayList<Cell> col = new ArrayList<>();
            for (int j = 0; j < MAX_COLUMN_WINDOW_PATTERN; j++) {
                try {
                    col.add(new Cell(Utils.EMPTY_CELL, Integer.parseInt(imported[cont])));
                } catch (NumberFormatException e) {
                    if (imported[cont].equals(Utils.EMPTY_CELL)) {
                        col.add(new Cell(Utils.EMPTY_CELL, 0));
                    } else {
                        col.add(new Cell(imported[cont], 0));
                    }

                } finally {
                    cont++;
                }
            }
            grid.add(col);
        }
    }

    /**
     * Method to retrieve a single cell element from this Window Pattern Card
     * It requires 2 parameters
     *
     * @param i index of the row
     * @param j index of the column
     * @return return the requested Cell
     */
    public Cell getCell(int i, int j) {
        return grid.get(i).get(j);
    }

    /**
     * Method used to insert a dice from the player board to the single cell
     *
     * @param dice Dice to be inserted
     * @param row  Row of cel
     * @param col  Column of the cell
     */
    public void insertDie(Dice dice, int row, int col) {
        grid.get(row).get(col).setDice(dice);
    }

    /**
     * Defined toString method to return the content of this Window Pattern Card
     *
     * @return a string to describe the content of this class
     */
    private String printGrid() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < MAX_ROW_WINDOW_PATTERN; i++) {
            for (int j = 0; j < MAX_COLUMN_WINDOW_PATTERN; j++) {
                ret.append("\t[ ").append(grid.get(i).get(j).getColor()).append(" - ").append(grid.get(i).get(j).getValue()).append(" ]");
            }
            ret.append("\n");
        }
        return ret.toString();
    }

    /**
     * Defined toString method to retrieve information about this window pattern
     *
     * @return String with: Name, Difficulty and the window pattern grid
     */
    public String toString() {
        return "\n=======\n" +
                "Name: " + getName() + "\n" +
                "Difficulty: " + getDifficulty() + "\n" +
                "Grid: " + "\n" +
                printGrid() + "\n" +
                "=======\n";
    }


    /**
     * Method toString used for create a colored window board
     *
     * @return string of the window board
     */
    public String fullColoredString() {
        StringBuilder sr = new StringBuilder();
        for (int i = 0; i < MAX_ROW_WINDOW_PATTERN; i++) {

            for (int k = 0; k < 5; k++) {
                for (int j = 0; j < MAX_COLUMN_WINDOW_PATTERN; j++) {

                    if (this.getCell(i, j).getValue() != 0) {
                        sr.append(PlayerBoard.veryBigNumber(this.getCell(i, j).getValue())[k]);
                    } else if (this.getCell(i, j).getColor().equals(Utils.EMPTY_CELL)) {
                        sr.append(PlayerBoard.emptyFace()[k]);
                    } else {
                        sr.append(Color.returnASCIIStringFormatted(this.getCell(i, j).getColor(),
                                PlayerBoard.coloredFace()[k]));
                    }

                }
                sr.append("\n");
            }
        }
        return sr.toString();
    }


    /**
     * Check if the color and the value of each cell of the window board is the same
     * of the one in obj
     *
     * @param obj the window pattern being equalized
     * @return the boolean value
     */
    @Override
    public boolean equals(Object obj) {
        for (int i = 0; i < Utils.MAX_ROW_WINDOW_PATTERN; i++) {
            for (int j = 0; j < Utils.MAX_COLUMN_WINDOW_PATTERN; j++) {
                if (!(((WindowPatternCard) obj).getCell(i, j).getColor().equals(this.getCell(i, j).getColor())) ||
                        ((WindowPatternCard) obj).getCell(i, j).getValue() != this.getCell(i, j).getValue()) {
                    return false;
                }
            }
        }
        return true;
    }
}
