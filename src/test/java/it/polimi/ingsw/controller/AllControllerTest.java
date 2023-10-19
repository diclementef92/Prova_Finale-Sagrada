package it.polimi.ingsw.controller;

import it.polimi.ingsw.controller.interpreter.ActionInterpreterTest;
import it.polimi.ingsw.controller.interpreter.InfoInterpreterTest;
import it.polimi.ingsw.controller.interpreter.InterpreterTest;
import it.polimi.ingsw.controller.message.requests.AbortRequestTest;
import it.polimi.ingsw.controller.message.responses.PrivateResponseTest;
import it.polimi.ingsw.controller.message.responses.SetPlayerResponseTest;
import it.polimi.ingsw.controller.message.responses.UpdateModelResponseTest;
import org.junit.Test;

public class AllControllerTest {

    /**
     * Runs cascading every test in the controller pkg
     */
    @Test
    public void AllControllerTest(){
        ControllerTest controllerTest = new ControllerTest();
        TournamentTest tournamentTest = new TournamentTest();
        TurnTest turnTest = new TurnTest();
        PrivateResponseTest privateResponseTest = new PrivateResponseTest();
        SetPlayerResponseTest setPlayerResponseTest = new SetPlayerResponseTest();
        UpdateModelResponseTest updateModelResponseTest = new UpdateModelResponseTest();
        AbortRequestTest abortRequestTest = new AbortRequestTest();
        InterpreterTest interpreterTest = new InterpreterTest();
        InfoInterpreterTest infoInterpreterTest = new InfoInterpreterTest();
        ActionInterpreterTest actionInterpreterTest = new ActionInterpreterTest();
        controllerTest.checkIfCurrentPlayerTest();
        tournamentTest.newTurnTest();
        tournamentTest.nextRoundTest1();
        tournamentTest.startGameTest();
        turnTest.getSelectDiceActionCounterTest();
        turnTest.getUseToolCardActionsCounterTest();
        turnTest.setSelectDiceActionCounterTest();
        turnTest.setUseToolCardActionsCounterTest();
        privateResponseTest.getPlayerTest();
        setPlayerResponseTest.getPlayerTest();
        updateModelResponseTest.getModelTest();
        updateModelResponseTest.getModelTest1();
        abortRequestTest.decryptTest();
        interpreterTest.parseTest1();
        interpreterTest.parseTest3();
        infoInterpreterTest.parseInfoTest();
        //actionInterpreterTest.parseActionTest1();
        actionInterpreterTest.parseActionTest3();
    }
}
