package it.polimi.ingsw.controller.message.responses;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.dice.Dice;

/**
 * Skip Turn Response class
 */
public class SkipTurnResponse extends ActionResponse {

    /**
     * Main constructor of the abstract class for the Action Response
     *
     * @param message
     * @param actionType
     * @param dice
     */
    public SkipTurnResponse(String message, String actionType, Dice dice) {
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
