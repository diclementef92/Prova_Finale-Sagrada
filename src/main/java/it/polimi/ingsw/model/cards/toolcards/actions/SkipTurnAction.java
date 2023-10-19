package it.polimi.ingsw.model.cards.toolcards.actions;

import it.polimi.ingsw.controller.message.responses.PrivateResponse;
import it.polimi.ingsw.controller.message.responses.SkipTurnResponse;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerBoard;
import it.polimi.ingsw.utils.Utils;

public class SkipTurnAction extends Action {

    private String action;
    private String actionType;
    private int row;
    private int col;


    public SkipTurnAction(Model model, String action, String actionType) {
        super(model);
        this.action = action;
        this.actionType = actionType;

    }

    @Override
    public void performRequest() {
        getModel().notifyViews(new SkipTurnResponse(Utils.ACTION_SKIP_TURN, this.actionType, getModel().getTournament().getCurrentTurn().getDiceToBePlaced()));
    }

    @Override
    public String getActionString() {
        return action;
    }

    @Override
    public boolean checkActionValidity() {

        Player currentPlayer = getModel().getTournament().getRoundPlayers().get(getModel().getTournament().getCurrentRoundPlayerNumber());
        PlayerBoard playerBoard = currentPlayer.getPlayerBoard();
        Dice toPlace = getModel().getTournament().getCurrentTurn().getDiceToBePlaced();

        if (actionType.equals(Utils.ACTIONTYPE_SKIP_NEXT_TURN)) {
            return playerBoard.checkValidityPlacement(toPlace, row, col, -3, -3);
        }


        return true;
    }

    @Override
    public void applyAction() {


        Player currentPlayer = getModel().getTournament().getRoundPlayers().get(getModel().getTournament().getCurrentRoundPlayerNumber());

        if (actionType.equals(Utils.ACTIONTYPE_SKIP_NEXT_TURN)) {
            Dice toPlace = getModel().getTournament().getCurrentTurn().getDiceToBePlaced();
            currentPlayer.getPlayerBoard().placeDice(toPlace, row, col);
            getModel().getTournament().getPlayerThatSkipTurn().add(currentPlayer);

            System.out.println("salta turno!!!");
            //getModel().getTournament().getCurrentTurn().setSelectDiceActionCounter(getModel().getTournament().getCurrentTurn().getSelectDiceActionCounter() - 1);

            //RIMUOVO IL DADO UTILIZZATO DALLA DRAFTPOOL
            for (Dice insideDice : getModel().getGameBoard().getDraftPool().getListDraftPoolDice()) {
                if (toPlace.getValue() == insideDice.getValue() && toPlace.getColor().equals(insideDice.getColor())) {
                    getModel().getGameBoard().getDraftPool().getListDraftPoolDice().remove(insideDice);
                    break;
                }
            }
            getModel().notifyViews(new PrivateResponse(Utils.PLACE_DICE_ACTION_COMPLETED, currentPlayer));
        }


    }


    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }


}
