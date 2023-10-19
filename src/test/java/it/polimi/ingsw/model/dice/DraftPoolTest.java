package it.polimi.ingsw.model.dice;


import it.polimi.ingsw.utils.Color;
import it.polimi.ingsw.utils.Utils;
import org.junit.Test;

import static it.polimi.ingsw.utils.Utils.NUMBER_DICE_SHADES;
import static junit.framework.TestCase.*;



public class DraftPoolTest {


    /**
     * When the round start, verify that the dices in the Draft Pool are exactly 2 times the number of players plus one
     */
    @Test
    public void getSizeDraftPoolTest_AtTheBeginningOfTheRound(){
        for(int i=2;i<=4;i++){

            DiceBag diceBag = new DiceBag();
            DraftPool draftpool = new DraftPool(diceBag, i);

            assertEquals(0, draftpool.getSizeDraftpool());
        }
    }

    /**
     * When the round is finished, which dimension depends on the number of the player, verify that there is at least one dice left
     * */
    @Test
    public void getSizeDraftPoolTest_AtTheEndOfTheRound(){

        for(int i=2;i<=4;i++){

            DiceBag diceBag = new DiceBag();
            DraftPool draftpool = new DraftPool(diceBag, i);
            draftpool.draftDice();

            for (int j = 1; j <= (2 * i); j++) {

                draftpool.removeFromDraftPool();
            }
            assertEquals(1,draftpool.getSizeDraftpool());
        }
    }

    /**
     * When re roll every dice of the Draft Pool values are still in the right range
     */
    @Test
    public void reRollAllTest_ValidValues() {
        int n;
        for(int i=2;i<=4;i++) {

            DiceBag diceBag = new DiceBag();

            DraftPool draftpool = new DraftPool(diceBag, i);

            draftpool.draftDice();
            draftpool.reRollAll();

            for (int j = 1; j <= (2 * i); j++) {
                Dice dice = draftpool.removeFromDraftPool();
                n = dice.getValue();
                if (n < 1 || n > NUMBER_DICE_SHADES) {
                    fail("invalid value for dice");
                }
            }

        }
    }

    /**
     * Verify that the number of dices in draftpool are the expected with two players
     */
    @Test
    public void draftDiceTest(){
        DiceBag diceBag = new DiceBag();
        DraftPool draftPool = new DraftPool(diceBag, 2);
        draftPool.draftDice();
        assertEquals(5, draftPool.getListDraftPoolDice().size());
    }

    /**
     * Verify that the draftpool method toString retrieves the expected value when is empty
     */
    @Test
    public void toStringTest1(){
        DiceBag diceBag = new DiceBag();
        DraftPool draftPool = new DraftPool(diceBag, 2);
        assertEquals(Utils.DRAFTPOOL_EMPTY, draftPool.toString());
    }

    /**
     * Verify that the draftpool method toString retrieves the expected value after drafting for two players
     */
    @Test
    public void toStringTest2(){
        DiceBag diceBag = new DiceBag();
        DraftPool draftPool = new DraftPool(diceBag, 2);
        draftPool.draftDice();
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < 5; k++){
            for (Dice dice : draftPool.getListDraftPoolDice()){
                sb.append(Color.returnASCIIStringFormatted(dice.getColor(), Dice.diceFaces(dice.getValue())[k]));
            }
            sb.append("\n");
        }
        assertEquals(sb.toString(), draftPool.toString());
    }

}