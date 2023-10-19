package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.windowpattern.WindowPatternCard;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;



public class PlayerBoardTest {



    /**
     * Verify that the color of the player board is the same selected by the player
     */
    @Test
    public void getColorTest() {
        String[] test = {"red","empty","blue","empty","yellow",
                "4","purple","3","green","2",
                "empty","1","empty","5","empty",
                "empty","empty","6","empty","empty"};

        WindowPatternCard wpcard = new WindowPatternCard("test",4,test);
        PlayerBoard playerBoard = new PlayerBoard("red",wpcard);
        assertEquals("red", playerBoard.getColor());
    }

    /**
     * Verify that the window pattern card in the player board is the same selected by the player
     */
    @Test
    public void getWindowboardTest() {
        String[] test = {"red","empty","blue","empty","yellow",
                "4","purple","3","green","2",
                "empty","1","empty","5","empty",
                "empty","empty","6","empty","empty"};

        WindowPatternCard wpcard = new WindowPatternCard("test",4,test);
        PlayerBoard playerBoard = new PlayerBoard("red",wpcard);
        assertEquals(wpcard,playerBoard.getWindowboard());
    }


    /**
     * Try to insert a dice in a empty cell
     */
    @Test
    public void placeDiceTest() {
        String[] test = {"red","empty","blue","empty","yellow",
                "4","purple","3","green","2",
                "empty","1","empty","5","empty",
                "empty","empty","6","empty","empty"};

        WindowPatternCard wpcard = new WindowPatternCard("test",4,test);
        PlayerBoard playerBoard = new PlayerBoard("red",wpcard);
        Dice dice = new Dice("red");
        dice.setValue(2);
        playerBoard.placeDice(dice,0,3);
        assertEquals(2, playerBoard.getWindowboard().getCell(0,3).getDice().getValue());

    }


    /**
     * Try to insert a dice in a cell of the same color
     */
    @Test
    public void checkValidityPlacementTest_diceInaColoredCellRight(){
        String[] test = {"red","empty","blue","empty","yellow",
                "4","purple","3","green","2",
                "empty","1","empty","5","empty",
                "empty","empty","6","empty","empty"};

        WindowPatternCard wpcard = new WindowPatternCard("test",4,test);
        PlayerBoard playerBoard = new PlayerBoard("red",wpcard);
        Dice red = new Dice("red");
        red.setValue(1);
        if(playerBoard.checkValidityPlacement(red,0,0,0,0)){
            playerBoard.placeDice(red,0,0);

        }else{
            fail("error: red dice in a red cell, should be right but is not allowed");
        }

    }

    /**
     *
     */
    /*
    @Test
    public void checkValidityPlacementTest_diceInaColoredCellWrong(){

        Dice blue = new Dice("blue");
        blue.setValue(1);
        if(playerBoard.checkValidityPlacement(blue,0,0)){

            fail("error: blue dice in a red cell, should be wrong but is allowed");

        }

    }*/

    /**
     * Try to insert a dice twice in the same cell
     */
    @Test
    public void checkValidityPlacementTest_diceInaOccupiedCell() {
        String[] test = {"red","empty","blue","empty","yellow",
                "4","purple","3","green","2",
                "empty","1","empty","5","empty",
                "empty","empty","6","empty","empty"};

        WindowPatternCard wpcard = new WindowPatternCard("test",4,test);
        PlayerBoard playerBoard = new PlayerBoard("red",wpcard);
        Dice first = new Dice("red");
        first.setValue(1);

        if(playerBoard.checkValidityPlacement(first,0,0,0,0)){
            playerBoard.placeDice(first,0,0);

        }else{
            fail("error: red dice in a red cell, should be right but method said is not allowed");
        }

        Dice second = new Dice("red");
        second.setValue(1);

        assertFalse(playerBoard.checkValidityPlacement(second,0,0,0,0));

    }

