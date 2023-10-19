package it.polimi.ingsw.controller;

import it.polimi.ingsw.controller.message.responses.PrivateResponse;
import it.polimi.ingsw.controller.message.responses.Response;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.cards.toolcards.ToolCard;
import it.polimi.ingsw.model.cards.toolcards.actions.Action;
import it.polimi.ingsw.model.cards.toolcards.actions.MoveDiceAction;
import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;

import static it.polimi.ingsw.utils.Utils.ACTION_MOVE_DICE;
import static it.polimi.ingsw.utils.Utils.MAX_DICE_FOR_TURN;
import static it.polimi.ingsw.utils.Utils.MAX_USETOOLCARD_FOR_TURN;

/**
 * Turn class
 */
public class Turn implements Serializable {


    private Dice diceToBePlaced = null;
    private Dice diceForSinglePlayer = null;
    private int selectDiceActionCounter;
    private int useToolCardActionsCounter;

    private ToolCard currentToolCard;
    private int actionsToPerform = 0;
    private int currentActionStepCounter = 0;

    private Model model;
    private Player player;

    static final String NOW_TOKEN_AMOUNT = "Now your token amount is: ";
    static final String USED_TOOLCARD = " has used a toolcard!";


    /**
     * Main constructor of the Turn class
     * It contains the information about how many actions are left for
     * the player passed as param
     *
     * @param model  model of the current game
     * @param player the player passed as param
     */
    public Turn(Model model, Player player) {


        this.selectDiceActionCounter = MAX_DICE_FOR_TURN;
        this.useToolCardActionsCounter = MAX_USETOOLCARD_FOR_TURN;
        this.model = model;
        this.player = player;

    }

    /**
     * Getter for the number of dice action
     *
     * @return an integer of dice action
     */
    public int getSelectDiceActionCounter() {
        return selectDiceActionCounter;
    }

    /**
     * Getter for the number of toolcard action
     *
     * @return an integer of toolcard action
     */
    public int getUseToolCardActionsCounter() {
        return useToolCardActionsCounter;
    }

    /**
     * Setter for the number of dice action
     *
     * @param selectDiceActionCounter
     */
    public void setSelectDiceActionCounter(int selectDiceActionCounter) {
        this.selectDiceActionCounter = selectDiceActionCounter;
    }

    /**
     * Setter for the number of toolcard action
     *
     * @param useToolCardActionsCounter
     */
    public void setUseToolCardActionsCounter(int useToolCardActionsCounter) {
        this.useToolCardActionsCounter = useToolCardActionsCounter;
    }

    /**
     * Getter for the current tool card used by the player in this turn
     *
     * @return a ToolCard
     */
    public ToolCard getCurrentToolCard() {
        return currentToolCard;
    }

    /**
     * Setter for the ToolCard used in this turn
     * If a player request the usage of a ToolCard, that one is set inside the turn with this method
     *
     * @param currentToolCard
     */
    public void setCurrentToolCard(ToolCard currentToolCard) {
        this.currentToolCard = currentToolCard;
        if (currentToolCard != null) {
            actionsToPerform = currentToolCard.getActionToPerform().size();
            callNextToolCardAction();
        }
    }

    /**
     * Method used to call the next toolcard action
     */
    public void callNextToolCardAction() {

        Player currentPlayer = model.getTournament().getRoundPlayers().get(model.getTournament().getCurrentRoundPlayerNumber());

        if (currentActionStepCounter != actionsToPerform) {
            currentToolCard.getActionToPerform().get(currentActionStepCounter).performRequest();
        } else {
            boolean finalCheck = true;
            for (Action actions : currentToolCard.getActionToPerform()) {
                finalCheck = finalCheck && actions.checkActionValidity();
            }
            if (finalCheck) {
                for (Action action : currentToolCard.getActionToPerform()) {
                    action.applyAction();
                }

                for (Player p : model.getTournament().getRoundPlayers()) {
                    if (p.getPlayerNickname().equals(player.getPlayerNickname())) {
                        model.notifyViews(new PrivateResponse(Utils.TOOLCARD_ACTION_COMPLETED, p));
                    } else {
                        model.notifyViews(new PrivateResponse(currentPlayer.getPlayerNickname() + USED_TOOLCARD, p));
                    }
                }

                resetTurn();


            } else {
                if (actionsToPerform > 1) {
                    for (int i = currentActionStepCounter - 1; i >= 0; i--) {
                        if (currentToolCard.getActionToPerform().get(i).getActionString().equals(ACTION_MOVE_DICE)) {
                            ((MoveDiceAction) currentToolCard.getActionToPerform().get(i)).rollback();
                        }
                    }
                }
                resetTurn();
                model.notifyViews(new PrivateResponse(Utils.FAILED_TOOLCARD_ACTION, player));

            }

        }

        //MULTIPLAYER
        //if (model.getPlayers().size() != 1) {
        //aggiungere la rimozione dei token



         /*

        } else {
            //SINGLE PLAYER
            model.getGameBoard().getToolcardsInGame().remove(currentToolCard);
            model.getGameBoard().getDraftPool().getListDraftPoolDice().remove(diceForSinglePlayer);
            currentToolCard = null;
            currentActionStepCounter = 0;
            actionsToPerform = 0;
            diceToBePlaced = null;
            useToolCardActionsCounter -= 1;
        }
        */
        //
    }


