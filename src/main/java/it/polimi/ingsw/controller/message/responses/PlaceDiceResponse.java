package it.polimi.ingsw.controller.message.responses;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.dice.Dice;

/**
 * PlaceDiceResponse class
 */
public class PlaceDiceResponse extends ActionResponse {

    /**
     * Main constructor of the Place Dice Response
     *
     * @param message
     * @param actionType
     * @param dice
     */
    public PlaceDiceResponse(String message, String actionType, Dice dice) {
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
