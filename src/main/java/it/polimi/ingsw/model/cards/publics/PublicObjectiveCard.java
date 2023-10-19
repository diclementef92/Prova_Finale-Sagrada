package it.polimi.ingsw.model.cards.publics;


import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.player.PlayerBoard;

/**
 * Abstract class of the public objective card
 * It extends the generic card object
 */
public abstract class PublicObjectiveCard extends Card {

    /**
     * Score of the card
     */
    private int score;

    /**
     * Main constructor of the Public Objective Card
     *
     * @param name        name of the card
     * @param description description of the card
     * @param score       score of the card
     */
    public PublicObjectiveCard(String name, String description, int score) {
        super(name, description);
        this.score = score;
    }

    /**
     * Getter to retrieve the value of the score
     *
     * @return an integer of the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Method used to calculate the final score
     *
     * @param board player window board
     * @return integer as the score calculate
     */
    public abstract int returnScore(PlayerBoard board);

    /**
     * Defined method toString that retrieves information about the Card
     *
     * @return a string that describes the card
     */
    @Override
    public String toString() {
        return "\n=======\n" +
                "Name: " + getName() + "\n" +
                "Difficulty: " + getDescription() + "\n" +
                "Score:  " + getScore() + "\n" +
                "=======\n";
    }


}



