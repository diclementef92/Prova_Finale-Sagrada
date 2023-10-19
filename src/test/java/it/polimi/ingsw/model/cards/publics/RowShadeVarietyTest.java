package it.polimi.ingsw.model.cards.publics;

import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.player.PlayerBoard;
import it.polimi.ingsw.model.windowpattern.WindowPatternCard;
import org.junit.Test;

import static org.junit.Assert.*;

public class RowShadeVarietyTest {

    /**
     * Verify that the score is the expected
     * in a board with four dices in a row with different values
     */
    @Test
    public void returnScore() {
        String[] test = {
                "y", "b" , "empty" , "empty" , "empty" ,
                "y","empty", "5" , "b", "empty" ,
                "3" , "r", "y", "empty" , "b",
                "empty" , "empty" , "empty","y", "empty"};
        WindowPatternCard windowPatternCard = new WindowPatternCard("name",5,test);
        System.out.println(windowPatternCard.toString());
        PlayerBoard playerBoard = new PlayerBoard("color",windowPatternCard);
        RowShadeVariety rowShadeVariety = new RowShadeVariety("ads", "asd", 5);
        Dice prova3 = new Dice("y");
        prova3.setValue(5);
        Dice prova2 = new Dice("b");
        prova2.setValue(4);
        Dice prova1 = new Dice("r");
        prova1.setValue(3);
        Dice prova4 = new Dice("g");
        prova4.setValue(2);
        Dice prova5 = new Dice("p");
        prova5.setValue(1);
        playerBoard.getWindowboard().insertDie(prova3, 0,0);
        playerBoard.getWindowboard().insertDie(prova2, 0,1);
        playerBoard.getWindowboard().insertDie(prova1, 0,2);
        playerBoard.getWindowboard().insertDie(prova4, 0,3);
        playerBoard.getWindowboard().insertDie(prova5, 0,4);
        System.out.println(playerBoard);
        assertEquals(5, rowShadeVariety.returnScore(playerBoard));
    }
}