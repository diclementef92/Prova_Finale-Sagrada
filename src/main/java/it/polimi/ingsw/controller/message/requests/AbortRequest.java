package it.polimi.ingsw.controller.message.requests;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.Turn;
import it.polimi.ingsw.controller.message.responses.PrivateResponse;
import it.polimi.ingsw.model.cards.toolcards.ToolCard;
import it.polimi.ingsw.model.cards.toolcards.actions.MoveDiceAction;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.utils.Utils;


/**
 * Class for the abortion of a request
 */
public class AbortRequest extends ActionRequest {

    /**
     * Main constructor of the abort request class
     *
     * @param message String of the abortion
     */
    public AbortRequest(String message, Player player) {
        super(message, player);
    }

    /**
     * Decrypt the message received, aborting the last action performed
     *
     * @param controller: current controller used
     */
    @Override
    public void decrypt(Controller controller) {
        ToolCard currentToolCard = controller.getModel().getTournament().getCurrentTurn().getCurrentToolCard();
        Turn currentTurn = controller.getModel().getTournament().getCurrentTurn();
        int currentActionStepCounter = currentTurn.getCurrentActionStepCounter();
        for (int i = currentActionStepCounter-1; i >= 0; i--) {
            if (currentToolCard.getActionToPerform().get(i).getActionString().equals(Utils.ACTION_MOVE_DICE)) {
                ((MoveDiceAction) currentToolCard.getActionToPerform().get(i)).rollback();
            }
        }
        currentTurn.resetTurn();

        for (Player p : controller.getModel().getTournament().getRoundPlayers()) {
            if (!p.getPlayerNickname().equals(getPlayer().getPlayerNickname())) {
                controller.getModel().notifyViews(new PrivateResponse(getPlayer().getPlayerNickname() + " has lost token and toolcard action!\n", p));
            } else {
                controller.getModel().notifyViews(new PrivateResponse(Utils.FAILED_TOOLCARD_ACTION, getPlayer()));
            }

        }
    }

}