    /**
     * Method used to increase Current Action Step Counter
     */
    public void increaseCurrentActionStepCounter() {
        currentActionStepCounter++;
    }

    /**
     * Getter for the current action step counter
     *
     * @return an integer of Current Action Step Counter
     */
    public int getCurrentActionStepCounter() {
        return currentActionStepCounter;
    }

    /**
     * Setter for the current action step counter
     *
     * @param currentActionStepCounter
     */
    public void setCurrentActionStepCounter(int currentActionStepCounter) {
        this.currentActionStepCounter = currentActionStepCounter;
    }

    /**
     * Setter for actions to perform
     * @param actionsToPerform
     */
    public void setActionsToPerform(int actionsToPerform) {
        this.actionsToPerform = actionsToPerform;
    }


    /**
     * Getter for the dice to be placed (used for toolcards actions)
     * @return the dice that must be placed in the current round
     */
    public Dice getDiceToBePlaced() {
        return diceToBePlaced;
    }

    /**
     * Setter for the dice to be placed (used for toolcards actions)
     * @param diceToBePlaced
     */
    public void setDiceToBePlaced(Dice diceToBePlaced) {
        this.diceToBePlaced = diceToBePlaced;
    }

    /**
     *
     * @return
     */
    public Dice getDiceForSinglePlayer() {
        return diceForSinglePlayer;
    }

    /**
     * @param diceForSinglePlayer
     */
    public void setDiceForSinglePlayer(Dice diceForSinglePlayer) {
        this.diceForSinglePlayer = diceForSinglePlayer;
    }

    /**
     * Method used to reset turn
     */
    public void resetTurn() {
        removeToken();

        currentToolCard.setMoveDiceFinalPositions(new ArrayList<>());
        currentToolCard.setMoveDiceStartingPositions(new ArrayList<>());

        currentToolCard = null;
        diceToBePlaced = null;
        useToolCardActionsCounter -= 1;
        actionsToPerform = 0;
        currentActionStepCounter = 0;
    }

    /**
     * Method used to remove the token after the toolcard action
     */
    private void removeToken() {

        if (currentToolCard.getFavorToken() == 0) {

            for (Player p : model.getTournament().getRoundPlayers()) {
                if (p.getPlayerNickname().equals(model.getTournament().getRoundPlayers().get(
                        model.getTournament().getCurrentRoundPlayerNumber()
                ).getPlayerNickname())) {
                    p.setFavorTokenAmount(p.getFavorTokenAmount() - Utils.TOKEN_NEEDED_IF_EMPTY);
                    model.notifyViews(new PrivateResponse(NOW_TOKEN_AMOUNT + p.getFavorTokenAmount(), p));
                }
            }

            currentToolCard.setFavorToken(currentToolCard.getFavorToken() + Utils.TOKEN_NEEDED_IF_EMPTY);

        } else {
            for (Player p : model.getTournament().getRoundPlayers()) {
                if (p.getPlayerNickname().equals(model.getTournament().getRoundPlayers().get(
                        model.getTournament().getCurrentRoundPlayerNumber()
                ).getPlayerNickname())) {
                    p.setFavorTokenAmount(p.getFavorTokenAmount() - Utils.TOKEN_NEEDED_IF_NOT_EMPTY);
                    model.notifyViews(new PrivateResponse(NOW_TOKEN_AMOUNT + p.getFavorTokenAmount(), p));
                }
            }
            currentToolCard.setFavorToken(currentToolCard.getFavorToken() + Utils.TOKEN_NEEDED_IF_NOT_EMPTY);
        }

    }


    /**
     * Getter of the number of actions to perform in this toolcard
     *
     * @return an integer
     */
    public int getActionsToPerform() {
        return actionsToPerform;
    }


}