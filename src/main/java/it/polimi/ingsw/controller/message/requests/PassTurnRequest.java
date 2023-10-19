package it.polimi.ingsw.controller.message.requests;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.message.responses.PrivateResponse;
import it.polimi.ingsw.controller.message.responses.Response;
import it.polimi.ingsw.model.cards.toolcards.actions.PassTurnAction;
import it.polimi.ingsw.model.player.Player;

/**
 * Public class for pass turn request
 */
public class PassTurnRequest extends Request {

    /**
     * Main constructor of the pass turn request
     *
     * @param message: pass turn request message
     * @param player:  player whom requested it
     */
    public PassTurnRequest(String message, Player player) {
        super(message, player);
    }

    /**
     * Perform the pass turn action, if the current player is the one whom requested it
     *
     * @param controller: current controller used
     */
    @Override
    public void decrypt(Controller controller) {
        if (controller.checkIfCurrentPlayer(getPlayer())) {
            controller.getModel().notifyViews(new PrivateResponse("You passed the turn", getPlayer()));
            controller.getModel().notifyViews(new Response(getPlayer().getPlayerNickname() + " has passed the turn!"));
            PassTurnAction passAction = new PassTurnAction(controller.getModel());
            passAction.applyAction();
        } else {
            System.out.println("INVALID ACTION");
        }

    }
}

