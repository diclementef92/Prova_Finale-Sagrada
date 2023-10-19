package it.polimi.ingsw.model.windowpattern;

import it.polimi.ingsw.model.dice.Dice;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {


    /**
     * Verify that the color of the cell is the assigned color
     */
    @Test
    public void getColorTest() {

        Cell cell= new Cell("red",3);
        assertEquals("red",cell.getColor());
    }

    /**
     * Verify that the value of the cell is the assigned value
     */
    @Test
    public void getValueTest() {
        Cell cell= new Cell("red",3);
        assertEquals(3,cell.getValue());
    }

    /**
     * Verify that the dice added to the cell is really assigned to it
     */
    @Test
    public void setDiceTest() {
        Cell cell= new Cell("red",3);
        Dice dice= new Dice("blue");
        dice.setValue(2);//fatto per rendere il dado idoneo alla cella
        cell.setDice(dice);
        assertEquals(dice,cell.getDice());
    }

    /**
     * Verify that the dice is the expected
     */
    @Test
    public void getDiceTest(){
        Cell test= new Cell("red",3);
        Dice dice = new Dice("red");
        dice.setValue(3);
        test.setDice(dice);
        assertEquals(dice, test.getDice());
    }

    /**
     * Verify that the cell is empty
     */
    @Test
    public void getDiceTest_cellWithoutDiceAtBeginning() {
        Cell cell= new Cell("red",3);
        assertNull(cell.getDice());
    }

    /**
     * Verify that the cell method toString retrieves the expected value
     */
    @Test
    public void toStringTest(){
        Cell cell= new Cell("red",3);
        assertEquals(cell.getColor() + " " + cell.getDice() + " " + cell.getDice(), cell.toString());
    }

}