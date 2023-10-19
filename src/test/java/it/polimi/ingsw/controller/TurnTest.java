package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.player.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class TurnTest {

    Player player1 = new Player("nickname");
    Turn turn = new Turn(null,player1);

    /**
     * Verify that the number of admissible actions at beginning for selecting a dice is 1
     */
    @Test
    public void getSelectDiceActionCounterTest() {
        assertEquals(1,turn.getSelectDiceActionCounter());
    }

    /**
     * Verify that the number of admissible actions at beginning for using a tool card is 1
     */
    @Test
    public void getUseToolCardActionsCounterTest() {
        assertEquals(1,turn.getUseToolCardActionsCounter());
    }

    /**
     * Verify that is allowed to set the counter of dice used
     */
    @Test
    public void setSelectDiceActionCounterTest() {
        turn.setSelectDiceActionCounter(0);

        assertEquals(0,turn.getSelectDiceActionCounter());

        turn.setSelectDiceActionCounter(2);

        assertEquals(2,turn.getSelectDiceActionCounter());
    }

    /**
     * Verify that is allowed to set the counter of tool cards used
     */
    @Test
    public void setUseToolCardActionsCounterTest() {
        turn.setUseToolCardActionsCounter(0);

        assertEquals(0,turn.getUseToolCardActionsCounter());

        turn.setUseToolCardActionsCounter(2);

        assertEquals(2,turn.getUseToolCardActionsCounter());
    }
}