    /**
     * Verify is allowed to place a red dice in a red cell
     */
    @Test
    public void checkValidityPlacementTest_diceInaNumericCellRight() {
        String[] test = {"red","empty","blue","empty","yellow",
                "4","purple","3","green","2",
                "empty","1","empty","5","empty",
                "empty","empty","6","empty","empty"};

        WindowPatternCard wpcard = new WindowPatternCard("test",4,test);
        PlayerBoard playerBoard = new PlayerBoard("red",wpcard);
        Dice four = new Dice("red");
        four.setValue(4);

        if(playerBoard.checkValidityPlacement(four,1,0,0,0)){
            playerBoard.placeDice(four,1,0);

        }else{
            fail("error: dice with value 4 in a 4 shade cell, should be right but method said is not allowed");
        }

    }

    /**
     * Try to insert a dice in a cell with a different shade
     */
    /*
    @Test
    public void checkValidityPlacementTest_diceInaNumericCellWrong() {

        Dice four = new Dice("red");
        four.setValue(1);

        if(playerBoard.checkValidityPlacement(four,1,0)){

            fail("error: dice with value 1 in a 4 shade cell, should be wrong but method said is allowed");
        }

    }*/

    /**
     * Verify that an empty cell on board is recognized correctly
     * Empty cell means without dice
     */
    @Test
    public void checkIfCellIsEmptyTest(){
        String[] test = {"red","empty","blue","empty","yellow",
                "4","purple","3","green","2",
                "empty","1","empty","5","empty",
                "empty","empty","6","empty","empty"};

        WindowPatternCard wpcard = new WindowPatternCard("test",4,test);
        PlayerBoard playerBoard = new PlayerBoard("red",wpcard);
        assertTrue(playerBoard.checkIfCellIsEmpty(0,1));
    }

    /**
     * Verify that an empty player board is recognized correctly
     */
    @Test
    public void checkisFirstDice(){
        String[] test = {"red","empty","blue","empty","yellow",
                "4","purple","3","green","2",
                "empty","1","empty","5","empty",
                "empty","empty","6","empty","empty"};

        WindowPatternCard wpcard = new WindowPatternCard("test",4,test);
        PlayerBoard playerBoard = new PlayerBoard("red",wpcard);
        assertTrue(playerBoard.checkisFirstDice());
    }

    /**
     * Verify that a white cell on board is recognized correctly
     */
    @Test
    public void checkIfWhiteCell(){
        String[] test = {"red","empty","blue","empty","yellow",
                "4","purple","3","green","2",
                "empty","1","empty","5","empty",
                "empty","empty","6","empty","empty"};

        WindowPatternCard wpcard = new WindowPatternCard("test",4,test);
        PlayerBoard playerBoard = new PlayerBoard("red",wpcard);
        assertTrue(playerBoard.checkIfWhiteCell(new Dice("red"), 0, 1));
    }

    /**
     * Verify that a colored cell on board is recognized correctly
     */
    @Test
    public void checkColorValidity(){
        String[] test = {"red","empty","blue","empty","yellow",
                "4","purple","3","green","2",
                "empty","1","empty","5","empty",
                "empty","empty","6","empty","empty"};

        WindowPatternCard wpcard = new WindowPatternCard("test",4,test);
        PlayerBoard playerBoard = new PlayerBoard("red",wpcard);
        assertTrue(playerBoard.checkColorValidity(new Dice("red"), 0,0));
    }

    /**
     * Verify that if the dice value correspond to the cell value, the check method return true
     */
    @Test
    public void checkValueValiditiy(){
        String[] test = {"red","empty","blue","empty","yellow",
                "4","purple","3","green","2",
                "empty","1","empty","5","empty",
                "empty","empty","6","empty","empty"};

        WindowPatternCard wpcard = new WindowPatternCard("test",4,test);
        PlayerBoard playerBoard = new PlayerBoard("red",wpcard);
        Dice dice = new Dice("red");
        dice.setValue(2);
        assertTrue(playerBoard.checkValueValidity(dice, 1,4));
    }

