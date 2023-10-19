package it.polimi.ingsw.controller.message.requests;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.Turn;
import it.polimi.ingsw.model.cards.toolcards.actions.Action;
import it.polimi.ingsw.model.cards.toolcards.actions.SkipTurnAction;
import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.utils.Utils;

/**
 * Public class for skip turn request
 */
public class SkipTurnRequest extends ActionRequest {

    private boolean counterClockwise;
    private int row;
    private int col;
    private Dice dice;


    /**
     *
     * Main constructor of the skip turn request
     *
     * @param message: skip turn request message
     * @param player: player whom requested it
     * @param dice: selected die
     * @param row: player's row where die is
     * @param col: player's column where die is
     * @param counterClockwise: check if round is in the counterclockwise phase
     */
    public SkipTurnRequest(String message, Player player, Dice dice, int row, int col, boolean counterClockwise) {
        super(message, player);
        this.dice = dice;
        this.row = row;
        this.col = col;
        this.counterClockwise = counterClockwise;
    }

    /**
     *
     * Decrypt the message received, checking if the content requests a skip turn action, and performing it
     *
     * @param controller: current controller used
     */
    @Override
    public void decrypt(Controller controller) {

        if (getMessageContent().equals(Utils.ACTIONTYPE_SKIP_NEXT_TURN)) {

            Turn turn = controller.getModel().getTournament().getCurrentTurn();
            Action action = (turn.getCurrentToolCard().getActionToPerform().get(turn.getCurrentActionStepCounter()));
            turn.setDiceToBePlaced(dice);

            ((SkipTurnAction) action).setCol(this.col);
            ((SkipTurnAction) action).setRow(this.row);

            turn.increaseCurrentActionStepCounter();
            turn.callNextToolCardAction();

        }

    }
}
