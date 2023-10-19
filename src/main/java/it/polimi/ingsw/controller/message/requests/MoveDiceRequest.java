package it.polimi.ingsw.controller.message.requests;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.Turn;
import it.polimi.ingsw.model.cards.toolcards.actions.Action;
import it.polimi.ingsw.model.cards.toolcards.actions.MoveDiceAction;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.utils.Utils;


/**
 * Public class for MoveDiceRequest
 */
public class MoveDiceRequest extends ActionRequest {

    private int startRow;
    private int startCol;
    private int finalRow;
    private int finalCol;
    private int another;

    /**
     * Main constructor of the move dice request
     *
     * @param message:  move dice request message
     * @param startRow: first row to select
     * @param startCol: first column to select
     * @param finalRow: last row to select
     * @param finalCol: last columns to select
     * @param player:   player whom requested it
     */
    public MoveDiceRequest(String message, int startRow, int startCol, int finalRow, int finalCol, int another, Player player) {
        super(message, player);
        this.startRow = startRow;
        this.startCol = startCol;
        this.finalCol = finalCol;
        this.finalRow = finalRow;
        this.another = another;
    }

    /**
     * Decrypt the message received, checking if the content requests a move dice action, and performs it
     *
     * @param controller: current controller used
     */
    @Override
    public void decrypt(Controller controller) {
        Turn turn = controller.getModel().getTournament().getCurrentTurn();
        Action action = (turn.getCurrentToolCard().getActionToPerform().get(turn.getCurrentActionStepCounter()));

        ((MoveDiceAction) action).setFinalCol(this.finalCol);
        ((MoveDiceAction) action).setFinalRow(this.finalRow);
        ((MoveDiceAction) action).setStartCol(this.startCol);
        ((MoveDiceAction) action).setStartRow(this.startRow);

         if (getMessageContent().equals(Utils.ACTIONTYPE_MOVE_MATCHING_ROUNDTRACKDICE)) {
            if (this.another == 1) {
                controller.getModel().getTournament().getCurrentTurn().getCurrentToolCard().setAnotherMoveDice(1);
                controller.getModel().getTournament().getCurrentTurn().getCurrentToolCard().getActionToPerform().remove(
                        controller.getModel().getTournament().getCurrentTurn().getCurrentToolCard().getActionToPerform().size() - 1
                );
                System.out.println(controller.getModel().getTournament().getCurrentTurn().getCurrentToolCard().getActionToPerform().size());
                controller.getModel().getTournament().getCurrentTurn().setActionsToPerform(
                        controller.getModel().getTournament().getCurrentTurn().getActionsToPerform() - 1
                );
            }
            if (controller.getModel().getTournament().getCurrentTurn().getCurrentToolCard().getAnotherMoveDice() == -1 &&
                    this.another == 0) {
                controller.getModel().getTournament().getCurrentTurn().getCurrentToolCard().setAnotherMoveDice(0);
            }
        }


        turn.increaseCurrentActionStepCounter();
        turn.callNextToolCardAction();

    }
}
