package it.polimi.ingsw.controller.message.requests;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.Turn;
import it.polimi.ingsw.model.cards.toolcards.actions.Action;
import it.polimi.ingsw.model.cards.toolcards.actions.ChangeDiceValueAction;
import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.utils.Utils;

/**
 * Public class for change dice value request
 */
public class ChangeDiceValueRequest extends ActionRequest {


    private Dice diceToBeChanged;
    private int value;

    /**
     * Main constructor of the class change dice value request
     *
     * @param message type of change of value
     * @param dice    Dice to be changed
     * @param value   Value of the dice
     */
    public ChangeDiceValueRequest(String message, Dice dice, int value, Player player) {
        super(message, player);
        this.diceToBeChanged = dice;
        this.value = value;
    }

    /**
     *
     * Decrypt the message received, checking which toolcard requested it, and it performs the change dice value action
     *
     * @param controller: current controller used
     */
    @Override
    public void decrypt(Controller controller) {
        controller.getModel().getTournament().getCurrentTurn().setDiceToBePlaced(diceToBeChanged);

        if (getMessageContent().equals(Utils.ACTIONTYPE_REROLL_ALL)) {
            Turn turn = controller.getModel().getTournament().getCurrentTurn();
            Action action = (turn.getCurrentToolCard().getActionToPerform().get(turn.getCurrentActionStepCounter()));
            ((ChangeDiceValueAction) action).setSelectedToChangeDice(null);
            ((ChangeDiceValueAction) action).setValueOfChange(0);
            turn.increaseCurrentActionStepCounter();
            turn.callNextToolCardAction();


        } else if (getMessageContent().equals(Utils.ACTIONTYPE_INCREASE_DICE_VALUE) || getMessageContent().equals(Utils.ACTIONTYPE_SELECT_DICE_VALUE)) {
            Turn turn = controller.getModel().getTournament().getCurrentTurn();
            Action action = (turn.getCurrentToolCard().getActionToPerform().get(turn.getCurrentActionStepCounter()));
            ((ChangeDiceValueAction) action).setSelectedToChangeDice(diceToBeChanged);
            ((ChangeDiceValueAction) action).setValueOfChange(value);
            turn.increaseCurrentActionStepCounter();
            turn.callNextToolCardAction();

        } else if (getMessageContent().equals(Utils.ACTIONTYPE_FLIP_DICE)) {
            Turn turn = controller.getModel().getTournament().getCurrentTurn();
            Action action = (turn.getCurrentToolCard().getActionToPerform().get(turn.getCurrentActionStepCounter()));
            Dice temp = new Dice(diceToBeChanged.getColor());
            temp.setValue(diceToBeChanged.getValue());
            ((ChangeDiceValueAction) action).setDiceBeforeChanges(temp);
            diceToBeChanged.setValue(7 - diceToBeChanged.getValue());
            ((ChangeDiceValueAction) action).setSelectedToChangeDice(diceToBeChanged);
            turn.increaseCurrentActionStepCounter();
            turn.callNextToolCardAction();

        } else if (getMessageContent().equals(Utils.ACTIONTYPE_REROLL_DICE)) {
            Turn turn = controller.getModel().getTournament().getCurrentTurn();
            Action action = (turn.getCurrentToolCard().getActionToPerform().get(turn.getCurrentActionStepCounter()));
            Dice temp = new Dice(diceToBeChanged.getColor());
            temp.setValue(diceToBeChanged.getValue());
            ((ChangeDiceValueAction) action).setDiceBeforeChanges(temp);
            diceToBeChanged.rollDice();
            ((ChangeDiceValueAction) action).setSelectedToChangeDice(diceToBeChanged);
            //((ChangeDiceValueAction) action).setValueOfChange(diceToBeChanged.rollDice());
            turn.increaseCurrentActionStepCounter();
            turn.callNextToolCardAction();

        }
    }
}
