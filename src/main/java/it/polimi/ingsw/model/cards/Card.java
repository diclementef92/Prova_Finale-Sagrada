package it.polimi.ingsw.model.cards;

import java.io.Serializable;

/**
 * Card Class
 * This class is an abstract class meant to be extended by othe objects
 */
public abstract class Card implements Serializable {

    /**
     * Name of the Card
     */
    private String name;
    /**
     * Description of the card
     */
    private String description;

    /**
     * Main constructor of the class
     * It is never called as it is not supposed to
     */
    public Card() {
    }

    /**
     * Implemented constructor of a Card where values are passed via super
     * It takes the name and the description of the card
     *
     * @param name        string that contains the name of the card
     * @param description string that contains the description of the card
     */
    public Card(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Getter used to retrieve the name of the card
     *
     * @return a string containing the name of the card
     */
    public String getName() {
        return name;
    }

    /**
     * Getter used to retrieve the description of the card
     *
     * @return a string containing the name of the card
     */
    public String getDescription() {
        return description;
    }


}
