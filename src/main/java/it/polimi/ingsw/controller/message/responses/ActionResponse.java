package it.polimi.ingsw.controller.message.responses;

import it.polimi.ingsw.controller.message.Message;
import it.polimi.ingsw.model.dice.Dice;

/**
 * This abstract class purpose is to be extended by all the action responses
 * In this way is possible to ex
 */
public abstract class ActionResponse extends Message {

    private String actionType;
    private Dice dice;


    /**
     * Main constructor of the abstract class for the Action Response
     *
     * @param message
     * @param actionType
     */
    public ActionResponse(String message, String actionType, Dice dice) {
        super(message);
        this.actionType = actionType;
        this.dice = dice;
    }

    /**
     * Getter for the action type
     *
     * @return a string with action type to be passed to the ActionInterpreter
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * Getter for the action type
     *
     * @return the dice set in the current turn
     */
    public Dice getDice() {
        return dice;
    }

}
