package it.polimi.ingsw.controller.message.responses;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.dice.Dice;

/**
 * Move Dice Response class
 */
public class MoveDiceResponse extends ActionResponse {


    /**
     * Main constructor of the Move Dice Response
     *
     * @param message
     * @param actionType
     * @param dice
     */
    public MoveDiceResponse(String message, String actionType, Dice dice) {
        super(message, actionType, dice);
    }

    /**
     * Method used to decrypt the message
     * @param controller
     */
    @Override
    public void decrypt(Controller controller) {

    }
}
