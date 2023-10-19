package it.polimi.ingsw.model.dice;

import org.junit.Test;

import java.util.Arrays;

import static it.polimi.ingsw.utils.Utils.MIN_SHADE_DICE;
import static it.polimi.ingsw.utils.Utils.NUMBER_DICE_SHADES;
import static junit.framework.TestCase.*;

public class DiceTest {

    private Dice dice = new Dice("red");

    /**
     * Try to decrease the value of the dice
     */
    @Test
    public void decreaseValueTest() {
        dice.setValue(4);
        dice.decreaseValue();

        //System.out.println(dice.getValue());
        assertEquals(3,dice.getValue());
    }

    /**
     * Try to do increase the value of the dice
     */
    @Test
    public void increaseValueTest(){
        dice.setValue(1);
        dice.increaseValue();

        //System.out.println(dice.getValue());
        assertEquals(2,dice.getValue());
    }

    /**
     * Try to increase the value of the dice over its maximum value
     */

    @Test
    public void increaseValueTest_overMax(){
        dice.setValue(NUMBER_DICE_SHADES);
        dice.increaseValue();

        //System.out.println(dice.getValue());
        assertEquals(NUMBER_DICE_SHADES,dice.getValue());
    }

    /**
     *Try to decrease the value of the dice under its minimum value
     */

    @Test
    public void decreaseValueTest_underMin() {
        dice.setValue(MIN_SHADE_DICE);
        dice.decreaseValue();

        //System.out.println(dice.getValue());
        assertEquals(MIN_SHADE_DICE,dice.getValue());
    }

    /**
     * Verify that the dice value after rolling is in the range
     */
    @Test
    public void rollDiceTest_valueintheRange(){
        dice.rollDice();
        int value = dice.getValue();

        //System.out.println(dice.getValue());

        if (value<MIN_SHADE_DICE || value>NUMBER_DICE_SHADES) {
            fail("failed: invalid value number, out of range");
        }
    }

    /**
     * Verify that the color of the dice is the expected
     */
    @Test
    public void getColorTest(){
        assertEquals("red",dice.getColor());
    }

    /**
     * Verify that the value of the dice is the expected
     */
    @Test
    public void setValueTest(){
        for(int i = 1; i<=6; i++){
            dice.setValue(i);
            assertEquals(i, dice.getValue());
        }
        for(int j = -20; j <=0; j++){
            dice.setValue(j);
            assertEquals(6, dice.getValue());
        }
    }

    /**
     * Verify that the dice method toString retrieves a not null value
     */
    @Test
    public void toStringTest(){
        Dice dice = new Dice("red");
        dice.setValue(2);
        assertNotNull(dice.toString());
    }

    /**
     * Verify that the dice method color toString retrieves a not null value
     */
    @Test
    public void toColorString(){
    Dice dice = new Dice("red");
    dice.setValue(2);
    assertNotNull(dice.toColorString());
    }

    /**
     * Verify that the dice method for drawing faces retrieves the expected value
     */
    @Test
    public void diceFacesTest(){
        String [] test = {"┏━━━━━━━┓","┃ ●     ┃", "┃       ┃", "┃     ● ┃", "┗━━━━━━━┛"};
        assertTrue(Arrays.equals(test, Dice.diceFaces(2)));
    }

}