    /**
     * Verify that a cell on corners or edge on player board is recognized correctly
     */
    @Test
    public void checkIfCornersOrEdgeTest(){
        String[] test = {"red","empty","blue","empty","yellow",
                "4","purple","3","green","2",
                "empty","1","empty","5","empty",
                "empty","empty","6","empty","empty"};

        WindowPatternCard wpcard = new WindowPatternCard("test",4,test);
        PlayerBoard playerBoard = new PlayerBoard("red",wpcard);
        assertTrue(playerBoard.checkIfCornersOrEdge(0,1));
    }

    /**
     * Verify that if a cell doesn't have any dices around, the check method return true
     *
     */
    @Test
    public void checkIfDicesAround(){
        String[] test = {"red","empty","blue","empty","yellow",
                "4","purple","3","green","2",
                "empty","1","empty","5","empty",
                "empty","empty","6","empty","empty"};

        WindowPatternCard wpcard = new WindowPatternCard("test",4,test);
        PlayerBoard playerBoard = new PlayerBoard("red",wpcard);
        Dice dice = new Dice("red");
        dice.setValue(2);
        playerBoard.placeDice(dice, 0,1);
        assertTrue(playerBoard.checkIfDicesAround(0,0, -3 ,-3));
    }

    /**
     *
     */
    @Test
    public void checkIfOrthogonalIgnoreSelf(){
        String[] test = {"red","empty","blue","empty","yellow",
                "4","purple","3","green","2",
                "empty","1","empty","5","empty",
                "empty","empty","6","empty","empty"};

        WindowPatternCard wpcard = new WindowPatternCard("test",4,test);
        PlayerBoard playerBoard = new PlayerBoard("red",wpcard);
        Dice dice = new Dice("red");
        dice.setValue(2);
        assertTrue(playerBoard.checkIfOrthogonalIgnoreSelf(dice, 4,4,0,0));
    }

    /**
     * Verify that the playerboard method fullColoredString retrieves a not null value
     */
    @Test
    public void fullColoredStringTest(){
        String[] test = {"red","empty","blue","empty","yellow",
                "4","purple","3","green","2",
                "empty","1","empty","5","empty",
                "empty","empty","6","empty","empty"};

        WindowPatternCard wpcard = new WindowPatternCard("test",4,test);
        PlayerBoard playerBoard = new PlayerBoard("red",wpcard);
        assertNotNull(playerBoard.fullColoredString());
    }

    /**
     * Verify that the playerboard method toString retrieves a not null value
     */
    @Test
    public void toStringTest(){
        String[] test = {"red","empty","blue","empty","yellow",
                "4","purple","3","green","2",
                "empty","1","empty","5","empty",
                "empty","empty","6","empty","empty"};

        WindowPatternCard wpcard = new WindowPatternCard("test",4,test);
        PlayerBoard playerBoard = new PlayerBoard("red",wpcard);
        assertNotNull(playerBoard.toString());
    }

    /**
     * Verify that the playerboard method for drawing empty cells retrieves the expected value
     */
    @Test
    public void emptyFacesTest(){
        String [] test = {"┏━━━━━━━┓", "┃       ┃", "┃       ┃", "┃       ┃", "┗━━━━━━━┛"};
        assertTrue(Arrays.equals(test, PlayerBoard.emptyFace()));
    }

    /**
     * Verify that the playerboard method for drawing colored cells retrieves the expected value
     */
    @Test
    public void coloredFaceTest(){
        String [] test = {"┏━━━━━━━┓", "┃███████┃", "┃███████┃", "┃███████┃", "┗━━━━━━━┛"};
        assertTrue(Arrays.equals(test, PlayerBoard.coloredFace()));
    }

    /**
     * Verify that the playerboard method for drawing cells with numbers retrieves the expected value
     */
    @Test
    public void veryBigNumber(){
        String [] test = {"┏━━━━━━━┓", "┃  ╺━┓  ┃", "┃   ━┫  ┃", "┃  ╺━┛  ┃", "┗━━━━━━━┛"};
        assertTrue(Arrays.equals(test, PlayerBoard.veryBigNumber(3)));
    }
}