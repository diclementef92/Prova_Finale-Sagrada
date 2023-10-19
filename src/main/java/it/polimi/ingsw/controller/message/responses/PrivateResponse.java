package it.polimi.ingsw.controller.message.responses;

import it.polimi.ingsw.model.player.Player;

/**
 * Private Response class
 */
public class PrivateResponse extends Response {

    private Player player;

    /**
     * Main constructor for Private Response
     *
     * @param message
     * @param player
     */
    public PrivateResponse(String message, Player player) {
        super(message);
        this.player = player;
    }

    /**
     * method used to get the player
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }
}
