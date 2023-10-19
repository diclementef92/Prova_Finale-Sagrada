package it.polimi.ingsw.model.windowpattern;

import it.polimi.ingsw.model.cards.Card;
import org.junit.Test;

import static org.junit.Assert.*;

import static it.polimi.ingsw.utils.Utils.*;

public class WindowPatternDeckTest {

    private String[] test = {"red","empty","blue","empty","yellow",
            "4","purple","3","green","2",
            "empty","1","empty","5","empty",
            "empty","empty","6","empty","empty"};

    /**
     * Verify the number of windows at beginning by verify
     * if it is possible to take all the 24 windows pattern from the deck
     */
    @Test
    public  void WindowPatternDeckTest_numberOfWindows(){

        WindowPatternDeck windowPatternDeck = new WindowPatternDeck();
        for (int i = 1; i <= MAX_NUMBER_WINDOWPATTERN; i++) {

            try {
                windowPatternDeck.takeCardFromWPDeck();
            }catch (IndexOutOfBoundsException Ex){
                fail("the number of window pattern are less than " + MAX_NUMBER_WINDOWPATTERN);
            }
        }
    }

    /**
     * Verify that the number of window pattern cards in the deck decrease as we pick them <br>
     *     till it is empty
     */
    @Test
    public void getSizeWindowPatternDeckTest(){
        WindowPatternDeck wpdeck = new WindowPatternDeck();
        for (int i = 1; i <= MAX_NUMBER_WINDOWPATTERN; i++) {

            wpdeck.takeCardFromWPDeck();
            assertEquals(MAX_NUMBER_WINDOWPATTERN - i, wpdeck.getSizeWindowPatternDeck());
        }
    }

    /**
     * Verify that the window pattern deck method for take returns a not null value
     */
    @Test
    public void takeCardFromWPDeckTest(){
        WindowPatternDeck windowPatternDeck = new WindowPatternDeck();
        assertNotNull(windowPatternDeck.takeCardFromWPDeck());
    }

    /**
     * Verify that the window pattern deck method toString retrieves the expected value
     */
    @Test
    public void toStringTest(){
        WindowPatternDeck windowPatternDeck1 = new WindowPatternDeck();
        WindowPatternDeck test = windowPatternDeck1;
        StringBuilder str = new StringBuilder();
        while(windowPatternDeck1.getSizeWindowPatternDeck()>0)
            str.append(windowPatternDeck1.takeCardFromWPDeck());
        assertNotEquals(str.toString(), test.toString());
    }
}