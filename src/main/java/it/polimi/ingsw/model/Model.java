package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.Tournament;
import it.polimi.ingsw.controller.message.Message;
import it.polimi.ingsw.controller.message.responses.PrivateResponse;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.cards.privates.PrivateObjectiveCard;
import it.polimi.ingsw.model.cards.publics.PublicObjectiveCard;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerBoard;
import it.polimi.ingsw.utils.Utils;

import javax.rmi.CORBA.Util;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/***
 * Model Class
 * The entire game is referred here somehow
 */
public class Model extends Observable implements Serializable {

    /**
     * List of players which are currently playing the game
     */
    private List<Player> players;
    /**
     * Game Board of the game
     */
    private GameBoard gameBoard;

    /**
     * Tournament class in model
     */
    private Tournament tournament;

    /**
     * Each player in game have an identification number.
     * <br>
     * This number is the index in the array of players
     */
    private int currentPlayerNumber = 0;


    /**
     * Main constructor of the Model class
     *
     * @param players array list of player in order to initialize the gameboard
     */
    public Model(List<Player> players) {

        this.players = players;
        gameBoard = new GameBoard(this, players.size());
        tournament = new Tournament(this);

        //This is just for debug

        gameBoard.getWindowpatterndeck().shuffleWindowPatternDeck();

        /*if (players.size() == 1) {
            for (Player player : players) {
                System.out.println(Utils.SINGLE_PLAYER_MODE);
                player.setPlayboard(new PlayerBoard(Utils.RED, gameBoard.getWindowpatterndeck().takeCardFromWPDeck()));
            }


        } else {
            //This is just for debug
            for (Player player : players) {
                player.setPrivateCard((PrivateObjectiveCard) gameBoard.getPrivates().pickCard());
                player.setPlayboard(new PlayerBoard(Utils.RED, gameBoard.getWindowpatterndeck().takeCardFromWPDeck()));
                player.setFavorTokenAmount(player.getPlayerBoard().getWindowboard().getDifficulty());
            }
        }*/

    }

    /**
     * Getter of the list of players currently in game
     *
     * @return players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Getter of the Game board used currently in this game
     *
     * @return gameBoard
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Getter of the Tournament used currently in this game
     *
     * @return tournament
     */
    public Tournament getTournament() {
        return tournament;
    }

    /**
     * Return the index of the current player
     *
     * @return currentPlayerNumber
     */
    public int getCurrentPlayerNumber() {
        return currentPlayerNumber;
    }

    /**
     * Set the number of the index of the current player
     *
     * @param currentPlayerNumber index of the current player in game
     */
    public void setCurrentPlayerNumber(int currentPlayerNumber) {
        this.currentPlayerNumber = currentPlayerNumber;
    }

    /**
     * Method used to notify all the views
     * @param message to send
     */
    public void notifyViews(Message message) {
        setChanged();
        notifyObservers(message);
    }

    /**
     * Method used to print the final score
     */
    public void printFinalScore() {
        int scoreToken = 0;
        int whiteSpace = 0;
        int scorePublics = 0;
        int scorePrivate = 0;

        for (Player p : players) {

            //+1 PV FOREACH FAVOR TOKEN
            scoreToken = p.getFavorTokenAmount();
            scorePrivate = p.getPrivateCard().getScore();

            for (int i = 0; i < Utils.MAX_ROW_WINDOW_PATTERN; i++) {
                for (int j = 0; j < Utils.MAX_COLUMN_WINDOW_PATTERN; j++) {
                    if (p.getPlayerBoard().getWindowboard().getCell(i, j).getDice() == null) {
                        //YOU LOSE 1 POINT FOREACH WHITE SPACE
                        whiteSpace++;
                    }
                }
            }

            for (Card card : getGameBoard().getPublicsInGame()) {
                scorePublics = +((PublicObjectiveCard) card).getScore();
            }


            int finalScore = scoreToken + scorePrivate + scorePublics - whiteSpace;
            notifyViews(new PrivateResponse(Utils.FINAL_SCORE + finalScore, p));

        }


    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}