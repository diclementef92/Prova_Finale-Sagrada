package it.polimi.ingsw.model.cards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Deck Class
 * This is a generic container for cards
 */
public class Deck implements Serializable {

    /**
     * List of Cards inside this deck
     * They can be off all types that extend Card
     */
    private ArrayList<Card> deckCards;

    /**
     * Main constructor of the Deck
     * It doesn't take any argument or parameter
     */
    public Deck() {
        deckCards = new ArrayList<>();
    }

    /**
     * Method used to pick the first card from the deck
     *
     * @return the first card from the Deck
     */
    public Card pickCard() {
        return deckCards.remove(0);
    }

    /**
     * Getter to retrieve the array list of cards in this deck
     *
     * @return an array list of cards
     */
    public List<Card> getDeckCards() {
        return this.deckCards;
    }

    /**
     * Method used for shuffle the deck and so the cards
     */
    public void shuffleDeck() {
        Collections.shuffle(deckCards);
    }

    /**
     * Defined method toString that return a string containing the information about this deck
     *
     * @return a string containing the information about this deck
     */
    public String toString() {
        StringBuilder bld = new StringBuilder();
        for (Card c : deckCards) {
            bld.append(c);
        }
        return bld.toString() + "\n";
    }
}
