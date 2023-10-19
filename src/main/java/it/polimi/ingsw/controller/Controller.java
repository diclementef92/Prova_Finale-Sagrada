package it.polimi.ingsw.controller;

import it.polimi.ingsw.controller.message.Message;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.player.Player;

import java.util.Observable;
import java.util.Observer;


/**
 * This is the controller of the game on the server
 */
public class Controller implements Observer {

    private Model model;

    /**
     * Main constructor for controller
     *
     * @param model
     */
    public Controller(Model model) {
        this.model = model;
    }

    /**
     * Method used to get the current model
     *
     * @return the current model
     */
    public Model getModel() {
        return model;
    }

    /**
     * The controller implements Observer
     *
     * @param o   view
     * @param arg message
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Message) {
            ((Message) arg).decrypt(this);
        }
    }

    /**
     * Check if the player requesting the action is the current player in the model
     *
     * @param player the player requesting the action
     * @return a boolean value
     */
    public boolean checkIfCurrentPlayer(Player player) {
        return player.getPlayerNickname().equals(model.getTournament().getRoundPlayers().get(model.getTournament().getCurrentRoundPlayerNumber()).getPlayerNickname());
    }

}
