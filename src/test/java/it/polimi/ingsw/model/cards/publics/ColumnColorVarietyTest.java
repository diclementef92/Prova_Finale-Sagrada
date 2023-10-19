package it.polimi.ingsw.model.cards.publics;

import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.player.PlayerBoard;
import it.polimi.ingsw.model.windowpattern.WindowPatternCard;
import org.junit.Test;

import static org.junit.Assert.*;

public class ColumnColorVarietyTest {

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
        ColumnColorVariety columnColorVariety = new ColumnColorVariety("asd", "asd " ,5);
        Dice prova3 = new Dice("y");
        Dice prova2 = new Dice("b");
        Dice prova1 = new Dice("r");
        Dice prova4 = new Dice("g");
        playerBoard.getWindowboard().insertDie(prova3, 0,0);
        playerBoard.getWindowboard().insertDie(prova2, 1,0);
        playerBoard.getWindowboard().insertDie(prova1, 2,0);
        playerBoard.getWindowboard().insertDie(prova4, 3,0);
        System.out.println(playerBoard);
        assertEquals(5, columnColorVariety.returnScore(playerBoard));
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
        ColumnColorVariety columnColorVariety = new ColumnColorVariety("asd", "asd " ,5);
        Dice prova3 = new Dice("y");
        Dice prova2 = new Dice("r");
        Dice prova1 = new Dice("r");
        Dice prova4 = new Dice("g");
        playerBoard.getWindowboard().insertDie(prova3, 0,0);
        playerBoard.getWindowboard().insertDie(prova2, 1,0);
        playerBoard.getWindowboard().insertDie(prova1, 2,0);
        playerBoard.getWindowboard().insertDie(prova4, 3,0);
        System.out.println(playerBoard);
        assertEquals(0, columnColorVariety.returnScore(playerBoard));
    }
}