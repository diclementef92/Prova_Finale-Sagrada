package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.AllCardsTest;
import it.polimi.ingsw.model.dice.DiceBagTest;
import it.polimi.ingsw.model.dice.DiceTest;
import it.polimi.ingsw.model.dice.DraftPoolTest;
import it.polimi.ingsw.model.dice.RoundTrackTest;
import it.polimi.ingsw.model.player.PlayerBoardTest;
import it.polimi.ingsw.model.player.PlayerTest;
import it.polimi.ingsw.model.windowpattern.CellTest;
import it.polimi.ingsw.model.windowpattern.WindowPatternCardTest;
import it.polimi.ingsw.model.windowpattern.WindowPatternDeckTest;
import org.junit.Test;

public class AllModelTest {

    /**
     * Method used for testing all model classes
     */
    @Test
    public void allModelTest(){
        GameBoardTest gameBoardTest = new GameBoardTest();
        ModelTest modelTest = new ModelTest();
        CellTest cellTest = new CellTest();
        WindowPatternCardTest windowPatternCardTest = new WindowPatternCardTest();
        WindowPatternDeckTest windowPatternDeckTest = new WindowPatternDeckTest();
        PlayerBoardTest playerBoardTest = new PlayerBoardTest();
        PlayerTest playerTest = new PlayerTest();
        DiceBagTest diceBagTest = new DiceBagTest();
        DiceTest diceTest = new DiceTest();
        DraftPoolTest draftPoolTest = new DraftPoolTest();
        RoundTrackTest roundTrackTest = new RoundTrackTest();
        AllCardsTest AllCardsTest = new AllCardsTest();

        gameBoardTest.getBag();
        gameBoardTest.getRounds();
        gameBoardTest.getDratPoolTest();
        gameBoardTest.getPrivatesInGame();
        gameBoardTest.getPrivatesTest();
        gameBoardTest.getPublicsInGameTest();
        gameBoardTest.getPublicsTest();
        gameBoardTest.getToolCardsInGameTest();
        gameBoardTest.getToolCardsTest();
        gameBoardTest.getWindowPatternDeckTest();
        gameBoardTest.toStringTest1();
        gameBoardTest.toStringTest2();
        modelTest.getCurrentPlayerNumberTest();
        modelTest.getPlayersTest();
        modelTest.setCurrentPlayerNumberTest();
        modelTest.getGameBoardTest();
        modelTest.getTournamentTest();
        cellTest.getColorTest();
        cellTest.getDiceTest_cellWithoutDiceAtBeginning();
        cellTest.getValueTest();
        cellTest.setDiceTest();
        cellTest.getDiceTest();
        cellTest.toStringTest();
        windowPatternCardTest.getDifficultyTest();
        windowPatternCardTest.getNameTest();
        windowPatternCardTest.insertDieTest_ColoredCell();
        windowPatternCardTest.insertDieTest_NumericCell();
        windowPatternCardTest.getCellTest();
        windowPatternCardTest.toStringTest();
        windowPatternDeckTest.getSizeWindowPatternDeckTest();
        windowPatternDeckTest.WindowPatternDeckTest_numberOfWindows();
        windowPatternDeckTest.takeCardFromWPDeckTest();
        windowPatternDeckTest.toStringTest();
        playerBoardTest.checkValidityPlacementTest_diceInaColoredCellRight();
        playerBoardTest.checkValidityPlacementTest_diceInaNumericCellRight();
        playerBoardTest.checkValidityPlacementTest_diceInaOccupiedCell();
        playerBoardTest.getColorTest();
        playerBoardTest.getWindowboardTest();
        playerBoardTest.placeDiceTest();
        playerBoardTest.checkColorValidity();
        playerBoardTest.checkIfCellIsEmptyTest();
        playerBoardTest.checkIfCornersOrEdgeTest();
        playerBoardTest.checkIfDicesAround();
        playerBoardTest.checkIfOrthogonalIgnoreSelf();
        playerBoardTest.checkIfWhiteCell();
        playerBoardTest.checkisFirstDice();
        playerBoardTest.checkValueValiditiy();
        playerBoardTest.coloredFaceTest();
        playerBoardTest.emptyFacesTest();
        playerBoardTest.fullColoredStringTest();
        playerBoardTest.toStringTest();
        playerBoardTest.veryBigNumber();
        playerTest.getFavorTokenAmount();
        playerTest.getPlayerBoard();
        playerTest.getPlayerNickname();
        playerTest.getPrivateCard();
        playerTest.toStringTest();
        diceBagTest.checkDiceLeftinTheDicebag_AtTheEnd();
        diceBagTest.diceBagSizeTest();
        diceBagTest.diceBagSizeTest_WhenCreatedIsfull();
        diceBagTest.removeDiceFromBagTest();
        diceBagTest.shuffleDiceBagTest_numElemNotChange();
        diceBagTest.toStringTest();
        diceTest.decreaseValueTest();
        diceTest.decreaseValueTest_underMin();
        diceTest.getColorTest();
        diceTest.increaseValueTest();
        diceTest.increaseValueTest_overMax();
        diceTest.rollDiceTest_valueintheRange();
        diceTest.setValueTest();
        diceTest.diceFacesTest();
        diceTest.toColorString();
        diceTest.toStringTest();
        draftPoolTest.getSizeDraftPoolTest_AtTheBeginningOfTheRound();
        draftPoolTest.getSizeDraftPoolTest_AtTheEndOfTheRound();
        draftPoolTest.reRollAllTest_ValidValues();
        draftPoolTest.draftDiceTest();
        draftPoolTest.toStringTest1();
        draftPoolTest.toStringTest2();
        roundTrackTest.getNumberofRoundTest_atTheBeginningOfTheGame();
        roundTrackTest.insertDiceTest_allDicesLeftinDraftpoolAreInTheRoundtrackFirstPosition();
        roundTrackTest.getNumberofRoundTest();
        roundTrackTest.fullColoredString();
        roundTrackTest.getDiceRoundTrackTest1();
        roundTrackTest.getDiceRoundTrackTest2();
        roundTrackTest.incrementRoundTest();
        roundTrackTest.toStringTest1();
        roundTrackTest.toStringTest2();
        AllCardsTest.allCardsTest();
    }
}
