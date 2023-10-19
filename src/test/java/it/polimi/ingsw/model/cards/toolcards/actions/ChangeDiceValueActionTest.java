package it.polimi.ingsw.model.cards.toolcards.actions;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.utils.Utils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChangeDiceValueActionTest {


    /**
     * Verify that the dice's value in draftpool is the expected after increase it
     */
    @Test
    public void applyActionTest1(){
        List<Player> players = new ArrayList();
        players.add(new Player("John"));
        players.add(new Player("Jack"));
        Model model = new Model(players);
        Dice prove = new Dice("red");
        prove.setValue(2);
        model.getGameBoard().getDraftPool().getListDraftPoolDice().add(prove);
        ChangeDiceValueAction changeDiceValueAction = new ChangeDiceValueAction(model,"test", Utils.ACTIONTYPE_INCREASE_DICE_VALUE);
        changeDiceValueAction.setSelectedToChangeDice(model.getGameBoard().getDraftPool().getListDraftPoolDice().get(0));
        changeDiceValueAction.setValueOfChange(1);
        changeDiceValueAction.applyAction();
        assertEquals(3, model.getGameBoard().getDraftPool().getListDraftPoolDice().get(0).getValue());
    }

    /**
     * Verify that the dice's value in draftpool is the expected after flip it
     */
    @Test
    public void applyActionTest2() {
        List<Player> players = new ArrayList();
        players.add(new Player("John"));
        players.add(new Player("Jack"));
        Model model = new Model(players);
        Dice prove = new Dice("red");
        prove.setValue(2);
        Dice test = new Dice("red");
        test.setValue(3);
        model.getGameBoard().getDraftPool().getListDraftPoolDice().add(prove);
        ChangeDiceValueAction changeDiceValueAction = new ChangeDiceValueAction(model,"test", Utils.ACTIONTYPE_FLIP_DICE);
        changeDiceValueAction.setSelectedToChangeDice(test);
        changeDiceValueAction.setDiceBeforeChanges(prove);
        changeDiceValueAction.applyAction();
        assertEquals(test.getValue(), model.getGameBoard().getDraftPool().getListDraftPoolDice().get(0).getValue());
    }

    /**
     * Verify that the dice's value in draftpool is the expected after re-roll it
     */
    @Test
    public void applyActionTest3(){
        List<Player> players = new ArrayList();
        players.add(new Player("John"));
        players.add(new Player("Jack"));
        Model model = new Model(players);
        Dice prove = new Dice("red");
        prove.setValue(2);
        Dice test = new Dice("red");
        test.setValue(3);
        model.getGameBoard().getDraftPool().getListDraftPoolDice().add(prove);
        ChangeDiceValueAction changeDiceValueAction = new ChangeDiceValueAction(model,"test", Utils.ACTIONTYPE_REROLL_DICE);
        changeDiceValueAction.setSelectedToChangeDice(test);
        changeDiceValueAction.setDiceBeforeChanges(prove);
        changeDiceValueAction.applyAction();
        assertEquals(test.getValue(), model.getGameBoard().getDraftPool().getListDraftPoolDice().get(0).getValue());
    }

    /**
     * Verify that the dice's value in draftpool before chenge it's the expected
     */
    @Test
    public void getDiceBeforeChangesTest(){
        List<Player> players = new ArrayList();
        players.add(new Player("John"));
        players.add(new Player("Jack"));
        Model model = new Model(players);
        Dice prove = new Dice("red");
        prove.setValue(2);
        Dice test = new Dice("red");
        test.setValue(3);
        model.getGameBoard().getDraftPool().getListDraftPoolDice().add(prove);
        ChangeDiceValueAction changeDiceValueAction = new ChangeDiceValueAction(model,"test", Utils.ACTIONTYPE_FLIP_DICE);
        changeDiceValueAction.setSelectedToChangeDice(test);
        changeDiceValueAction.setDiceBeforeChanges(prove);
        changeDiceValueAction.applyAction();
        changeDiceValueAction.setDiceBeforeChanges(prove);
        assertEquals(prove, changeDiceValueAction.getDiceBeforeChanges());
    }
}
