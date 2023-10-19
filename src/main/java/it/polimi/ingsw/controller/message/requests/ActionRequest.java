package it.polimi.ingsw.controller.message.requests;

import it.polimi.ingsw.model.player.Player;

/**
 * Abstract class for Action Request
 */
public abstract class ActionRequest extends Request {

    /**
     * Main constructor of the abstract class action request
     * @param message: action request message
     * @param player: player whom requested it
     */
    public ActionRequest(String message, Player player) {
        super(message, player);
    }

}
