package it.polimi.ingsw.model.cards.publics;

import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.player.PlayerBoard;
import it.polimi.ingsw.model.windowpattern.WindowPatternCard;
import org.junit.Test;

import static org.junit.Assert.*;

public class RowColorVarietyTest {


    /**
     * Case with all color different
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
        RowColorVariety rowColorVariety = new RowColorVariety("asd", "asd " ,6);
        Dice prova3 = new Dice("y");
        Dice prova2 = new Dice("b");
        Dice prova1 = new Dice("r");
        Dice prova4 = new Dice("g");
        Dice prova5 = new Dice("p");
        playerBoard.getWindowboard().insertDie(prova3, 0,0);
        playerBoard.getWindowboard().insertDie(prova2, 0,1);
        playerBoard.getWindowboard().insertDie(prova1, 0,2);
        playerBoard.getWindowboard().insertDie(prova4, 0,3);
        playerBoard.getWindowboard().insertDie(prova5, 0,4);
        assertEquals(6, rowColorVariety.returnScore(playerBoard));
    }

    /**
     * Case with two equal colors in same row
     */
    @Test
    public void returnScoreTest_case2() {

        String[] test = {
                "y", "b" , "empty" , "empty" , "empty" ,
                "y","empty", "5" , "b", "empty" ,
                "3" , "r", "y", "empty" , "b",
                "empty" , "empty" , "empty","y", "empty"};
        WindowPatternCard windowPatternCard = new WindowPatternCard("name",5,test);
        PlayerBoard playerBoard = new PlayerBoard("color",windowPatternCard);
        RowColorVariety rowColorVariety = new RowColorVariety("asd", "asd " ,6);
        Dice prova3 = new Dice("y");
        Dice prova2 = new Dice("b");
        Dice prova1 = new Dice("r");
        Dice prova4 = new Dice("r");
        Dice prova5 = new Dice("p");
        playerBoard.getWindowboard().insertDie(prova3, 0,0);
        playerBoard.getWindowboard().insertDie(prova2, 0,1);
        playerBoard.getWindowboard().insertDie(prova1, 0,2);
        playerBoard.getWindowboard().insertDie(prova4, 0,3);
        playerBoard.getWindowboard().insertDie(prova5, 0,4);
        assertEquals(0, rowColorVariety.returnScore(playerBoard));
    }
}