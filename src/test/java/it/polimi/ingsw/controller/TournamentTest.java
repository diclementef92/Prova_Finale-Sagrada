package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.player.Player;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TournamentTest {
    Player player1 = new Player("Jhon");
    Player player2 = new Player("Jack");

    /**
     * Verify that the current turn value is not null
     */
    @Test
    public void startGameTest(){
        List<Player> players =  new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        Tournament tournament = model.getTournament();
        model.setCurrentPlayerNumber(0);
        tournament.startGame();
        assertNotNull(tournament.getCurrentTurn());
    }

    /**
     * Verify that the nickname of the current player after next turn action is the expected
     */
    @Test
    public void nextRoundTest1(){
        List<Player> players =  new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        Tournament tournament = model.getTournament();
        model.setCurrentPlayerNumber(0);
        tournament.startGame();
        tournament.nextTurn();
        assertEquals("Jack", model.getPlayers().get(tournament.getCurrentRoundPlayerNumber()).getPlayerNickname());
    }

    /**
     * Verify that the tournament is rounding counter clock wise after two turns with two players
     */
    @Test
    public void newTurnTest(){
        List<Player> players =  new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        Tournament tournament = model.getTournament();
        model.setCurrentPlayerNumber(0);
        tournament.startGame();
        tournament.nextTurn();
        tournament.nextTurn();
        assertTrue(tournament.isCounterClockwise());
    }
}
