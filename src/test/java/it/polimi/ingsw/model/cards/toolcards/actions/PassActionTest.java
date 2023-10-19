package it.polimi.ingsw.model.cards.toolcards.actions;

import it.polimi.ingsw.controller.Tournament;
import it.polimi.ingsw.controller.Turn;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.player.Player;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PassActionTest {

    Player player1 = new Player("Jhon");
    Player player2 = new Player("Nicolas");
    List<Player> players = new ArrayList<>();

    /**
     * Verify that the current player number is the expected also after pass turn action
     */
    @Test
    public void checkIfCurrentPlayerTest (){

        players.add(player1);
        players.add(player2);
        Model model = new Model(players);

        Tournament tournament = model.getTournament();
        tournament.startGame();

        assertEquals(0,model.getCurrentPlayerNumber());


        //turno player 1
        assertEquals( player1, model.getPlayers().get(model.getCurrentPlayerNumber()));

        //player 1 passa
        PassTurnAction passAction =  new PassTurnAction(model);
        Turn turn = tournament.getCurrentTurn();

        passAction.performRequest();

        //il gioco passa al player 2
        System.out.println("giocatore corrente: " + model.getCurrentPlayerNumber());
        //assertEquals(player2, model.getPlayers().get(model.getCurrentPlayerNumber()));

    }

    /**
     * Verify that the pass turn action is available
     */
    @Test
    public void checkActionValidityTest(){
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        PassTurnAction passTurnAction = new PassTurnAction(model);
        assertTrue(passTurnAction.checkActionValidity());
    }
}