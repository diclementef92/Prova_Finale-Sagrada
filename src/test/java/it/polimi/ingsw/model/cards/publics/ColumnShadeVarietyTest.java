package it.polimi.ingsw.model.cards.publics;

import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.player.PlayerBoard;
import it.polimi.ingsw.model.windowpattern.WindowPatternCard;
import org.junit.Test;

import static org.junit.Assert.*;

public class ColumnShadeVarietyTest {

    /**
     * Verify that the score is the expected
     * in a board with all dices with different shades in a column
     */
    @Test
    public void returnScoreTest_case1() {

        String[] test = {
                "y", "b" , "empty" , "empty" , "empty" ,
                "y","empty", "5" , "b", "empty" ,
                "3" , "r", "y", "empty" , "b",
                "empty" , "empty" , "empty","y", "empty"};
        WindowPatternCard windowPatternCard = new WindowPatternCard("name",5,test);
        PlayerBoard playerBoard = new PlayerBoard("color",windowPatternCard);
        ColumnShadeVariety columnShadeVariety = new ColumnShadeVariety("asd", "asd " ,4);
        Dice prova3 = new Dice("y");
        Dice prova2 = new Dice("r");
        Dice prova1 = new Dice("r");
        Dice prova4 = new Dice("g");
        prova3.setValue(1);
        prova1.setValue(2);
        prova2.setValue(3);
        prova4.setValue(5);
        playerBoard.getWindowboard().insertDie(prova3, 0,0);
        playerBoard.getWindowboard().insertDie(prova2, 1,0);
        playerBoard.getWindowboard().insertDie(prova1, 2,0);
        playerBoard.getWindowboard().insertDie(prova4, 3,0);
        System.out.println(playerBoard);
        assertEquals(4, columnShadeVariety.returnScore(playerBoard));
    }
}