package it.polimi.ingsw.controller.interpreter;

import it.polimi.ingsw.controller.message.requests.PassTurnRequest;
import it.polimi.ingsw.controller.message.requests.ToolcardUsageRequest;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.player.Player;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class InterpreterTest {
    Player player1 = new Player("Jhon");
    Player player2 = new Player("Jack");
    List<Player> players =  new ArrayList<>();

    /**
     * Verify that the interpreter recognize correctly a pass turn request
     */
    @Test
    public void parseTest1(){
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        model.getTournament().startGame();
        Interpreter interpreter = new Interpreter(System.out, model, player1, System.in);
        assertTrue(interpreter.parse("pass") instanceof PassTurnRequest);
    }

    /*
    //Cannot be tested, because the request created with the "placedice" command require an input from the user
    // (number of dice)
    @Test
    public void parseTest2(){
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        model.getTournament().startGame();
        Interpreter interpreter = new Interpreter(System.out, model, player1, System.in);
        assertTrue(interpreter.parse("placedice") instanceof PlaceDiceRequest);
    }*/

    /**
     * Verify that the interpreter recognize correctly a tool card usage request
     */
    @Test
    public void parseTest3(){
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        model.getTournament().startGame();
        int toolcardChose = 0;
        System.setIn(new ByteArrayInputStream(Integer.toString(toolcardChose).getBytes()));
        Interpreter interpreter = new Interpreter(System.out, model, player1, System.in);
        assertTrue(interpreter.parse("usetool") instanceof ToolcardUsageRequest);
    }

}
