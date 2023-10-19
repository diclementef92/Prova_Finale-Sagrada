package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.cards.privates.PrivateObjectiveCard;
import it.polimi.ingsw.model.windowpattern.WindowPatternCard;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    /**
     * Verify that the nickname of the player is the expected
     */
    @Test
    public void getPlayerNickname() {
        Player player = new Player("John");
        assertEquals("John", player.getPlayerNickname());
    }

    /**
     * Test for the window board of the player
     */
    @Test
    public void getPlayerBoard() {
        String[] test = {"y", "b" , "empty" , "empty" , "empty" , "y","empty", "5" ,
                "b", "empty" , "3" , "r","y", "empty" , "b", "empty" , "empty" , "empty","y", "empty" , "b", "empty" , "empty" , "empty",
                "y", "empty", "b", "empty" , "empty" , "empty"};
        WindowPatternCard windowPatternCard = new WindowPatternCard("name",5,test);
        PlayerBoard playerBoard = new PlayerBoard("r", windowPatternCard);
        Player player = new Player("James");
        player.setPlayboard(playerBoard);
        assertEquals("y", player.getPlayerBoard().getWindowboard().getCell(0,0).getColor());
    }

    /**
     * Verify that the color of the player's private card is the expected
     */
    @Test
    public void getPrivateCard() {
        Player player = new Player("Jonathan");
        player.setPrivateCard(new PrivateObjectiveCard("Blue Card", "This is a blue card", "b"));
        assertEquals("b",player.getPrivateCard().getColorPrivate());
    }

    /**
     *  Verify that is allowed to set the amount of favor token of the player
     */
    @Test
    public void getFavorTokenAmount() {
        Player player = new Player("Jason the JSON");
        player.setFavorTokenAmount(8);
        assertEquals(8, player.getFavorTokenAmount());
    }

    /**
     * Verify that the player method toString retrieves the expected value
     */
    @Test
    public void toStringTest(){
        String[] test = {"y", "b" , "empty" , "empty" , "empty" , "y","empty", "5" ,
                "b", "empty" , "3" , "r","y", "empty" , "b", "empty" , "empty" , "empty","y", "empty" , "b", "empty" , "empty" , "empty",
                "y", "empty", "b", "empty" , "empty" , "empty"};
        WindowPatternCard windowPatternCard = new WindowPatternCard("name",5,test);
        PlayerBoard playerBoard = new PlayerBoard("r", windowPatternCard);        Player player = new Player("John");
        player.setPlayboard(playerBoard);
        player.setFavorTokenAmount(2);
        player.setPrivateCard(new PrivateObjectiveCard("Blue Card", "This is a blue card", "b"));
        assertEquals("\n=======\n" +
                "Nickname: " + player.getPlayerNickname() + "\n" +
                "Favor Token: " + player.getFavorTokenAmount() + "\n" +
                "Player Board: " + player.getPlayerBoard().fullColoredString() + "\n" +
                "=======\n", player.toString());
    }
}