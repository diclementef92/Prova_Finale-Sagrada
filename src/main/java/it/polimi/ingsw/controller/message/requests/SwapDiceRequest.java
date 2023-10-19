package it.polimi.ingsw.controller.message.requests;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.Turn;
import it.polimi.ingsw.model.cards.toolcards.actions.Action;
import it.polimi.ingsw.model.cards.toolcards.actions.SwapDiceAction;
import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.utils.Utils;

/**
 * public class for swap dice request
 */
public class SwapDiceRequest extends ActionRequest {

    private int draftpool;
    private int round;
    private int index;

    /**
     * Main constructor of the class swap dice request
     *
     * @param message: swap dice request message
     * @param draftpool: current draftpool
     * @param round: current round
     * @param index: die index
     * @param player: player whom requested it
     */
    public SwapDiceRequest(String message, int draftpool, int round, int index, Player player) {
        super(message, player);
        this.draftpool = draftpool;
        this.round = round;
        this.index = index;
    }

    /**
     *
     * Decrypt the message received, checking if the content requests a swap dice action, and performing it
     *
     * @param controller: current controller used
     */
    @Override
    public void decrypt(Controller controller) {

        if (getMessageContent().equals(Utils.ACTIONTYPE_SWAP_DICE_FROM_ROUNDTRACK)) {

            controller.getModel().getTournament().getCurrentTurn().setDiceToBePlaced(controller.getModel().getGameBoard().getRoundTrack().getDicePerRound(round).get(index));

            Turn turn = controller.getModel().getTournament().getCurrentTurn();
            Action action = (turn.getCurrentToolCard().getActionToPerform().get(turn.getCurrentActionStepCounter()));
            ((SwapDiceAction) action).setDraftpool(this.draftpool);
            ((SwapDiceAction) action).setRound(this.round);
            ((SwapDiceAction) action).setDiceIndex(this.index);
            turn.increaseCurrentActionStepCounter();
            turn.callNextToolCardAction();

        }

        else if (getMessageContent().equals(Utils.ACTIONTYPE_SWAP_DICE_FROM_DICEBAG)) {

            Dice toMove = controller.getModel().getGameBoard().getDiceBag().getFirstDice();
            toMove.setValue(Utils.NUMBER_DICE_SHADES);

            controller.getModel().getTournament().getCurrentTurn().setDiceToBePlaced(toMove);

            Turn turn = controller.getModel().getTournament().getCurrentTurn();
            Action action = (turn.getCurrentToolCard().getActionToPerform().get(turn.getCurrentActionStepCounter()));
            ((SwapDiceAction) action).setDraftpool(this.draftpool);
            ((SwapDiceAction) action).setDiceIndex(this.index);
            turn.increaseCurrentActionStepCounter();
            turn.callNextToolCardAction();

        }
    }
}
