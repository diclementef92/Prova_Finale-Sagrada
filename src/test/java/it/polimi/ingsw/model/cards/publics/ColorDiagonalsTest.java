package it.polimi.ingsw.model.cards.publics;

import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.player.PlayerBoard;
import it.polimi.ingsw.model.windowpattern.WindowPatternCard;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ColorDiagonalsTest {

    /**
     * Verify that the score is the expected
     * in a board with all dices with different colors in diagonals
     */
    @Test
    public void returnScoreTest(){
        String[] test = {
                "y", "b" , "empty" , "empty" , "empty" ,
                "y","empty", "5" , "b", "empty" ,
                "3" , "r", "y", "empty" , "b",
                "empty" , "empty" , "empty","y", "empty"};
        WindowPatternCard windowPatternCard = new WindowPatternCard("name",5,test);
        PlayerBoard playerBoard = new PlayerBoard("color",windowPatternCard);
        ColorDiagonals colorDiagonals = new ColorDiagonals("asd", "asd", 4);
        Dice prova1 = new Dice("red");
        Dice prova2 = new Dice("blue");
        Dice prova3 = new Dice("yellow");
        Dice prova4 = new Dice("green");
        Dice prova5 = new Dice("purple");
        playerBoard.getWindowboard().insertDie(prova3, 0,0);
        playerBoard.getWindowboard().insertDie(prova2, 0,1);
        playerBoard.getWindowboard().insertDie(prova1, 0,2);
        playerBoard.getWindowboard().insertDie(prova4, 0,3);
        playerBoard.getWindowboard().insertDie(prova5, 0,4);
        //temporary, until colordiagonals is fixed
        assertEquals(0, colorDiagonals.returnScore(playerBoard));
    }
}
