package it.polimi.ingsw.model.cards.privates;

import it.polimi.ingsw.model.cards.publics.ColorVariety;
import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.player.PlayerBoard;
import it.polimi.ingsw.model.windowpattern.WindowPatternCard;
import it.polimi.ingsw.utils.Utils;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrivateObjectiveCardTest {

    PrivateObjectiveCard privateObjectiveCard = new PrivateObjectiveCard("name","descr","red");

    /**
     * Verify that the color of the card is the selected one
     */
    @Test
    public void getColorPrivateTest() {
        assertEquals("red",privateObjectiveCard.getColorPrivate());
    }

    /**
     * Verify that the score at beginning of the game is zero
     */
    @Test
    public void getScoreTest_AtBeginning() {
        assertEquals(0,privateObjectiveCard.getScore());
    }

    /**
     * Verify that the score is the expected
     */
    @Test
    public void returnScoreTest(){
        String[] color={"blue", "yellow", "red", "purple", "green"};
        //creation of user window board, code from color variety test
        String[] test = {
                "y", "b" , "empty" , "empty" , "empty" ,
                "y","empty", "5" , "b", "empty" ,
                "3" , "r", "y", "empty" , "b",
                "empty" , "empty" , "empty","y", "empty"};

        int temp = 0;
        WindowPatternCard windowPatternCard = new WindowPatternCard("name",5,test);
        PlayerBoard playerBoard = new PlayerBoard("color",windowPatternCard);
        Dice prova1 = new Dice("red");
        Dice prova2 = new Dice("blue");
        Dice prova3 = new Dice("yellow");
        Dice prova4 = new Dice("green");
        Dice prova5 = new Dice("purple");

        prova1.setValue(1);
        prova2.setValue(1);
        prova3.setValue(1);
        prova4.setValue(1);
        prova5.setValue(1);

        playerBoard.getWindowboard().insertDie(prova3, 0,0);
        playerBoard.getWindowboard().insertDie(prova2, 0,1);
        playerBoard.getWindowboard().insertDie(prova1, 0,2);
        playerBoard.getWindowboard().insertDie(prova4, 0,3);
        playerBoard.getWindowboard().insertDie(prova5, 0,4);

        //
        for(int i = 0; i<color.length; i++, temp = 0)
        {
            PrivateObjectiveCard privateCard = new PrivateObjectiveCard("test", "test", color[i]);
            int expectedScore = 0;
            for(int j=0; j<test.length; j++) {
                if (j!=0&&(j%Utils.MAX_ROW_WINDOW_PATTERN) == 0)
                    temp = j/Utils.MAX_ROW_WINDOW_PATTERN;
                int row = (j - (temp) * Utils.MAX_ROW_WINDOW_PATTERN);
                int col = temp;
                System.out.println("row: " + row + ", col: " + col+ ":playerboard remaining: "+(test.length-j));
                if (playerBoard.getWindowboard().getCell(row, col).getDice() != null) {
                    System.out.println("die color: " + playerBoard.getWindowboard().getCell(row, col).getDice().getColor() + ", color: " + color[i]);
                    if (playerBoard.getWindowboard().getCell(row, col).getDice().getColor().equals(color[i]))
                        expectedScore += playerBoard.getWindowboard().getCell(row, col).getDice().getValue();
                    System.out.println("punteggio: "+expectedScore);
                }
            }
            System.out.println("prova n° "+i);
            assertEquals(expectedScore,privateCard.returnScore(playerBoard));
        }
    }

    /**
     * Verify that the method toString retrieves the expected value
     */
    @Test
    public void toStringTest(){
        PrivateObjectiveCard privObj = new PrivateObjectiveCard("test", "Test", "test");
        assertEquals("------\nName: test\nDescription: Test\nColor: test\n------\n", privObj.toString());
    }
}