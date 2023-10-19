package it.polimi.ingsw.controller;

import it.polimi.ingsw.controller.message.responses.PrivateResponse;
import it.polimi.ingsw.controller.message.responses.Response;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.utils.Color;
import it.polimi.ingsw.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Tournament class
 */
public class Tournament implements Serializable {

    private Model model;
    /**
     * Turn  contains the information about how
     * many actions are left
     */
    private Turn currentTurn;
    /**
     * Each turn start clockwise
     */

    private boolean counterClockwise = false;
    private int currentRoundPlayerNumber = 0;
    private List<Player> roundPlayers = new ArrayList<>();

    private List<Player> playerThatSkipTurn = new ArrayList<>();

    /**
     * Main constructor of the class Tournament
     *
     * @param model
     */
    public Tournament(Model model) {
        this.model = model;
    }

    /**
     * Method used at the beginning of the game to start the current game
     */
    public void startGame() {

        this.roundPlayers = model.getPlayers();
        StringBuilder sb = new StringBuilder();
        for (Player player : roundPlayers) {
            sb.append(player.getPlayerNickname()).append(",");
        }

        printNewRound();

        model.notifyViews(new Response(Utils.NEW_TURN_ORDER + sb.toString() + " ***"));


        model.notifyViews(new Response(Color.ANSI_YELLOW.escape() + "\n[Insert - help - to show a list of commands]\n" + Color.RESET));
        model.notifyViews(new Response("NOW it's " + model.getTournament().getRoundPlayers().get(model.getTournament().getCurrentRoundPlayerNumber()).getPlayerNickname() + " turn!"));
        model.notifyViews(new PrivateResponse(Utils.YOUR_TURN, roundPlayers.get(currentRoundPlayerNumber)));
        model.notifyViews(new Response(Utils.INSERT_COMMAND_COLOREDSTRING));

        model.getGameBoard().getDraftPool().draftDice();
        currentTurn = new Turn(model, model.getPlayers().get(model.getCurrentPlayerNumber()));

    }

    /**
     * Method used to create the next turn in a round
     */
    public void nextTurn() {


        Player player = roundPlayers.get(currentRoundPlayerNumber);


        if (roundPlayers.size() == 1) {
            if (!counterClockwise) {
                checkForSkip();
                counterClockwise = true;
            } else {
                model.getGameBoard().getRoundTrack().insertDiceInRoundtrack(model.getGameBoard().getDraftPool());
                model.getGameBoard().getRoundTrack().incrementRound();
                newRound();
                counterClockwise = false;
            }
            return;
        }


        //CLOCKWISE

        if (player == roundPlayers.get(0) && !counterClockwise) {
            currentRoundPlayerNumber += 1;
            checkForSkip();
        } else if (player != roundPlayers.get(0) && player != roundPlayers.get(model.getPlayers().size() - 1) && !counterClockwise) {

            currentRoundPlayerNumber += 1;
            checkForSkip();
        } else if (player == roundPlayers.get(model.getPlayers().size() - 1) && !counterClockwise) {

            counterClockwise = true;
            checkForSkip();
            printChangeWise();
        }


        //COUNTER-CLOCKWISE

        else if (player == roundPlayers.get(model.getPlayers().size() - 1) && counterClockwise) {

            currentRoundPlayerNumber -= 1;
            checkForSkip();
        } else if (player != roundPlayers.get(0) && player != roundPlayers.get(model.getPlayers().size() - 1) && counterClockwise) {

            currentRoundPlayerNumber -= 1;
            checkForSkip();
        } else if (player == roundPlayers.get(0) && counterClockwise) {


            counterClockwise = false;
            List<Player> old = roundPlayers;
            List<Player> newRoundList = new ArrayList<>();
            for (int i = getCurrentRoundPlayerNumber() + 1; i < model.getPlayers().size(); i++) {
                newRoundList.add(old.get(i));
            }
            if (newRoundList.size() != model.getPlayers().size()) {
                for (int j = 0; j < getCurrentRoundPlayerNumber() + 1; j++) {
                    newRoundList.add(old.get(j));
                }
            }
            roundPlayers = newRoundList;
            currentRoundPlayerNumber = 0;
            model.getGameBoard().getRoundTrack().insertDiceInRoundtrack(model.getGameBoard().getDraftPool());
            model.getGameBoard().getRoundTrack().incrementRound();


            if (model.getGameBoard().getRoundTrack().getNumberofRound() == Utils.MAX_ROUND + 1) {
                model.printFinalScore();
                model.notifyViews(new Response(printGameOverLogo()));

                System.exit(0);

            } else
                newRound();
        }
    }


    /**
     * Method used to create a new round
     */
    private void newRound() {


        StringBuilder sb = new StringBuilder();
        for (Player test : roundPlayers) {
            sb.append(test.getPlayerNickname()).append(",");
        }

        printNewRound();
        model.notifyViews(new Response(Utils.NEW_TURN_ORDER + sb.toString() + " ***"));
        model.getGameBoard().getDraftPool().draftDice();

        currentTurn = new Turn(model, roundPlayers.get(currentRoundPlayerNumber));

    }

