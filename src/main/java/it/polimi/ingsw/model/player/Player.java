package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.cards.privates.PrivateObjectiveCard;

import java.io.Serializable;

/**
 * Player class
 * It is the class that contains the properties of the single player
 * in the game
 */
public class Player implements Serializable {

    private String playerNickname;
    private PlayerBoard playerboard;
    private PrivateObjectiveCard privateCard;
    private int favorTokenAmount;

    /**
     * Main constructor of the Player class
     *
     * @param nickname string of the name given to the player
     */
    public Player(String nickname) {
        this.playerNickname = nickname;
    }

    /**
     * Getter of the nickname od the player
     *
     * @return a string with the nickname of the player
     */
    public String getPlayerNickname() {
        return playerNickname;
    }

    /**
     * Getter of the player board of the player
     *
     * @return the player board of the single player
     */

    public PlayerBoard getPlayerBoard() {
        return playerboard;
    }

    /**
     * Getter of the private Objective Card of the player,
     * the one that is not known to other players
     *
     * @return a single Private Objective Card
     */
    public PrivateObjectiveCard getPrivateCard() {
        return privateCard;
    }

    /**
     * Return the amount of favor token held by the player
     *
     * @return an integer of the amount o favor token
     */
    public int getFavorTokenAmount() {
        return favorTokenAmount;
    }

    /**
     * Setter to give a player board to the player
     *
     * @param playboard a player board to be assigned to this player
     */
    public void setPlayboard(PlayerBoard playboard) {
        this.playerboard = playboard;
    }

    /**
     * Setter to give to the single player a Private Objective Card
     *
     * @param privateCard the Private Objective Card for the player
     */
    public void setPrivateCard(PrivateObjectiveCard privateCard) {
        this.privateCard = privateCard;
    }

    /**
     * Setter to give a certan amount of favor token to the player
     *
     * @param favorTokenAmount number of favor token to be set on the player
     */
    public void setFavorTokenAmount(int favorTokenAmount) {
        this.favorTokenAmount = favorTokenAmount;
    }

    /**
     * Method toString that retrieve the information of the player
     *
     * @return a string with the nickname, favor token and player board
     */
    @Override
    public String toString() {
        return "\n=======\n" +
                "Nickname: " + getPlayerNickname() + "\n" +
                "Favor Token: " + getFavorTokenAmount() + "\n" +
                "Player Board: " + getPlayerBoard().fullColoredString() + "\n" +
                "=======\n";
    }


}


