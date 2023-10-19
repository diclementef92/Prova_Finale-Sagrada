package it.polimi.ingsw.controller.interpreter;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.player.Player;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;

public class InfoInterpreterTest {
    Player player1 = new Player("Jhon");
    Player player2 = new Player("Jack");
    List<Player> players =  new ArrayList<>();

    /**
     * Verify that the interpreter recognize correctly an information request
     */
    @Test
    public void parseInfoTest(){
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        InfoInterpreter infoInterpreter = new InfoInterpreter(model, player1, System.out, System.in);
        assertNull(infoInterpreter.parseInfo("showhelp", player1, model));
    }
}
