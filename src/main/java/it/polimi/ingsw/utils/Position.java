package it.polimi.ingsw.utils;

import java.io.Serializable;

/**
 * Position class
 */
public class Position implements Serializable {


    int col;
    int row;

    /**
     * Main constructor of the class Position
     * this class is used to store useful information about the position of certain
     * objects inside the game
     *
     * @param row row of the position
     * @param col col of the position
     */
    public Position(int row, int col){
        this.col = col;
        this.row = row;

    }

    /**
     * Getter of the col of the position
     *
     * @return integer for the col of the position
     */
    public int getCol() {
        return col;
    }

    /**
     * Getter of the row of the position
     *
     * @return an integer for the row of the position
     */
    public int getRow() {
        return row;
    }
}
