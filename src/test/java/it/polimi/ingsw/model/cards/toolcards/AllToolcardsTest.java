package it.polimi.ingsw.model.cards.toolcards;

import it.polimi.ingsw.model.cards.toolcards.actions.*;
import org.junit.Test;

public class AllToolcardsTest {

    /**
     * Method used for testing all tool cards classes
     */
    @Test
    public void allToolcardsTests(){
        ChangeDiceValueActionTest changeDiceValueActionTest = new ChangeDiceValueActionTest();
        MoveDiceActionTest moveDiceActionTest = new MoveDiceActionTest();
        PassActionTest passActionTest = new PassActionTest();
        PlaceDiceActionTest placeDiceActionTest = new PlaceDiceActionTest();
        SkipTurnActionTest skipTurnActionTest = new SkipTurnActionTest();
        SwapDiceActionTest swapDiceActionTest = new SwapDiceActionTest();
        ToolCardTest toolCardTest = new ToolCardTest();
        changeDiceValueActionTest.applyActionTest1();
        changeDiceValueActionTest.applyActionTest2();
        changeDiceValueActionTest.applyActionTest3();
        moveDiceActionTest.applyActionTest();
        passActionTest.checkIfCurrentPlayerTest();
        passActionTest.checkActionValidityTest();
        //placeDiceActionTest.applyActionTest();
        placeDiceActionTest.checkActionValidityTest();
        placeDiceActionTest.checkMainActionValidityTest();
        skipTurnActionTest.applyActionTest();
        skipTurnActionTest.checkActionValidityTest();
        swapDiceActionTest.applyActionTest();
        swapDiceActionTest.checkValidityTest();
        toolCardTest.getColorTest();
        toolCardTest.getFavorTokenNeededTest();
        toolCardTest.getFavorTokenTest();
        toolCardTest.getNumberTest();
        toolCardTest.toStringTest();
        toolCardTest.getActionToPerformTest();
        toolCardTest.getAnotherMoveDiceTest();
        toolCardTest.getChooseColorFromRoundTrackTest();
    }
}
