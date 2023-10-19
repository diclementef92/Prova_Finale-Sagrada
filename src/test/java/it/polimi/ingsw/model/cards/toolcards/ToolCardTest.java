package it.polimi.ingsw.model.cards.toolcards;

import it.polimi.ingsw.model.cards.toolcards.actions.Action;
import it.polimi.ingsw.utils.Utils;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;


public class ToolCardTest {

    /**
     * Verify that the number of tokens is the expected
     */
    @Test
    public void getFavorTokenTest(){
        int favorToken = 1;
        int number = 1;
        ArrayList<Action> actions = new ArrayList();
        ToolCard toolCard = new ToolCard("test", number, "red", "test", actions);
        toolCard.setFavorToken(favorToken);
        assertEquals(favorToken, toolCard.getFavorToken());
    }

    /**
     * Verify that the number of the card is the expected
     */
    @Test
    public void getNumberTest(){
        int favorToken = 1;
        int number = 1;
        String color = "red";
        ArrayList<Action> actions = new ArrayList();
        ToolCard toolCard = new ToolCard("test", number, color, "test", actions);
        toolCard.setFavorToken(favorToken);
        assertEquals(number, toolCard.getNumber());
    }

    /**
     * Verify that the color of teh card is the expected
     */
    @Test
    public void getColorTest(){
        int favorToken = 1;
        int number = 1;
        String color = "red";
        ArrayList<Action> actions = new ArrayList();
        ToolCard toolCard = new ToolCard("test", number, color, "test", actions);
        toolCard.setFavorToken(favorToken);
        assertEquals(color, toolCard.getColor());
    }

    /**
     * Verify that the number of tokens needed for use the tool card is the expected
     */
    @Test
    public void getFavorTokenNeededTest(){
        int favorToken = 1;
        int number = 1;
        String color = "red";
        ArrayList<Action> actions = new ArrayList<>();
        ToolCard toolCard = new ToolCard("test", number, color, "test", actions);
        toolCard.setFavorToken(favorToken);
        assertEquals(Utils.TOKEN_NEEDED_IF_NOT_EMPTY, toolCard.getFavorTokenNeeded());
        toolCard.setFavorToken(0);
        assertEquals(Utils.TOKEN_NEEDED_IF_EMPTY, toolCard.getFavorTokenNeeded());
    }

    /**
     * Verify that the actions performed by the tool card are the expected
     */
    @Test
    public void getActionToPerformTest(){
        int number = 1;
        String color = "red";
        ArrayList<Action> actions = new ArrayList<>();
        ToolCard toolCard = new ToolCard("test", number, color, "test", actions);
        assertEquals(actions, toolCard.getActionToPerform());
    }

    /**
     * Verify that the another move dice value is the expected
     */
    @Test
    public void  getAnotherMoveDiceTest(){
        int favorToken = 1;
        int number = 1;
        String color = "red";
        ArrayList<Action> actions = new ArrayList<>();
        ToolCard toolCard = new ToolCard("test", number, color, "test", actions);
        toolCard.setAnotherMoveDice(0);
        assertEquals(0,toolCard.getAnotherMoveDice());
    }

    /**
     * Verify that the dice's color choose from round track is the expected
     */
    @Test
    public void getChooseColorFromRoundTrackTest(){
        int favorToken = 1;
        int number = 1;
        String color = "red";
        ArrayList<Action> actions = new ArrayList<>();
        ToolCard toolCard = new ToolCard("test", number, color, "test", actions);
        toolCard.setChoseColorFromRoundrack("red");
        assertEquals("red", toolCard.getChoseColorFromRoundrack());
    }

    /**
     * Verify that the toolcard method toString retrieves a not null value
     */
    @Test
    public void toStringTest(){
        int favorToken = 1;
        int number = 1;
        String color = "red";
        ArrayList<Action> actions = new ArrayList<>();
        ToolCard toolCard = new ToolCard("test", number, color, "test", actions);
        assertNotNull(toolCard.toString());
    }
    /**
     * Verify that the number of the tool card is the expected
     */
   // @Test
   // public void getNumeroTest() {
       // assertEquals(1,toolCard.getNumber());
    //}

    /**
     * Verify that the color of the tool card is the expected
     */
    //@Test
   // public void getColoreTest() {
      //  assertEquals("yellow",toolCard.getColor());
   // }

}