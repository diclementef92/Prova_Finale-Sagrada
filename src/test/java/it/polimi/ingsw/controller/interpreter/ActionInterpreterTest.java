package it.polimi.ingsw.controller.interpreter;

import it.polimi.ingsw.controller.message.requests.AbortRequest;
import it.polimi.ingsw.controller.message.requests.ChangeDiceValueRequest;
import it.polimi.ingsw.controller.message.requests.PlaceDiceRequest;
import it.polimi.ingsw.controller.message.responses.PlaceDiceResponse;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.utils.Utils;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ActionInterpreterTest {
    Player player1 = new Player("Jhon");
    Player player2 = new Player("Jack");
    List<Player> players =  new ArrayList<>();



    /*
    //Cannot test placedice action, because it requires multiple inputs fro user
    @Test
    public  void parseActionTest2(){
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        int yes = 0;
        int no = 1;
        System.setIn(new ByteArrayInputStream(Integer.toString(yes).getBytes()));
        model.getGameBoard().getDraftPool().draftDice();
        model.getTournament().startGame();
        ActionInterpreter actionInterpreter = new ActionInterpreter(System.out, model, player1, System.in);
        assertTrue(actionInterpreter.parseAction(new PlaceDiceResponse(Utils.ACTION_PLACE_DICE, Utils.ACTIONTYPE_PLACE_DICE_WITH_ALL_RESTRICTIONS, model.getGameBoard().getDraftPool().getListDraftPoolDice().get(0))) instanceof PlaceDiceRequest);
    }*/

    /**
     * Verify that the interpreter recognize correctly a change dice value request
     */
    @Test
    public void parseActionTest3(){
        players.add(player1);
        players.add(player2);
        Model model = new Model(players);
        int yes = 0;
        int no = 1;
        System.setIn(new ByteArrayInputStream(Integer.toString(yes).getBytes()));
        model.getGameBoard().getDraftPool().draftDice();
        model.getTournament().startGame();
        model.getTournament().nextTurn();
        model.getTournament().nextTurn();
        ActionInterpreter actionInterpreter = new ActionInterpreter(System.out, model, player1, System.in);
        assertTrue(actionInterpreter.parseAction(new PlaceDiceResponse(Utils.ACTION_CHANGE_DICE_VALUE, Utils.ACTIONTYPE_REROLL_ALL, model.getGameBoard().getDraftPool().getListDraftPoolDice().get(0))) instanceof ChangeDiceValueRequest);
    }

    //Cannot test the other action requests, because they all require multiple user choices
}
