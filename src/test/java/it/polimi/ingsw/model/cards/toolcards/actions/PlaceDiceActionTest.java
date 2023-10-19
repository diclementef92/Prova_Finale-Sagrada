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

public class PlaceDiceActionTest {

    /**
     * Verify that the place dice action is available in a empty cell
     */
    @Test
    public void checkMainActionValidityTest(){
        String[] test = {
                "y", "b" , "empty" , "empty" , "empty" ,
                "y","empty", "5" , "b", "empty" ,
                "3" , "r", "y", "empty" , "b",
                "empty" , "empty" , "empty","y", "empty"};
        WindowPatternCard windowPatternCard = new WindowPatternCard("name",5,test);
        PlayerBoard playerBoard = new PlayerBoard("color",windowPatternCard);
        Dice prova1 = new Dice("red");
        List<Player> players = new ArrayList();
        players.add(new Player("John"));
        players.add(new Player("Jack"));
        Model model = new Model(players);
        model.getTournament().startGame();
        model.setCurrentPlayerNumber(0);
        model.getPlayers().get(model.getTournament().getCurrentRoundPlayerNumber()).setPlayboard(playerBoard);
        PlaceDiceAction placeDiceAction = new PlaceDiceAction(model, prova1, 0, 2);
        assertTrue(placeDiceAction.checkMainActionValidity());
    }

    /**
     * Verify that the place dice action in case of toolcard is available in a empty cell
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
        List<Player> players = new ArrayList();
        players.add(new Player("John"));
        players.add(new Player("Jack"));
        Model model = new Model(players);
        model.getTournament().startGame();
        model.setCurrentPlayerNumber(0);
        model.getPlayers().get(model.getTournament().getCurrentRoundPlayerNumber()).setPlayboard(playerBoard);
        PlaceDiceAction placeDiceAction = new PlaceDiceAction(model, Utils.ACTIONTYPE_PLACE_DICE_WITH_ALL_RESTRICTIONS,Utils.ACTIONTYPE_PLACE_DICE_WITH_ALL_RESTRICTIONS);
        placeDiceAction.setCol(2);
        placeDiceAction.setRow(0);
        placeDiceAction.setDice(prova1);
        assertTrue(placeDiceAction.checkMainActionValidity());
    }

    /**
     * Verify that the dice in the cell is the expected after place dice action
     */
    /*@Test
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
        model.getGameBoard().getDraftPool().getListDraftPoolDice().add(prova1);
        PlaceDiceAction placeDiceAction = new PlaceDiceAction(model, Utils.ACTIONTYPE_PLACE_DICE_WITH_ALL_RESTRICTIONS,Utils.ACTIONTYPE_PLACE_DICE_WITH_ALL_RESTRICTIONS);
        placeDiceAction.setCol(2);
        placeDiceAction.setRow(0);
        model.getTournament().getCurrentTurn().setDiceToBePlaced(prova1);
        placeDiceAction.applyAction();
        assertEquals(prova1, model.getPlayers().get(model.getCurrentPlayerNumber()).getPlayerBoard().getWindowboard().getCell(0,2).getDice());
    }*/
}
