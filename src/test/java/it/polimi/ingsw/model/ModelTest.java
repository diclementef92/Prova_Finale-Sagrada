package it.polimi.ingsw.model;

import it.polimi.ingsw.model.player.Player;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ModelTest {

    /**
     * Verify that the list of players is the expected
     */
    @Test
    public void getPlayersTest() {
        Player player1 = new Player("Jhon");
        Player player2 = new Player("Jack");
        List<Player> players =  new ArrayList<>();
        players.add(player1);
        players.add(player2);

        Model model = new Model(players);

        assertEquals(players,model.getPlayers());

    }

    /**
     * Verify that the starting player number is 0
     */
    @Test
    public void getCurrentPlayerNumberTest() {
        Player player1 = new Player("Jhon");
        Player player2 = new Player("Jack");
        List<Player> players =  new ArrayList<>();
        players.add(player1);
        players.add(player2);

        Model model = new Model(players);

        assertEquals(0,model.getCurrentPlayerNumber());

    }

    /**
     * Verify that is allowed to set the current player number
     */
    @Test
    public void setCurrentPlayerNumberTest() {
        Player player1 = new Player("Jhon");
        Player player2 = new Player("Jack");
        List<Player> players =  new ArrayList<>();
        players.add(player1);
        players.add(player2);

        Model model = new Model(players);
        model.getTournament().startGame();
        model.setCurrentPlayerNumber(1);
        assertEquals(1,model.getCurrentPlayerNumber());

    }

    /**
     * Verify that the value of the game board in the model is not null
     */
    @Test
    public void getGameBoardTest(){
        Player player1 = new Player("John");
        Player player2 = new Player("Jack");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        assertNotNull(model.getGameBoard());
    }

    /**
     * Verify that the value of the tournament in the model is not null
     */
    @Test
    public void getTournamentTest(){
        Player player1 = new Player("John");
        Player player2 = new Player("Jack");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        assertNotNull(model.getTournament());
    }
}