package it.polimi.ingsw.model.cards.toolcards.actions;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerBoard;
import it.polimi.ingsw.model.windowpattern.WindowPatternCard;
import it.polimi.ingsw.utils.Utils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SkipTurnActionTest {

    /**
     * Verify that the skip turn action is valid
     */
    @Test
    public void checkActionValidityTest(){
        String[] test = {
                "y", "b" , "empty" , "empty" , "empty" ,
                "y","empty", "5" , "b", "empty" ,
                "3" , "r", "y", "empty" , "b",
                "empty" , "empty" , "empty","y", "empty"};
        WindowPatternCard windowPatternCard = new WindowPatternCard("name",5,test);
        PlayerBoard playerBoard = new PlayerBoard("color",windowPatternCard);
        Dice prova1 = new Dice("red");
        prova1.setValue(2);
        List<Player> players = new ArrayList();
        players.add(new Player("John"));
        players.add(new Player("Jack"));
        Model model = new Model(players);
        model.getTournament().startGame();
        model.setCurrentPlayerNumber(0);
        model.getPlayers().get(model.getTournament().getCurrentRoundPlayerNumber()).setPlayboard(playerBoard);
        model.getTournament().getCurrentTurn().setDiceToBePlaced(prova1);
        SkipTurnAction skipTurnAction = new SkipTurnAction(model,  "test", Utils.ACTIONTYPE_SKIP_NEXT_TURN);
        skipTurnAction.setCol(2);
        skipTurnAction.setRow(0);
        assertTrue(skipTurnAction.checkActionValidity());
    }

    /**
     * Verify that the player that will skip the turn is the one that used the toolcard
     */
    @Test
    public void applyActionTest(){
        String[] test = {
                "y", "b" , "empty" , "empty" , "empty" ,
                "y","empty", "5" , "b", "empty" ,
                "3" , "r", "y", "empty" , "b",
                "empty" , "empty" , "empty","y", "empty"};
        WindowPatternCard windowPatternCard = new WindowPatternCard("name",5,test);
        PlayerBoard playerBoard = new PlayerBoard("color",windowPatternCard);
        Dice prova1 = new Dice("red");
        prova1.setValue(2);
        List<Player> players = new ArrayList();
        players.add(new Player("John"));
        players.add(new Player("Jack"));
        Model model = new Model(players);
        model.getTournament().startGame();
        model.setCurrentPlayerNumber(0);
        model.getPlayers().get(model.getTournament().getCurrentRoundPlayerNumber()).setPlayboard(playerBoard);
        model.getTournament().getCurrentTurn().setDiceToBePlaced(prova1);
        SkipTurnAction skipTurnAction = new SkipTurnAction(model,  "test", Utils.ACTIONTYPE_SKIP_NEXT_TURN);
        skipTurnAction.setCol(2);
        skipTurnAction.setRow(0);
        skipTurnAction.applyAction();
        assertEquals("John", model.getTournament().getPlayerThatSkipTurn().get(0).getPlayerNickname());
    }
}
