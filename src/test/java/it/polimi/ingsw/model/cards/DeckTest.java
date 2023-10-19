package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.cards.publics.ColorVariety;
import it.polimi.ingsw.model.cards.publics.ColumnColorVariety;
import it.polimi.ingsw.model.cards.publics.RowColorVariety;
import it.polimi.ingsw.model.cards.publics.RowShadeVariety;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DeckTest {

    /**
     * Verify that the name of the card picked from deck is the expected
     */
    @Test
    public void pickCard() {
        ColorVariety colorVariety = new ColorVariety("Color Variety", "temp", 4);
        ColumnColorVariety columnColorVariety = new ColumnColorVariety("Column Color Variety", "temp", 5);
        RowColorVariety rowColorVariety = new RowColorVariety("Row Color Variety", "temp", 6);
        RowShadeVariety rowShadeVariety = new RowShadeVariety("Row Shade Variety", "temp", 5);
        Deck deck = new Deck();
        deck.getDeckCards().add(colorVariety);
        deck.getDeckCards().add(columnColorVariety);
        deck.getDeckCards().add(rowColorVariety);
        deck.getDeckCards().add(rowShadeVariety);
        assertEquals("Color Variety", deck.pickCard().getName());
    }

    /**
     * Verify that the cards from deck are the expected
     */
    @Test
    public void getDeckCardsTest(){
        ColorVariety colorVariety = new ColorVariety("Color Variety", "temp", 4);
        ColumnColorVariety columnColorVariety = new ColumnColorVariety("Column Color Variety", "temp", 5);
        RowColorVariety rowColorVariety = new RowColorVariety("Row Color Variety", "temp", 6);
        RowShadeVariety rowShadeVariety = new RowShadeVariety("Row Shade Variety", "temp", 5);
        List<Card> cards = new ArrayList<>();
        cards.add(colorVariety);
        cards.add(columnColorVariety);
        cards.add(rowColorVariety);
        cards.add(rowShadeVariety);
        Deck deck = new Deck();
        deck.getDeckCards().add(colorVariety);
        deck.getDeckCards().add(columnColorVariety);
        deck.getDeckCards().add(rowColorVariety);
        deck.getDeckCards().add(rowShadeVariety);
        assertEquals(cards, deck.getDeckCards());
    }

    /**
     * Verify that the method toString retrieves a not null value
     */
    @Test
    public void toStringTest(){
        ColorVariety colorVariety = new ColorVariety("Color Variety", "temp", 4);
        ColumnColorVariety columnColorVariety = new ColumnColorVariety("Column Color Variety", "temp", 5);
        RowColorVariety rowColorVariety = new RowColorVariety("Row Color Variety", "temp", 6);
        RowShadeVariety rowShadeVariety = new RowShadeVariety("Row Shade Variety", "temp", 5);
        Deck deck = new Deck();
        deck.getDeckCards().add(colorVariety);
        deck.getDeckCards().add(columnColorVariety);
        deck.getDeckCards().add(rowColorVariety);
        deck.getDeckCards().add(rowShadeVariety);
        assertNotNull(deck.toString());
    }
}