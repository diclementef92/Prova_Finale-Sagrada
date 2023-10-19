package it.polimi.ingsw.model.cards.toolcards.actions;

import it.polimi.ingsw.controller.message.responses.PrivateResponse;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.utils.Utils;

public class PassTurnAction extends Action {

    public PassTurnAction(Model model) {
        super(model);
    }

    @Override
    public String getActionString() {
        return null;
    }

    @Override
    public void performRequest() {
    }


    @Override
    public boolean checkActionValidity() {
        return true;
    }

    @Override
    public void applyAction() {
        getModel().getTournament().nextTurn();

        Player currentPlayer = getModel().getTournament().getRoundPlayers().get(getModel().getTournament().getCurrentRoundPlayerNumber());

        for (Player player : getModel().getTournament().getRoundPlayers()) {
            if (player.getPlayerNickname().equals(currentPlayer.getPlayerNickname())) {
                getModel().notifyViews(new PrivateResponse(Utils.YOUR_TURN +"\n"+ Utils.INSERT_COMMAND_COLOREDSTRING, player));
            } else {
                getModel().notifyViews(new PrivateResponse("NOW it's " + currentPlayer.getPlayerNickname() + " turn", player));

            }
        }

    }


}
