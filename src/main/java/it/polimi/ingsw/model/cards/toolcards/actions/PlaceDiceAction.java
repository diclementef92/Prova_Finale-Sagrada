package it.polimi.ingsw.model.cards.toolcards.actions;

import it.polimi.ingsw.controller.message.responses.PlaceDiceResponse;
import it.polimi.ingsw.controller.message.responses.PrivateResponse;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerBoard;
import it.polimi.ingsw.utils.Utils;

import static it.polimi.ingsw.utils.Utils.NUMBER_DICE_SHADES;
import static it.polimi.ingsw.utils.Utils.PLACE_DICE_ACTION_COMPLETED;

public class PlaceDiceAction extends Action {


    private Dice dice;
    private int row;
    private int col;
    private String action;
    private String actionType;

    public PlaceDiceAction(Model model, Dice dice, int row, int col) {
        super(model);
        this.dice = dice;
        this.row = row;
        this.col = col;
    }

    public boolean checkMainActionValidity() {
        Player currentPlayer = getModel().getTournament().getRoundPlayers().get(getModel().getTournament().getCurrentRoundPlayerNumber());
        PlayerBoard playerBoard = currentPlayer.getPlayerBoard();

        return playerBoard.checkValidityPlacement(dice, row, col, -3, -3);

    }

    //costruttore per le toolcard
    public PlaceDiceAction(Model model, String action, String actionType) {
        super(model);
        this.action = action;
        this.actionType = actionType;

    }

    @Override
    public void performRequest() {
        getModel().notifyViews(new PlaceDiceResponse(Utils.ACTION_PLACE_DICE, this.actionType, getModel().getTournament().getCurrentTurn().getDiceToBePlaced()));

    }

    @Override
    public String getActionString() {
        return action;
    }

    @Override
    public boolean checkActionValidity() {


        Player currentPlayer = getModel().getTournament().getRoundPlayers().get(getModel().getTournament().getCurrentRoundPlayerNumber());
        PlayerBoard playerBoard = currentPlayer.getPlayerBoard();

        if (getModel().getTournament().getCurrentTurn().getSelectDiceActionCounter() != 0) {


            if (actionType.equals(Utils.ACTIONTYPE_PLACE_DICE_WITH_ALL_RESTRICTIONS)) {
                return playerBoard.checkValidityPlacement(dice, row, col, -3, -3);
            }

            if (actionType.equals(Utils.ACTIONTYPE_PLACE_DICE_IGNORE_ADJACENT)) {

                if (playerBoard.checkIfCellIsEmpty(row, col)) {
                    if ((playerBoard.checkColorValidity(dice, row, col) && playerBoard.checkValueValidity(dice, row, col))
                            || playerBoard.checkIfWhiteCell(dice, row, col)) {
                        return !playerBoard.checkIfDicesAround(row, col, -3, -3);
                    }
                }

            } else {
                getModel().notifyViews(new PrivateResponse(Utils.NO_MORE_DICE_PLACEMENT, currentPlayer));
                return false;
            }

        }
        return false;


    }

    /**
     * Se è arrivato fin qui il player è quello corrente, quindi non bisogna
     * piu fare quel controllo
     */
    @Override
    public void applyAction() {
        Player currentPlayer = getModel().getTournament().getRoundPlayers().get(getModel().getTournament().getCurrentRoundPlayerNumber());

        if (actionType.equals(Utils.ACTIONTYPE_PLACE_DICE_WITH_ALL_RESTRICTIONS) || actionType.equals(Utils.ACTIONTYPE_PLACE_DICE_IGNORE_ADJACENT)) {
            Dice toPlace = getModel().getTournament().getCurrentTurn().getDiceToBePlaced();
            currentPlayer.getPlayerBoard().placeDice(toPlace, row, col);

            getModel().getTournament().getCurrentTurn().setSelectDiceActionCounter(getModel().getTournament().getCurrentTurn().getSelectDiceActionCounter() - 1);

            for (Dice insideDice : getModel().getGameBoard().getDraftPool().getListDraftPoolDice()) {
                if (getModel().getTournament().getCurrentTurn().getCurrentToolCard().getActionToPerform().size() == 3) {
                    if (insideDice.getValue() == NUMBER_DICE_SHADES && insideDice.getColor().equals(toPlace.getColor())) {
                        getModel().getGameBoard().getDraftPool().getListDraftPoolDice().remove(insideDice);
                        break;

                    }
                } else if (toPlace.getValue() == insideDice.getValue() && toPlace.getColor().equals(insideDice.getColor())) {
                    getModel().getGameBoard().getDraftPool().getListDraftPoolDice().remove(insideDice);
                    break;
                }
            }

            for (Player player : getModel().getTournament().getRoundPlayers()) {
                if (player.getPlayerNickname().equals(currentPlayer.getPlayerNickname())) {
                    getModel().notifyViews(new PrivateResponse(PLACE_DICE_ACTION_COMPLETED, player));
                } else {
                    getModel().notifyViews(new PrivateResponse(currentPlayer.getPlayerNickname() + " has placed a dice", player));
                }
            }
        }
    }


    /**
     * Setter of the dice to be placed
     *
     * @param dice
     */
    public void setDice(Dice dice) {
        this.dice = dice;
    }

    /**
     * Setter of the row
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Setter of the col
     * @param col
     */
    public void setCol(int col) {
        this.col = col;
    }
}