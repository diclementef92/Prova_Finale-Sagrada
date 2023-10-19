package it.polimi.ingsw.model.cards.toolcards;

import it.polimi.ingsw.model.cards.toolcards.actions.Action;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.windowpattern.Cell;
import it.polimi.ingsw.utils.Position;
import it.polimi.ingsw.utils.Utils;

import java.util.ArrayList;

/**
 * Tool Card class
 * The tool card class that extends the basic card
 */
public class ToolCard extends Card {

    /**
     * Number of the ToolCard
     */
    private int number;
    /**
     * Color of the ToolCard
     */
    private String color;
    /**
     * Favor token currently placed on the ToolCard
     * At the beginning there a 0 on it
     */
    private int favorToken;

    private ArrayList<Action> actionToPerform;

    /**
     * This arraylist is used to contain all the move action positions when they are used in combination
     * It is also used to check if the player is not trying to move 2 dice in the same position at the end
     */
    private ArrayList<Position> moveDiceFinalPositions = new ArrayList<>();


    /**
     * This arraylist is used to contain all the move starting action positions
     * In that way it is possible to avoid that the player will try to place 2 dices starting from the same  position
     */
    private ArrayList<Position> moveDiceStartingPositions = new ArrayList<>() ;

    private String choseColorFromRoundrack = "default";


    private int anotherMoveDice = -1;


    /**
     * Main constructor of the ToolCard, it takes several parameters
     *
     * @param name        Name of the card (passed up via super)
     * @param number      Number of the card
     * @param color       Color of the card
     * @param description Description of the card (passed up via super)
     */
    public ToolCard(String name, int number, String color, String description, ArrayList<Action> actionToPerform) {
        super(name, description);
        this.number = number;
        this.color = color;
        this.actionToPerform = actionToPerform;

    }

    /**
     * Getter to retrieve the value of the number of the ToolCard
     *
     * @return an integer with the number of the ToolCard
     */
    public int getNumber() {
        return number;
    }

    /**
     * Getter used to retrieve the color of the ToolCard
     *
     * @return a string containing the color of the ToolCard
     */
    public String getColor() {
        return color;
    }

    /**
     * Getter used to retrieve the amount of token placed on the ToolCard
     *
     * @return an integer with the current amount of token on the ToolCard
     */
    public int getFavorToken() {
        return favorToken;
    }

    /**
     * Method used to return the amount of tokens needed to use this ToolCard
     *
     * @return the amount of token needed for this ToolCard
     */
    public int getFavorTokenNeeded() {
        if (getFavorToken() == 0) {
            return Utils.TOKEN_NEEDED_IF_EMPTY;
        } else {
            return Utils.TOKEN_NEEDED_IF_NOT_EMPTY;
        }
    }

    /**
     * Method used to activate the card effect, it is implement on each class
     * that decide to extend this one
     */
    public void setActionToPerform(ArrayList<Action> list) {
        this.actionToPerform = list;
    }

    /**
     * Getter userd to retrieve all the actions in the action list of the ToolCard
     * In that way is possibile to parse them and execute the in oder to complete the ToolCard effect
     *
     * @return a list of actions to perform
     */
    public ArrayList<Action> getActionToPerform() {
        return actionToPerform;
    }

    /**
     * Defined method toString that retrieves information about the ToolCard
     *
     * @return a string that describes the ToolCard
     */
    @Override
    public String toString() {
        return "------\n" +
                "Name: " + getName() + "\n" +
                "Number: " + getNumber() + "\n" +
                "Color: " + getColor() + "\n" +
                "Description: " + getDescription() + "\n" +
                "Token: " + getFavorToken() + "\n" +
                "------\n";
    }

    public void setFavorToken(int favorToken) {
        this.favorToken = favorToken;
    }


    /**
     * Getter of the final position array
     * The array is used to check if the user by mistake is not trying to put in the same
     * place 2 dices when making a combined action
     * @return the array of the final position used up to now
     */
    public ArrayList<Position> getMoveDiceFinalPositions() {
        return moveDiceFinalPositions;
    }

    /**
     * Getter of the starting position array
     * The array is used to check if the user by mistak is not trying to place 2 dices
     * both starting from the same position
     * @return the array of the starting positions used up to now
     */
    public ArrayList<Position> getMoveDiceStartingPositions() {
        return moveDiceStartingPositions;
    }

    /**
     * Setter for the string that represent the color of the chosen dice from the roundrack
     * @param choseColorFromRoundrack string of thte color
     */
    public void setChoseColorFromRoundrack(String choseColorFromRoundrack) {
        this.choseColorFromRoundrack = choseColorFromRoundrack;
    }

    /**
     * Getter to retrieve the string of the color chosen from the roundrack from a previous move
     * @return a color as string
     */
    public String getChoseColorFromRoundrack() {
        return choseColorFromRoundrack;
    }

    /**
     * Method used by the move dice action request to set if the user wants to put anotherMoveDice
     * dice after the first
     * @param anotherMoveDice integer to know if the user wants to put anotherMoveDice dice
     */
    public void setAnotherMoveDice(int anotherMoveDice) {
        this.anotherMoveDice = anotherMoveDice;
    }

    /**
     * Getter of the anotherMoveDice parameter to know if the player wants to set anotherMoveDice
     * dice after the first one
     * @return an integer with value of the anotherMoveDice parameter
     */
    public int getAnotherMoveDice() {
        return anotherMoveDice;
    }


    public void setMoveDiceFinalPositions(ArrayList<Position> moveDiceFinalPositions) {
        this.moveDiceFinalPositions = moveDiceFinalPositions;
    }

    public void setMoveDiceStartingPositions(ArrayList<Position> moveDiceStartingPositions) {
        this.moveDiceStartingPositions = moveDiceStartingPositions;
    }
}