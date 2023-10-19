package it.polimi.ingsw.model.windowpattern;

import it.polimi.ingsw.model.dice.Dice;

import java.io.Serializable;

/**
 * Cell class
 * It is used to define the single cell of the Window Pattern
 */
public class Cell implements Serializable {

    /**
     * There are no setter functions for the color and the value
     * as they are passed immediately in the constructor
     */
    private String color;
    private int value;
    private Dice dice;

    /**
     * Main constructor of the Cell class
     *
     * @param color Color defined for the cell, it may be optional
     * @param value Value of the die for the cell, it may be optional
     */
    public Cell(String color, int value) {
        this.color = color;
        this.value = value;

    }

    /**
     * Getter of the color value
     *
     * @return the color value
     */
    public String getColor() {
        return color;
    }

    /**
     * Getter of the value
     *
     * @return the value required by the die to be placed in
     */
    public int getValue() {
        return value;
    }

    /**
     * Getter of the die placed inside the cell
     * It returns null if there is no die inside
     *
     * @return The die inside the cell
     */
    public Dice getDice() {
        return dice;
    }

    /**
     * Setter of the dice
     * This operation is performed in order to set a dice inside
     * the cell
     *
     * @param dice to place in the cell
     */
    public void setDice(Dice dice) {
        this.dice = dice;
    }

    /**
     * Defined method toString to return a string of the Cell class
     *
     * @return a string with Cell information
     */
    public String toString() {
        return getColor() + " " + getDice() + " " + getDice();
    }
}
