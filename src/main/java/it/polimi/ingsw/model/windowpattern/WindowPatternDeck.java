package it.polimi.ingsw.model.windowpattern;

import it.polimi.ingsw.utils.Parser;
import it.polimi.ingsw.utils.Utils;

import javax.rmi.CORBA.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Window Pattern Deck
 * This class is used as a collection of single Window Pattern Cards
 */
public class WindowPatternDeck implements Serializable {

    /**
     * List containing all the Window Pattern card that can be chosen
     * by the players at the beginning of game (4 for each player)
     */
    private List<WindowPatternCard> listofwindowpattern;

    /**
     * Main constructor of the Window Pattern Deck
     */
    public WindowPatternDeck() {
        listofwindowpattern = new ArrayList<>();
        createDeckofWindowPattern();
    }

    /**
     * Method used by the constructor in order to create the deck
     * of Window Pattern Cards starting from imported strings from json
     */
    private void createDeckofWindowPattern() {
        Parser parser = new Parser();
        listofwindowpattern = parser.parserWindowPatternCard();
    }

    /**
     * Method that returns a single Window Pattern Card from the deck
     * It is meant to be used to retrieve cards from the deck and give them
     * to players at the beginning of the game
     *
     * @return The first Window Pattern Card in the deck
     */
    public WindowPatternCard takeCardFromWPDeck() {
        return listofwindowpattern.remove(0);
    }

    /**
     * Method used to shuffle the Window Pattern Deck
     * It changes the disposition of cards
     */
    public void shuffleWindowPatternDeck() {
        Collections.shuffle(listofwindowpattern);
    }

    /**
     * Method used to retrieve the number of Window Pattern Card currently in the Deck
     *
     * @return an integer describing the number of cards
     */
    public int getSizeWindowPatternDeck() {
        if (listofwindowpattern.isEmpty()) {
            System.out.println(Utils.DRAFTPOOL_EMPTY);
        }
        return listofwindowpattern.size();
    }

    /**
     * Defined method toString that returns the description of the deck
     * content
     *
     * @return a string that describe the content of the deck
     */
    public String toString() {
        StringBuilder temp = new StringBuilder();
        for (WindowPatternCard w : listofwindowpattern)
            temp.append(w);

        return temp.toString();
    }


    /**
     * Getter of the list of window pattern cards in deck
     * @return a list of window pattern card
     */
    public List<WindowPatternCard> getListofwindowpattern() {
        return listofwindowpattern;
    }
}


