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
import static org.junit.Assert.assertNull;

public class MoveDiceActionTest {

    /**
     * Verify that the dice is the expected after move action
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
        Player player = model.getPlayers().get(model.getTournament().getCurrentRoundPlayerNumber());
        player.setPlayboard(playerBoard);
        player.getPlayerBoard().getWindowboard().getCell(0, 2).setDice(prova1);
        model.getGameBoard().getDraftPool().getListDraftPoolDice().add(prova1);
        MoveDiceAction moveDiceAction = new MoveDiceAction(model, "test", Utils.ACTIONTYPE_MOVE_WITH_ALL_RESTRICTIONS);
        moveDiceAction.setStartCol(2);
        moveDiceAction.setStartRow(0);
        moveDiceAction.setFinalCol(3);
        moveDiceAction.setFinalRow(0);
        moveDiceAction.applyAction();
        assertNull(model.getTournament().getRoundPlayers().get(model.getTournament().getCurrentRoundPlayerNumber()).getPlayerBoard().getWindowboard().getCell(0, 3).getDice());

    }
}
