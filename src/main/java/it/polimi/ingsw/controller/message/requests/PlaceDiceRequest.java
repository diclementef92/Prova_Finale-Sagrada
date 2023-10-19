package it.polimi.ingsw.controller.message.requests;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.Turn;
import it.polimi.ingsw.controller.message.responses.PrivateResponse;
import it.polimi.ingsw.controller.message.responses.Response;
import it.polimi.ingsw.model.cards.toolcards.actions.Action;
import it.polimi.ingsw.model.cards.toolcards.actions.PlaceDiceAction;
import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.utils.Utils;

import static it.polimi.ingsw.utils.Utils.FAILED_PLACE_DICE_ACTION;
import static it.polimi.ingsw.utils.Utils.NOT_YOUR_TURN;
import static it.polimi.ingsw.utils.Utils.PLACE_DICE_ACTION_COMPLETED;

/**
 * Public class for place dice request
 */
public class PlaceDiceRequest extends ActionRequest {


    private Dice dice;
    private int row;
    private int col;

    /**
     * Main constructor of the place dice request
     *
     * @param message: place die request message
     * @param dice:    selected die
     * @param row:     player's row where die is
     * @param col:     player's column where die is
     * @param player:  player whom requested it
     */
    public PlaceDiceRequest(String message, Dice dice, int row, int col, Player player) {
        super(message, player);
        this.dice = dice;
        this.col = col;
        this.row = row;
    }

    /**
     * Decrypt the message received, checking if the content requests a place dice action. It checks if the request was sent by a toolcard or by the player, and performs the action
     *
     * @param controller: current controller used
     */
    @Override
    public void decrypt(Controller controller) {

        if (getMessageContent().equals(Utils.ACTIONTYPE_PLACE_DICE_WITH_ALL_RESTRICTIONS)) {

            Turn turn = controller.getModel().getTournament().getCurrentTurn();
            Action action = (turn.getCurrentToolCard().getActionToPerform().get(turn.getCurrentActionStepCounter()));
            ((PlaceDiceAction) action).setDice(dice);
            ((PlaceDiceAction) action).setRow(this.row);
            ((PlaceDiceAction) action).setCol(this.col);
            turn.increaseCurrentActionStepCounter();
            turn.callNextToolCardAction();

        } else if (getMessageContent().equals(Utils.ACTIONTYPE_PLACE_DICE_IGNORE_ADJACENT)) {

            Turn turn = controller.getModel().getTournament().getCurrentTurn();
            Action action = (turn.getCurrentToolCard().getActionToPerform().get(turn.getCurrentActionStepCounter()));
            turn.setDiceToBePlaced(dice);
            ((PlaceDiceAction) action).setDice(dice);
            ((PlaceDiceAction) action).setRow(this.row);
            ((PlaceDiceAction) action).setCol(this.col);
            turn.increaseCurrentActionStepCounter();
            turn.callNextToolCardAction();


        } else {
            if (controller.checkIfCurrentPlayer(getPlayer())) {
                Player currentPlayer = controller.getModel().getTournament().getRoundPlayers().get(controller.getModel().getTournament().getCurrentRoundPlayerNumber());
                PlaceDiceAction placeDiceAction = new PlaceDiceAction(controller.getModel(), dice, row, col);

                if (placeDiceAction.checkMainActionValidity()) {
                    currentPlayer.getPlayerBoard().placeDice(dice, row, col);

                    controller.getModel().getTournament().getCurrentTurn().setSelectDiceActionCounter(controller.getModel().getTournament().getCurrentTurn().getSelectDiceActionCounter() - 1);
                    //RIMUOVO IL DADO UTILIZZATO DALLA DRAFTPOOL
                    for (Dice insideDice : controller.getModel().getGameBoard().getDraftPool().getListDraftPoolDice()) {
                        if (dice.getValue() == insideDice.getValue() && dice.getColor().equals(insideDice.getColor())) {
                            controller.getModel().getGameBoard().getDraftPool().getListDraftPoolDice().remove(insideDice);
                            break;
                        }
                    }

                    //NOTIFICHE PER I VARI PLAYER
                    for (Player player : controller.getModel().getTournament().getRoundPlayers()) {
                        if (player.getPlayerNickname().equals(currentPlayer.getPlayerNickname())) {
                            controller.getModel().notifyViews(new PrivateResponse(PLACE_DICE_ACTION_COMPLETED, player));
                        } else {
                            controller.getModel().notifyViews(new PrivateResponse(currentPlayer.getPlayerNickname() + " has placed a dice!", player));
                        }
                    }

                } else
                    controller.getModel().notifyViews(new PrivateResponse(FAILED_PLACE_DICE_ACTION, currentPlayer));
            } else {
                controller.getModel().notifyViews(new Response(NOT_YOUR_TURN));
            }


        }
    }

}
