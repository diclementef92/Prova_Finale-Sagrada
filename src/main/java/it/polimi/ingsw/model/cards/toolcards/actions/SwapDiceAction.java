package it.polimi.ingsw.model.cards.toolcards.actions;

import it.polimi.ingsw.controller.message.responses.PrivateResponse;
import it.polimi.ingsw.controller.message.responses.SwapDiceResponse;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.utils.Utils;


public class SwapDiceAction extends Action {

    private int draftpool;
    private int round;
    private int index;
    private String action;
    private String actionType;

    public SwapDiceAction(Model model, String action, String actionType) {
        super(model);
        this.action = action;
        this.actionType = actionType;
    }

    @Override
    public void performRequest() {
        getModel().notifyViews(new SwapDiceResponse(Utils.ACTION_SWAP_DICE, this.actionType, getModel().getTournament().getCurrentTurn().getDiceToBePlaced()));

    }

    @Override
    public String getActionString() {
        return action;
    }

    @Override
    public boolean checkActionValidity() {
        if (actionType.equals(Utils.ACTIONTYPE_SWAP_DICE_FROM_ROUNDTRACK) || (actionType.equals(Utils.ACTIONTYPE_SWAP_DICE_FROM_DICEBAG))) {
            return true;

        }
        return false;
    }

    @Override
    public void applyAction() {


        if (actionType.equals(Utils.ACTIONTYPE_SWAP_DICE_FROM_ROUNDTRACK)) {
            Dice draft = getModel().getGameBoard().getDraftPool().removeSelectedDice(draftpool);
            Dice toSwap = getModel().getGameBoard().getRoundTrack().getDicePerRound(round).remove(index);

            getModel().getGameBoard().getDraftPool().getListDraftPoolDice().add(toSwap);
            getModel().getGameBoard().getRoundTrack().getDicePerRound(round).add(draft);

        } else if (actionType.equals(Utils.ACTIONTYPE_SWAP_DICE_FROM_DICEBAG)) {

            Dice draft = getModel().getGameBoard().getDraftPool().removeSelectedDice(draftpool);
            Dice toSwap = getModel().getGameBoard().getDiceBag().removeDiceFromBag();

            getModel().getGameBoard().getDraftPool().getListDraftPoolDice().add(toSwap);
            getModel().getGameBoard().getDiceBag().returnDiceToDiceBag(draft);

        }

        Player currentPlayer = getModel().getTournament().getRoundPlayers().get(getModel().getTournament().getCurrentRoundPlayerNumber());
        getModel().notifyViews(new PrivateResponse("Dice moved correctly!\n", currentPlayer));

    }

    public void setDraftpool(int draftpool) {
        this.draftpool = draftpool;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setDiceIndex(int index) {
        this.index = index;
    }


}
