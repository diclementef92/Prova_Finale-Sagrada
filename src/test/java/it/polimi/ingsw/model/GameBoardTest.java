package it.polimi.ingsw.model;

import it.polimi.ingsw.model.player.Player;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameBoardTest {

    /**
     * Verify that the dice bag contains all the dices when Gameboard is created
     */
    @Test
    public void getBag() {
        GameBoard gameBoard = new GameBoard(null,2);
        assertEquals(90, gameBoard.getDiceBag().diceBagSize());
    }

    /**
     * Verify that the round value is one when Gameboard is created
     */
    @Test
    public void getRounds() {
        GameBoard gameBoard = new GameBoard(null,2);
        assertEquals(1, gameBoard.getRoundTrack().getNumberofRound());
    }

    /**
     * Verify that the draft pool value is not null when Gameboard is created
     */
    @Test
    public void getDratPoolTest(){
        Player player1 = new Player("John");
        Player player2 = new Player("Jack");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        GameBoard gameBoard = new GameBoard(model, 2);
        assertNotNull(gameBoard.getDraftPool());
    }

    /**
     * Verify that the tool cards value are not null when Gameboard is created
     */
    @Test
    public void getToolCardsTest(){
        Player player1 = new Player("John");
        Player player2 = new Player("Jack");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        GameBoard gameBoard = new GameBoard(model, 2);
        assertNotNull(gameBoard.getToolcards());
    }

    /**
     * Verify that the public cards value are not null when Gameboard is created
     */
    @Test
    public void getPublicsTest(){
        Player player1 = new Player("John");
        Player player2 = new Player("Jack");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        GameBoard gameBoard = new GameBoard(model, 2);
        assertNotNull(gameBoard.getPublics());
    }

    /**
     * Verify that the private cards value are not null when Gameboard is created
     */
    @Test
    public void getPrivatesTest(){
        Player player1 = new Player("John");
        Player player2 = new Player("Jack");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        GameBoard gameBoard = new GameBoard(model, 2);
        assertNotNull(gameBoard.getPrivates());
    }

    /**
     * Verify that the window pattern deck value are not null when Gameboard is created
     */
    @Test
    public void getWindowPatternDeckTest(){
        Player player1 = new Player("John");
        Player player2 = new Player("Jack");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        GameBoard gameBoard = new GameBoard(model, 2);
        assertNotNull(gameBoard.getWindowpatterndeck());
    }

    /**
     * Verify that the tool cards value in game are not null when Gameboard is created
     */
    @Test
    public void getToolCardsInGameTest(){
        Player player1 = new Player("John");
        Player player2 = new Player("Jack");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        GameBoard gameBoard = new GameBoard(model, 2);
        assertNotNull(gameBoard.getToolcardsInGame());
    }

    /**
     * Verify that the public cards value in game are not null when Gameboard is created
     */
    @Test
    public void getPublicsInGameTest(){
        Player player1 = new Player("John");
        Player player2 = new Player("Jack");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        GameBoard gameBoard = new GameBoard(model, 2);
        assertNotNull(gameBoard.getPublicsInGame());
    }

    /**
     * Verify that the private cards value in game are not null when Gameboard is created
     */
    @Test
    public void getPrivatesInGame(){
        Player player1 = new Player("John");
        Player player2 = new Player("Jack");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        GameBoard gameBoard = new GameBoard(model, 2);
        assertNotNull(gameBoard.getPrivatesInGame());
    }

    /**
     * Verify that the gameboard method toString retrieves the expected value with two player
     */
    @Test
    public void toStringTest1(){
        Player player1 = new Player("John");
        Player player2 = new Player("Jack");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        GameBoard gameBoard = new GameBoard(model, 2);
        assertEquals("ACTUAL GAMEBOARD: \n" +
                "\n======================\n" +
                "Draftpool: \n" + gameBoard.getDraftPool().toString() +
                "\n======================\n" +
                "Roundtrack: \n" + gameBoard.getRoundTrack().fullColoredString() + "\n" +
                "\n======================\n" +
               "Public Objective Cards on the board: \n" + gameBoard.getPublicsInGame().toString() + "\n" +
                "\n======================\n" +
                "ToolCards on the board:\n " + gameBoard.getToolcardsInGame().toString() + "\n" +
                "\n======================\n", gameBoard.toString());
    }

    /**
     * Verify that the gameboard method toString retrieves the expected value with one player
     */
    @Test
    public void toStringTest2(){
        Player player1 = new Player("John");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        Model model = new Model(players);
        GameBoard gameBoard = new GameBoard(model, 1);
        assertEquals("ACTUAL GAMEBOARD: \n" +
                "\n======================\n" +
                "Draftpool: \n" + gameBoard.getDraftPool().toString() +
                "\n======================\n" +
                "Roundtrack: \n" + gameBoard.getRoundTrack().fullColoredString() + "\n" +
                "\n======================\n" +
                "Private Objective Cards on the board: \n" + gameBoard.getPrivatesInGame().toString() + "\n" +
                "\n======================\n" +
                "Public Objective Cards on the board: \n" + gameBoard.getPublicsInGame().toString() + "\n" +
                "\n======================\n" +
                "ToolCards on the board:\n " + gameBoard.getToolcardsInGame().toString() + "\n" +
                "\n======================\n", gameBoard.toString());
    }
}