package it.polimi.ingsw.controller.message.requests;


import it.polimi.ingsw.controller.message.Message;
import it.polimi.ingsw.model.player.Player;

/**
 * Abstrack class for Request
 */
public abstract class Request extends Message {

    private Player player;

    /**
     * Main constructor of the abstract class Request
     *
     * @param message: request message
     * @param player: player whom requested it
     */
    public Request(String message, Player player) {
        super(message);
        this.player = player;
    }

    /**
     * Method used to get the player
     *
     * @return get the player whom requested it
     */
    public Player getPlayer() {
        return player;
    }
}

