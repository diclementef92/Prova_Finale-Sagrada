package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.cards.privates.PrivateObjectiveCardTest;
import it.polimi.ingsw.model.cards.publics.AllPublicsTest;
import it.polimi.ingsw.model.cards.toolcards.AllToolcardsTest;
import org.junit.Test;

public class AllCardsTest {

    /**
     * Method used for testing all cards classes
     */
    @Test
    public void allCardsTest(){
        PrivateObjectiveCardTest privateObjectiveCardTest = new PrivateObjectiveCardTest();
        AllPublicsTest AllPublicsTest = new AllPublicsTest();
        AllToolcardsTest AllToolcardsTest = new AllToolcardsTest();
        DeckTest deckTest = new DeckTest();
        privateObjectiveCardTest.getColorPrivateTest();
        privateObjectiveCardTest.getScoreTest_AtBeginning();
        privateObjectiveCardTest.returnScoreTest();
        privateObjectiveCardTest.toStringTest();
        AllPublicsTest.allPublicsTests();
        AllToolcardsTest.allToolcardsTests();
        deckTest.getDeckCardsTest();
        deckTest.pickCard();
        deckTest.toStringTest();
    }
}
