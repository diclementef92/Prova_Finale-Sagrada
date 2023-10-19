package it.polimi.ingsw.controller.message.requests;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.message.responses.PrivateResponse;
import it.polimi.ingsw.controller.message.responses.Response;
import it.polimi.ingsw.model.cards.toolcards.ToolCard;
import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.utils.Utils;

/**
 * Public class for toolcard usage request
 */
public class ToolcardUsageRequest extends ActionRequest {

    private int requestedCardIndex;
    private ToolCard requestedToolCard;

    /**
     *
     * Main Constructior of the class Toolcard Request
     *
     * @param message: toolcard usage request message
     * @param requestedCardIndex: requested toolcard index
     * @param player: player whom requested it
     */
    public ToolcardUsageRequest(String message, int requestedCardIndex, Player player) {
        super(message, player);
        this.requestedCardIndex = requestedCardIndex;
    }


    /**
     *
     * Decrypt the message received (for toolcard usage), checking with model and controller if its usage is valid or not
     *
     * @param controller: current controller used
     */

    @Override
    public void decrypt(Controller controller) {

        Player currentPlayer = controller.getModel().getTournament().getRoundPlayers().get(controller.getModel().getTournament().getCurrentRoundPlayerNumber());
        int toolcardsInGame = controller.getModel().getGameBoard().getToolcardsInGame().size();

        if (controller.checkIfCurrentPlayer(currentPlayer)) {
            if (!(requestedCardIndex < 0 || requestedCardIndex >= toolcardsInGame)) {
                requestedToolCard = (ToolCard) controller.getModel().getGameBoard().getToolcardsInGame().get(requestedCardIndex);

                if (controller.getModel().getPlayers().size() == 1) {
                    //singleplayer
                        controller.getModel().getTournament().getCurrentTurn().setCurrentToolCard(requestedToolCard);
                    //multiplayer
                } else {
                    if (currentPlayer.getFavorTokenAmount() >= requestedToolCard.getFavorTokenNeeded()) {
                        controller.getModel().getTournament().getCurrentTurn().setCurrentToolCard(requestedToolCard);
                    } else {
                        controller.getModel().notifyViews(new PrivateResponse(Utils.NOT_ENOUGH_TOKEN,currentPlayer));
                    }
                }


            } else {
                controller.getModel().notifyViews(new PrivateResponse(Utils.INVALID_CARD_SELECTED,currentPlayer));
            }
        } else {
            controller.getModel().notifyViews(new Response(Utils.NOT_YOUR_TURN));
        }

    }
}
