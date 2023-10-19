package it.polimi.ingsw.model.dice;

import org.junit.Test;


import java.util.ArrayList;

import static it.polimi.ingsw.utils.Utils.MAX_DICE_FOR_DICEBAG;
import static it.polimi.ingsw.utils.Utils.MAX_ROUND;
import static org.junit.Assert.assertNotNull;

public class DiceBagTest {

    private DiceBag diceBag = new DiceBag();
    private ArrayList colors;

    /**
     * Verify that when the Dice Bag is created, it is full
     */
    @Test
    public void diceBagSizeTest_WhenCreatedIsfull() {

        assert(MAX_DICE_FOR_DICEBAG == diceBag.diceBagSize());
    }

    /**
     * Verify that there are at least one Dice in the Dice Bag
     */
    @Test
    public void diceBagSizeTest(){

        assert(diceBag.diceBagSize() > 0);
    }

    /**
     * When a dice is removed from Dice Bag, the Dice Bag size is decreased
     */
    @Test
    public void removeDiceFromBagTest(){

        diceBag.removeDiceFromBag();
        assert (diceBag.diceBagSize() == MAX_DICE_FOR_DICEBAG -1);
    }

    /**
     * When the Dice Bag is shuffled the number of its elements will be the same
     */
    @Test
    public void shuffleDiceBagTest_numElemNotChange() {

        DiceBag diceBagOld = diceBag;
        diceBag.shuffleDiceBag();
        assert(diceBagOld.diceBagSize()== diceBag.diceBagSize());

    }

    /**
     * Check the dice left in the Dice Bag, based on the number of player.
     * The number of dices takes from Dice Bag every round is always 2 times the number of players plus one.
     * Finally the Dice Bag size will be the max number of dices less the dices removed every round.
     */
    @Test
    public void checkDiceLeftinTheDicebag_AtTheEnd() {


        for (int n = 2; n <= 4; n++) {

            DiceBag bag = new DiceBag();

            for (int round = 1; round <= MAX_ROUND; round++) {

                DraftPool draftPool = new DraftPool(bag, n);
                draftPool.draftDice();
                for(int i=1; i<=2*n; i++)
                    draftPool.removeFromDraftPool();
            }

            //temp is the number of dices in the draft pool at teh beginning of every turn
            int temp= 2*n+1;

            assert(MAX_DICE_FOR_DICEBAG-(temp)*MAX_ROUND == bag.diceBagSize());
        }

    }

    /**
     * Verify that the Dicebag method toString retrieves a not null value
     */
    @Test
    public void toStringTest(){
        DiceBag diceBag = new DiceBag();
        assertNotNull(diceBag.toString());
    }

}