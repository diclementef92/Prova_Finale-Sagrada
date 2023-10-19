package it.polimi.ingsw.model.windowpattern;

import it.polimi.ingsw.model.dice.Dice;
import org.junit.Test;

import static it.polimi.ingsw.utils.Utils.MAX_COLUMN_WINDOW_PATTERN;
import static it.polimi.ingsw.utils.Utils.MAX_ROW_WINDOW_PATTERN;
import static org.junit.Assert.*;

public class WindowPatternCardTest {

    //until now this string is necessary for the construct but unused

    String[] imported = {"red", "empty", "blue", "empty", "yellow",
            "4", "purple", "3", "green", "2",
            "empty", "1", "empty", "5", "empty",
            "empty", "empty", "6", "empty", "empty"};

    /**
     * Verify that the name of the window is the expected
     */
    @Test
    public void getNameTest() {
        WindowPatternCard window = new WindowPatternCard("name", 5, imported);
        assertEquals("name", window.getName());
    }

    /**
     * Verify that the difficulty of the window is the expected
     */
    @Test
    public void getDifficultyTest() {
        WindowPatternCard window = new WindowPatternCard("name", 5, imported);
        assertEquals(5, window.getDifficulty());
    }


    /**
     * Verify that the dice put in a colored cell is really in it
     */
    @Test
    public void insertDieTest_ColoredCell() {
        WindowPatternCard window = new WindowPatternCard("name", 5, imported);
        Dice dice = new Dice("red");
        window.insertDie(dice, 0, 0);

        assertEquals(dice, window.getCell(0, 0).getDice());
    }

    /**
     * Verify that the dice put in a numeric cell is really in it
     */
    @Test
    public void insertDieTest_NumericCell() {
        WindowPatternCard window = new WindowPatternCard("name", 5, imported);
        Dice one = new Dice("yellow");
        one.setValue(1);

        window.insertDie(one, 0, 4);

        assertEquals(one, window.getCell(0, 4).getDice());
    }

    /**
     * Verify that the window pattern method getCell return a not null value
     */
    @Test
    public void getCellTest() {
        WindowPatternCard windowPatternCard = new WindowPatternCard("test", 5, imported);
        assertNotNull(windowPatternCard.getCell(0, 0));
    }

    /**
     * Verify that the window pattern method toString retrieves the expected value
     */
    @Test
    public void toStringTest() {
        WindowPatternCard windowPatternCard = new WindowPatternCard("test", 5, imported);
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < MAX_ROW_WINDOW_PATTERN; i++) {
            for (int j = 0; j < MAX_COLUMN_WINDOW_PATTERN; j++) {
                ret.append("\t[ ").append(windowPatternCard.getCell(i, j).getColor()).append(" - ").append(windowPatternCard.getCell(i, j).getValue()).append(" ]");
            }
            ret.append("\n");
        }
        assertEquals("\n=======\n" +
                "Name: " + windowPatternCard.getName() + "\n" +
                "Difficulty: " + windowPatternCard.getDifficulty() + "\n" +
                "Grid: " + "\n" +
                ret.toString() + "\n" +
                "=======\n", windowPatternCard.toString());
    }
}