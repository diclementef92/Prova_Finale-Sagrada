package it.polimi.ingsw.model.cards.toolcards.actions;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.dice.DiceBag;
import it.polimi.ingsw.model.dice.DraftPool;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerBoard;
import it.polimi.ingsw.model.windowpattern.WindowPatternCard;
import it.polimi.ingsw.utils.Utils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SwapDiceActionTest {

    /**
     * Verify that the swap dice action is valid
     */
    @Test
    public void checkValidityTest(){
        ArrayList<Player> players = new ArrayList();
        players.add(new Player("John"));
        players.add(new Player("Jack"));
        Model model = new Model(players);
        SwapDiceAction swapDiceAction = new SwapDiceAction(model, "test", Utils.ACTIONTYPE_SWAP_DICE_FROM_DICEBAG);
        assertTrue(swapDiceAction.checkActionValidity());
    }

    /**
     * Verify that the dice swapped is correctly in the roundtrack
     */
    @Test
    public void applyActionTest(){
        DraftPool draftPool = new DraftPool(new DiceBag(), 2);
        draftPool.draftDice();
        ArrayList<Player> players = new ArrayList();
        players.add(new Player("John"));
        players.add(new Player("Jack"));
        Model model = new Model(players);
        model.getTournament().startGame();
        model.setCurrentPlayerNumber(0);
        model.getGameBoard().getRoundTrack().insertDiceInRoundtrack(draftPool);
        draftPool.draftDice();
        Dice exdraft = model.getGameBoard().getDraftPool().getListDraftPoolDice().get(0);
        SwapDiceAction swapDiceAction = new SwapDiceAction(model, "test", Utils.ACTIONTYPE_SWAP_DICE_FROM_ROUNDTRACK);
        swapDiceAction.setRound(1);
        swapDiceAction.setDiceIndex(0);
        swapDiceAction.setDraftpool(0);
        swapDiceAction.applyAction();
        assertTrue( model.getGameBoard().getRoundTrack().getDicePerRound(1).contains(exdraft));
    }
}
