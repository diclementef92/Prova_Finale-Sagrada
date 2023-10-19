package it.polimi.ingsw.controller.message.responses;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.dice.Dice;

/**
 * Change Dice Value Response class
 */
public class ChangeDiceValueResponse extends ActionResponse {

    /**
     * Main constructor of the Change Dice value Response
     *
     * @param message
     * @param actionType
     * @param dice
     */
    public ChangeDiceValueResponse(String message, String actionType, Dice dice) {
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

