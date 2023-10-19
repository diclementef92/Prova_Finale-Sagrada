package it.polimi.ingsw.model.cards.publics;

import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.player.PlayerBoard;
import it.polimi.ingsw.model.windowpattern.WindowPatternCard;
import org.junit.Test;

import static org.junit.Assert.*;

public class LightShadesTest {

    /**
     * Verify that the score is the expected
     * in a board with four dices witch value is 1 or 2
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
        LightShades lightShades = new LightShades("ads", "asd", 2);
        Dice prova3 = new Dice("y");
        prova3.setValue(1);
        Dice prova2 = new Dice("b");
        prova2.setValue(2);
        Dice prova1 = new Dice("r");
        prova1.setValue(2);
        Dice prova4 = new Dice("g");
        prova4.setValue(1);
        playerBoard.getWindowboard().insertDie(prova3, 0,0);
        playerBoard.getWindowboard().insertDie(prova2, 0,1);
        playerBoard.getWindowboard().insertDie(prova1, 2,3);
        playerBoard.getWindowboard().insertDie(prova4, 3,0);
        System.out.println(playerBoard);
        assertEquals(4, lightShades.returnScore(playerBoard));
    }
}