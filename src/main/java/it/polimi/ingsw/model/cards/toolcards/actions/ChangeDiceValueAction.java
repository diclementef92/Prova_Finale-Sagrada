package it.polimi.ingsw.model.cards.toolcards.actions;

import it.polimi.ingsw.controller.message.responses.ChangeDiceValueResponse;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.utils.Utils;


/**
 * Public class of the Change Dice Value action
 * It allow to change the value of a dice in game depending on some parameters
 */
public class ChangeDiceValueAction extends Action {


    private Dice selectedToChangeDice;

    private int valueOfChange;

    private Dice diceBeforeChanges;


    private String action;
    private String actionType;

    public ChangeDiceValueAction(Model model, String action, String actionType) {
        super(model);
        this.action = action;
        this.actionType = actionType;
    }

    @Override
    public void performRequest() {
        getModel().notifyViews(new ChangeDiceValueResponse(Utils.ACTION_CHANGE_DICE_VALUE, this.actionType, getModel().getTournament().getCurrentTurn().getDiceToBePlaced()));
    }

    @Override
    public String getActionString() {
        return action;
    }

    public boolean checkActionValidity() {

        return true;
    }

    /**
     * This method is used to send a messeage to the user with the specific request (which is a reposponse in our scheme
     * as it goes from server to client) for this action
     * The client will parse it and answer with a particular request
     */

    @Override
    public void applyAction() {
        if (this.actionType.equals(Utils.ACTIONTYPE_INCREASE_DICE_VALUE)) {
            for (Dice dice : getModel().getGameBoard().getDraftPool().getListDraftPoolDice()) {
                if (dice.getValue() == selectedToChangeDice.getValue()-valueOfChange &&
                        dice.getColor().equals(selectedToChangeDice.getColor())) {
                    dice.setValue(this.selectedToChangeDice.getValue());
                    break;
                }
            }
        }
        if (this.actionType.equals(Utils.ACTIONTYPE_FLIP_DICE)) {

            for (Dice dice : getModel().getGameBoard().getDraftPool().getListDraftPoolDice()){
                if (dice.getValue() == diceBeforeChanges.getValue() && dice.getColor().equals(diceBeforeChanges.getColor())){
                   dice.setValue(selectedToChangeDice.getValue());
                   break;
                }
            }
        }

        if (this.actionType.equals((Utils.ACTIONTYPE_REROLL_DICE))) {
            for (Dice dice : getModel().getGameBoard().getDraftPool().getListDraftPoolDice()){
                if (dice.getValue() == diceBeforeChanges.getValue() &&
                        dice.getColor().equals(diceBeforeChanges.getColor())){
                    dice.setValue(selectedToChangeDice.getValue());
                    break;
                }
            }
        }

        if (this.actionType.equals(Utils.ACTIONTYPE_SELECT_DICE_VALUE)) {
            this.selectedToChangeDice.setValue(valueOfChange);
        }


        if (this.actionType.equals((Utils.ACTIONTYPE_REROLL_ALL))) {
            for (Dice d : getModel().getGameBoard().getDraftPool().getListDraftPoolDice()) {
                d.rollDice();
            }
        }

    }

    public void setSelectedToChangeDice(Dice selectedToChangeDice) {
        this.selectedToChangeDice = selectedToChangeDice;
    }

    public void setDiceBeforeChanges(Dice diceBeforeChanges){
        this.diceBeforeChanges = diceBeforeChanges;
    }

    public Dice getDiceBeforeChanges() {
        return diceBeforeChanges;
    }

    public void setValueOfChange(int valueOfChange) {
        this.valueOfChange = valueOfChange;
        if (selectedToChangeDice != null) {
            selectedToChangeDice.setValue(selectedToChangeDice.getValue() + valueOfChange);
        }
    }



}
