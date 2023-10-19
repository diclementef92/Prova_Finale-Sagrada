package it.polimi.ingsw.controller.message.responses;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.dice.Dice;

/**
 * Swap Dice Response class
 */
public class SwapDiceResponse extends ActionResponse {

    /**
     * Main constructor of the Swap Dice Response
     *
     * @param message
     * @param actionType
     * @param dice
     */
    public SwapDiceResponse(String message, String actionType, Dice dice) {
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
