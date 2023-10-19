package it.polimi.ingsw;

import it.polimi.ingsw.controller.AllControllerTest;
import it.polimi.ingsw.model.AllModelTest;
import org.junit.Test;

public class AllTests {

    /**
     * Runs cascading every test in the project
     */
    @Test
    public void AllTests(){
        AllModelTest allModelTest = new AllModelTest();
        AllControllerTest allControllerTest = new AllControllerTest();
        allModelTest.allModelTest();
        allControllerTest.AllControllerTest();
    }
}
