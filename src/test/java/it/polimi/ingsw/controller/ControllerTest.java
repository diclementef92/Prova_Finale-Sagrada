package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.player.Player;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ControllerTest {
    Player player1 = new Player("Jhon");
    Player player2 = new Player("Jack");
    List<Player> players =  new ArrayList<>();

    /**
     * Verify that the current player is the expected
     */
    @Test
    public void checkIfCurrentPlayerTest(){
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        model.getTournament().startGame();
        Controller controller = new Controller(model);
        assertTrue(controller.checkIfCurrentPlayer(player1));
    }
}
