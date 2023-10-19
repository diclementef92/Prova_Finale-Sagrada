package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.windowpattern.WindowPatternCard;
import it.polimi.ingsw.utils.Color;
import it.polimi.ingsw.utils.Utils;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.utils.Utils.MAX_COLUMN_WINDOW_PATTERN;
import static it.polimi.ingsw.utils.Utils.MAX_ROW_WINDOW_PATTERN;

/**
 * Player Board class
 * This board is held by every player in the game and contains the used
 * Window Pattern card
 */
public class PlayerBoard implements Serializable {

    /**
     * Board color
     * There is no setter for this attribute, as it is given in the constructor
     */
    private String color;

    /**
     * Window Pattern Card assigned to this player board
     * It was chosen at the beginning of the game by the player
     * <p>
     * There is no setter for this attribute, as it is given in the constructor
     */
    private WindowPatternCard windowboard;

    private static final Logger LOGGER = Logger.getLogger(PlayerBoard.class.getName());

    /**
     * Main constructor of the Player Board class
     * It needs a color and a Window Pattern card
     *
     * @param color    String containing the color of the board
     * @param selected Selected Window Pattern Card for this board
     */
    public PlayerBoard(String color, WindowPatternCard selected) {
        this.color = color;
        this.windowboard = selected;
    }

    /**
     * Getter to retrieve the color of this player board
     *
     * @return a string containing the color of this board
     */
    public String getColor() {
        return color;
    }

    /**
     * Getter used to retrieve te Window Pattern Card held by
     * this player board
     *
     * @return this Window Pattern Card
     */
    public WindowPatternCard getWindowboard() {
        return windowboard;
    }

    /**
     * Method used to place a die from the player board to the single cell
     *
     * @param dice to be placed
     * @param row  of the cell where the player wants to place the dice
     * @param col  of the cell where the player wants to place the dice
     */
    public void placeDice(Dice dice, int row, int col) {
        windowboard.insertDie(dice, row, col);
    }

    /**
     * Method to check the validity of the placement inside
     *
     * @param dice    to be placed
     * @param row     of the cell where you want to place the dice
     * @param col     of the cell where you want to place the dice
     * @param selfRow row of the initial position of the dice (only if required)
     * @param selfCol col of the initial position of the dice (only if required)
     * @return a boolean value to determine if the placement is correct or not
     */
    public boolean checkValidityPlacement(Dice dice, int row, int col, int selfRow, int selfCol) {

        if (checkIfCellIsEmpty(row, col)) {
            if ((checkColorValidity(dice, row, col) && checkValueValidity(dice, row, col))
                    || checkIfWhiteCell(dice, row, col)) {

                if (checkisFirstDice()) {
                    return checkIfCornersOrEdge(row, col);

                } else {
                    if (checkIfDicesAround(row, col, selfRow, selfCol)) {
                        return checkIfOrthogonalIgnoreSelf(dice, row, col, selfRow, selfCol);
                    }
                }
            }
        }


        return false;
    }

    /**
     * Method used to check if the cell is empty
     *
     * @param row of the cell
     * @param col of the cell
     * @return a boolean value to determine if the placement is correct or not
     */
    public boolean checkIfCellIsEmpty(int row, int col) {
        if (windowboard.getCell(row, col).getDice() == null) {
            LOGGER.log(Level.FINE, Utils.CELL_IS_EMPTY);
            return true;
        } else {
            LOGGER.log(Level.FINE, Utils.CELL_IS_EMPTY);
            return false;
        }
    }

    /**
     * Method used to check if it is the first dice in the player board
     *
     * @return a boolean value to determinate if the dice is the first or not
     */
    public boolean checkisFirstDice() {
        boolean flag = true;
        for (int i = 0; i < MAX_ROW_WINDOW_PATTERN; i++) {
            for (int j = 0; j < MAX_COLUMN_WINDOW_PATTERN; j++) {
                if (windowboard.getCell(i, j).getDice() != null) {
                    flag = false;
                }
            }
        }

        if (flag) {
            LOGGER.log(Level.FINE, Utils.NO_DICE_IN_PLAYERBOARD);
        }

        return flag;

    }

