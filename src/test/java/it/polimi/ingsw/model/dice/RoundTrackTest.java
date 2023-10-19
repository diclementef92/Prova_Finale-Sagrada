package it.polimi.ingsw.model.dice;


import static org.junit.Assert.*;

import it.polimi.ingsw.utils.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class RoundTrackTest {

    /**
     * Verify that the value of the number of round is one at the beginning of the game
     */
    @Test
    public void getNumberofRoundTest_atTheBeginningOfTheGame() {
        RoundTrack roundTrack = new RoundTrack();
        DiceBag diceBag = new DiceBag();
        DraftPool draftPool = new DraftPool(diceBag, 2);
        assertEquals(1, roundTrack.getNumberofRound());
    }

    /**
     * Verify that all the dices left in the Draft Pool on the first round, are in the first position of the Round Track
     */
    @Test
    public void insertDiceTest_allDicesLeftinDraftpoolAreInTheRoundtrackFirstPosition() {
        RoundTrack roundTrack = new RoundTrack();
        DiceBag diceBag = new DiceBag();
        DraftPool draftPool = new DraftPool(diceBag, 2);
        draftPool.draftDice();
        roundTrack.insertDiceInRoundtrack(draftPool);
        assertEquals(5, roundTrack.getDicePerRound(1).size());
    }

    /**
     * Verify that the value of the number of round is one at the beginning of the game
     */
    @Test
    public void getNumberofRoundTest(){
        RoundTrack roundTrack = new RoundTrack();
        assertEquals(1, roundTrack.getNumberofRound());
    }

    /**
     * Verify that is allowed to increment the number of round
     */
    @Test
    public void incrementRoundTest(){
        RoundTrack roundTrack = new RoundTrack();
        roundTrack.incrementRound();
        assertEquals(2, roundTrack.getNumberofRound());
    }

    /**
     * Verify that the roundtrack method toString retrieves the expected value when is empty
     */
    @Test
    public void getDiceRoundTrackTest1(){
        RoundTrack roundTrack = new RoundTrack();
        assertEquals(Utils.ROUNDTRACK_EMPTY, roundTrack.getDiceRoundtrack());
    }

    /**
     * Verify that the roundtrack method toString retrieves the expected value after incrementing round
     */
    @Test
    public void getDiceRoundTrackTest2(){
        DiceBag diceBag = new DiceBag();
        DraftPool draftPool = new DraftPool(diceBag, 2);
        draftPool.draftDice();
        RoundTrack roundTrack = new RoundTrack();
        roundTrack.insertDiceInRoundtrack(draftPool);
        roundTrack.incrementRound();
        assertEquals("Round: "+1+" - Dice: "+ roundTrack.getDicePerRound(1).toString(), roundTrack.getDiceRoundtrack());
    }

    /**
     * Verify that the roundtrack method toString retrieves the expected value when is empty
     */
    @Test
    public void toStringTest1(){
        RoundTrack roundTrack = new RoundTrack();
        assertEquals(Utils.ROUNDTRACK_EMPTY, roundTrack.toString());
    }

    /**
     * Verify that the roundtrack method toString retrieves the expected value at the end of first round
     */
    @Test
    public  void toStringTest2(){
        DiceBag diceBag = new DiceBag();
        DraftPool draftPool = new DraftPool(diceBag, 2);
        draftPool.draftDice();
        RoundTrack roundTrack = new RoundTrack();
        roundTrack.insertDiceInRoundtrack(draftPool);
        roundTrack.incrementRound();
        assertEquals(roundTrack.getDiceRoundtrack() + " " + roundTrack.getNumberofRound(), roundTrack.toString());
    }

    /**
     * Verify that the roundtrack method fullColoredString retrieves a not null value after insert dices from draftpool
     */
    @Test
    public void fullColoredString(){
        DiceBag diceBag = new DiceBag();
        DraftPool draftPool = new DraftPool(diceBag, 2);
        draftPool.draftDice();
        RoundTrack roundTrack = new RoundTrack();
        roundTrack.insertDiceInRoundtrack(draftPool);
        assertNotNull(roundTrack.fullColoredString());
    }
}