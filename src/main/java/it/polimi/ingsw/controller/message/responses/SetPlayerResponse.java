package it.polimi.ingsw.controller.message.responses;

import it.polimi.ingsw.model.player.Player;

/**
 * Set Player Response class
 */
public class SetPlayerResponse extends Response {

    private Player player;

    /**
     * Main constructor for Set Player Response
     * @param message
     * @param player
     */
    public SetPlayerResponse(String message, Player player) {
        super(message);
        this.player = player;
    }

    /**
     * Method used to get the player
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }
}