    /**
     * Getter for the current round player number
     *
     * @return int currentRoundPlayerNumber: the current Player number turn
     */
    public int getCurrentRoundPlayerNumber() {
        return currentRoundPlayerNumber;
    }

    /**
     * Getter for the current list of player in the round
     *
     * @return int RoundPlayers: the Players currently playing
     */
    public List<Player> getRoundPlayers() {
        return roundPlayers;
    }

    /**
     * Getter for the current turn
     *
     * @return Turn currentTurn: the current turn we are in
     */
    public Turn getCurrentTurn() {
        return currentTurn;
    }

    /**
     * Method used to check if is Counter Clockwise
     *
     * @return boolean counterClockwise: check if it is the second part of the round (counterclockwise)
     */
    public boolean isCounterClockwise() {
        return counterClockwise;
    }

    /**
     * Method used to print the ascii text for NEW ROUND
     * Generated from: patorjk.com/software/taag/
     * Font: Calvin S
     */
    private void printNewRound() {

        model.notifyViews(new Response(
                Color.ANSI_RED.escape() + "\n╔╗╔╔═╗╦ ╦  ╦═╗╔═╗╦ ╦╔╗╔╔╦╗\n" +
                        Color.ANSI_RED.escape() + "║║║║╣ ║║║  ╠╦╝║ ║║ ║║║║ ║║\n" +
                        Color.ANSI_RED.escape() + "╝╚╝╚═╝╚╩╝  ╩╚═╚═╝╚═╝╝╚╝═╩╝\n" + Color.RESET));
        model.notifyViews(new Response("ROUND: " + (model.getGameBoard().getRoundTrack().getNumberofRound()) + Color.RESET));

    }

    /**
     * Method used to print the ascii text for CHANGE WISE
     * Generated from: patorjk.com/software/taag/
     * Font: Calvin S
     */
    private void printChangeWise() {
        model.notifyViews(new Response(
                Color.ANSI_YELLOW.escape() + "\n╔═╗╦ ╦╔═╗╔╗╔╔═╗╔═╗  ╦ ╦╦╔═╗╔═╗\n" +
                        Color.ANSI_YELLOW.escape() + "║  ╠═╣╠═╣║║║║ ╦║╣   ║║║║╚═╗║╣ \n" +
                        Color.ANSI_YELLOW.escape() + "╚═╝╩ ╩╩ ╩╝╚╝╚═╝╚═╝  ╚╩╝╩╚═╝╚═╝\n" + Color.RESET));

    }

    /**
     * @return List<Player> playerThatSkipTurn: the players that skip the current turn
     */
    public List<Player> getPlayerThatSkipTurn() {
        return playerThatSkipTurn;
    }

    /**
     * method used to verify that the next player should not skipping the turn
     */
    private void checkForSkip() {
        boolean isToSkip = false;
        Iterator<Player> iter = playerThatSkipTurn.iterator();
        while (iter.hasNext()) {
            Player skippingPlayer = iter.next();

            if (roundPlayers.get(currentRoundPlayerNumber).getPlayerNickname().equals(skippingPlayer.getPlayerNickname())) {
                iter.remove();
                isToSkip = true;
            }
        }

        if (isToSkip) {
            model.notifyViews(new Response("The player " + roundPlayers.get(currentRoundPlayerNumber).getPlayerNickname() + " is skipping the turn!"));
            nextTurn();
        } else {
            currentTurn = new Turn(model, roundPlayers.get(currentRoundPlayerNumber));
        }
    }

    /**
     * Method to return a string for final GameOver
     * Generated from: patorjk.com/software/taag/
     * Font: Delta Corps Priest 1
     *
     * @return a colored string with GameOver
     */
    public String printGameOverLogo() {
        return (
                Color.ANSI_YELLOW.escape() + "\n ██████╗  █████╗ ███╗   ███╗███████╗     ██████╗ ██╗   ██╗███████╗██████╗ \n" +
                        Color.ANSI_YELLOW.escape() + "██╔════╝ ██╔══██╗████╗ ████║██╔════╝    ██╔═══██╗██║   ██║██╔════╝██╔══██╗ \n" +
                        Color.ANSI_YELLOW.escape() + "██║  ███╗███████║██╔████╔██║█████╗      ██║   ██║██║   ██║█████╗  ██████╔╝\n" +
                        Color.ANSI_YELLOW.escape() + "██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      ██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗\n" +
                        Color.ANSI_YELLOW.escape() + "╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    ╚██████╔╝ ╚████╔╝ ███████╗██║  ██║\n" +
                        Color.ANSI_YELLOW.escape() + "╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝     ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝\n" + Color.RESET);


    }
}