    /**
     * Method used to check if the cell is white
     * White space is used for cell without restrictions
     *
     * @param dice to place
     * @param row  of the cell
     * @param col  of the cell
     * @return a boolean value to determinate if the cell is white or not
     */
    public boolean checkIfWhiteCell(Dice dice, int row, int col) {
        if (windowboard.getCell(row, col).getValue() == 0 && windowboard.getCell(row, col).getColor().equals(Utils.EMPTY_CELL)) {
            LOGGER.log(Level.FINE, Utils.CELL_WITHOUT_RESTRICTION);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method used to check if the color of the cell is equal to the color of the dice
     *
     * @param dice to place
     * @param row  of the cell
     * @param col  of the cell
     * @return a boolean to determinate if the colors match
     */
    public boolean checkColorValidity(Dice dice, int row, int col) {

        if (windowboard.getCell(row, col).getColor().equals(dice.getColor()) || (windowboard.getCell(row, col).getColor().equals(Utils.EMPTY_CELL))) {
            LOGGER.log(Level.FINE, Utils.CELL_WITH_COLOR_RESTRICTION);
            return true;
        } else {
            LOGGER.log(Level.FINE, Utils.CELL_WITHOUT_COLOR_RESTRICTION);
            return false;
        }
    }

    /**
     * Method used for check if the value of the cell is equal to the value of the dice
     *
     * @param dice to place
     * @param row  of the cell
     * @param col  of the cell
     * @return a boolean to determinate if the values match
     */
    public boolean checkValueValidity(Dice dice, int row, int col) {
        if (windowboard.getCell(row, col).getValue() == dice.getValue() || windowboard.getCell(row, col).getValue() == 0) {
            LOGGER.log(Level.FINE, Utils.DICE_WITH_VALUE_RESTRICTION);
            return true;
        } else {
            LOGGER.log(Level.FINE, Utils.DICE_WITHOUT_VALUE_RESTRICTION + windowboard.getCell(row, col).getValue());
            return false;
        }
    }


    /**
     * Method used to check if the dice is placed at corners or edges of the board
     *
     * @param row row passed
     * @param col col passed
     * @return a boolean value to know if the dice is at corners or edges
     */
    public boolean checkIfCornersOrEdge(int row, int col) {
        if (col == 0 || row == 0 || col == MAX_COLUMN_WINDOW_PATTERN - 1 || row == MAX_ROW_WINDOW_PATTERN - 1) {
            LOGGER.log(Level.FINE, Utils.DICE_CONTAINED_IN_BORDER);
            return true;
        } else {
            LOGGER.log(Level.FINE, Utils.DICE_NOT_CONTAINED_IN_BORDER);
            return false;
        }

    }

    /**
     * Method used to check if there are dices around the place identified by row and col
     *
     * @param finalRow final row of the move
     * @param finalCol final col of the move
     * @param startRow starting row of the move
     * @param startCol starting col of the move
     * @return a boolean value
     */
    public boolean checkIfDicesAround(int finalRow, int finalCol, int startRow, int startCol) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((finalRow - i >= 0) && (finalRow - i <= MAX_ROW_WINDOW_PATTERN - 1) && ((finalCol - j >= 0) && (finalCol - j <= MAX_COLUMN_WINDOW_PATTERN - 1))) {
                    if(finalCol-i != startCol && finalRow-i != startRow) {
                        if (windowboard.getCell(finalRow - i, finalCol - j).getDice() != null) {
                            //controllo
                            LOGGER.log(Level.FINE, Utils.DICE_ADJACENT);
                            return true;


                        }
                    }
                }
            }
        }
        LOGGER.log(Level.FINE, Utils.NO_DICE_ADJACENT);
        return false;
    }


    /**
     * Method used to check if there are orthogonal restrictions to the passed dice
     * in the passed position
     * <p>
     * If the check of the startingRow and the startingCol is not required the passed value
     * is the default -3. In that case the internal if conditions are bypassed
     *
     * @param dice        dice to be placed
     * @param finalRow    row passed of the final position
     * @param finalCol    col passed  of the final position
     * @param startingRow row of the initial position of the dice
     * @param startingCol col of the initial position of the dice
     * @return a boolean value to check the orthogonal control
     */
    public boolean checkIfOrthogonalIgnoreSelf(Dice dice, int finalRow, int finalCol, int startingRow, int startingCol) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                if ((finalRow - i >= 0) && (finalRow - i <= MAX_ROW_WINDOW_PATTERN - 1) && ((finalCol - j >= 0) && (finalCol - j <= MAX_COLUMN_WINDOW_PATTERN - 1))) {
                    if ((i == 0 || j == 0) && i != j && (finalRow - i != startingRow) && (finalCol - j != startingCol)) {
                        if (windowboard.getCell(finalRow - i, finalCol - j).getDice() != null) {

                            if (windowboard.getCell(finalRow - i, finalCol - j).getDice().getColor().equals(dice.getColor()) ||
                                    windowboard.getCell(finalRow - i, finalCol - j).getDice().getValue() == dice.getValue()) {
                                LOGGER.log(Level.FINE, Utils.DICE_WITH_SAME_COLOR_OR_VALUE);
                                return false;
                            }
                        }
                    }

                }
            }


        }

        System.out.println(Utils.DICE_WITH_NO_SAME_COLOR_OR_VALUE);
        return true;
    }


    /**
     * Method used for counting the number of white cells without dices in the window board
     * It is used only at the end of game for calculate player points
     *
     * @return number of white cells without dices
     */
    public int whiteSpace() {
        int whiteSpace = 0;

        for (int i = 0; i < Utils.MAX_ROW_WINDOW_PATTERN; i++) {
            for (int j = 0; j < Utils.MAX_COLUMN_WINDOW_PATTERN; j++) {
                if (windowboard.getCell(i, j).getDice() == null) {
                    //YOU LOSE 1 POINT FOREACH WHITE SPACE
                    whiteSpace++;
                }
            }
        }
        return whiteSpace;
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
                    if (getWindowboard().getCell(i, j).getDice() != null) {
                        sr.append(Color.returnASCIIStringFormatted(getWindowboard().getCell(i, j).getDice().getColor(), Dice.diceFaces(getWindowboard().getCell(i, j).getDice().getValue())[k]));
                    } else {
                        if (this.getWindowboard().getCell(i, j).getValue() != 0) {
                            sr.append(PlayerBoard.veryBigNumber(this.getWindowboard().getCell(i, j).getValue())[k]);
                        } else if (this.getWindowboard().getCell(i, j).getColor().equals(Utils.EMPTY_CELL)) {
                            sr.append(PlayerBoard.emptyFace()[k]);
                        } else {
                            sr.append(Color.returnASCIIStringFormatted(this.getWindowboard().getCell(i, j).getColor(),
                                    PlayerBoard.coloredFace()[k]));
                        }
                    }
                }
                sr.append("\n");
            }
        }
        return sr.toString();
    }


    /**
     * Method toString for write the player board
     *
     * @return string of the player board
     */
    @Override
    public String toString() {
        StringBuilder sr = new StringBuilder();
        for (int i = 0; i < MAX_ROW_WINDOW_PATTERN; i++) {
            for (int j = 0; j < MAX_COLUMN_WINDOW_PATTERN; j++) {
                if (this.getWindowboard().getCell(i, j).getDice() != null) {
                    sr.append("[ ").append(this.getWindowboard().getCell(i, j).getDice().getColor()).append(" ]");
                } else {
                    sr.append("[ - ]");
                }

            }
            sr.append("\n");
        }
        return sr.toString();
    }

    /**
     * Method used for create the sketch of an empty cell on window board
     *
     * @return string of an empty cell on board
     */
    public static String[] emptyFace() {
        return new String[]{"┏━━━━━━━┓", "┃       ┃", "┃       ┃", "┃       ┃", "┗━━━━━━━┛"};
    }


    /**
     * Method used for create the sketch of a colored cell on window board
     *
     * @return string of a colored cell on board
     */
    public static String[] coloredFace() {
        return new String[]{"┏━━━━━━━┓", "┃███████┃", "┃███████┃", "┃███████┃", "┗━━━━━━━┛"};
    }

    /**
     * Method used for drawing numbers in the cells of the window board
     *
     * @param number to draw
     * @return string of a numbered cell on board
     */
    public static String[] veryBigNumber(int number) {
        switch (number) {
            case 1:
                return new String[]{"┏━━━━━━━┓", "┃   ╻   ┃", "┃   ┃   ┃", "┃   ╹   ┃", "┗━━━━━━━┛"};
            case 2:
                return new String[]{"┏━━━━━━━┓", "┃  ╺━┓  ┃", "┃  ┏━┛  ┃", "┃  ┗━╸  ┃", "┗━━━━━━━┛"};
            case 3:
                return new String[]{"┏━━━━━━━┓", "┃  ╺━┓  ┃", "┃   ━┫  ┃", "┃  ╺━┛  ┃", "┗━━━━━━━┛"};
            case 4:
                return new String[]{"┏━━━━━━━┓", "┃  ╻ ╻  ┃", "┃  ┗━┫  ┃", "┃    ╹  ┃", "┗━━━━━━━┛"};
            case 5:
                return new String[]{"┏━━━━━━━┓", "┃  ┏━╸  ┃", "┃  ┗━┓  ┃", "┃  ╺━┛  ┃", "┗━━━━━━━┛"};
            case 6:
                return new String[]{"┏━━━━━━━┓", "┃  ┏━╸  ┃", "┃  ┣━┓  ┃", "┃  ┗━┛  ┃", "┗━━━━━━━┛"};
        }
        return new String[]{"wat", "wat"};
    }

}