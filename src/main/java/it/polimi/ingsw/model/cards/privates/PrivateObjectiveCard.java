package it.polimi.ingsw.model.cards.privates;


import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.player.PlayerBoard;

import static it.polimi.ingsw.utils.Utils.MAX_COLUMN_WINDOW_PATTERN;
import static it.polimi.ingsw.utils.Utils.MAX_ROW_WINDOW_PATTERN;

/**
 * Private Objective Card
 */
public class PrivateObjectiveCard extends Card {

    /**
     * Color of the card
     */
    private String color;
    /**
     * Score of the card
     */
    private int score;

    /**
     * Main constructor of the Private Objective card
     *
     * @param name        name required for the card (passed up with super)
     * @param description description of the card (passed up with super)
     * @param color       String color of the card
     */
    public PrivateObjectiveCard(String name, String description, String color) {
        super(name, description);
        this.color = color;
        this.score = 0;
    }

    /**
     * Getter of the color of this Private Objective Card
     *
     * @return a string defining a color of this card
     */
    public String getColorPrivate() {
        return color;
    }

    /**
     * Getter to retrieve the score of this Private Objective Card
     *
     * @return an integer defining the current score od this Private Objective Card
     */
    public int getScore() {
        return score;
    }

    /**
     * This method is used to calculate the score for a specific player
     * applied by this card
     *
     * @param playerBoard Input player board where this card has to be applied
     * @return an integer with the score obatined by the application of this card over the player's gameboard
     */
    public int returnScore(PlayerBoard playerBoard) {

        for (int i = 0; i < MAX_ROW_WINDOW_PATTERN; i++) {
            for (int j = 0; j < MAX_COLUMN_WINDOW_PATTERN; j++) {
                if (playerBoard.getWindowboard().getCell(i, j).getDice() != null) {
                    if (playerBoard.getWindowboard().getCell(i, j).getDice().getColor().equals(getColorPrivate())) {
                        int sum = playerBoard.getWindowboard().getCell(i, j).getDice().getValue();
                        score = score + sum;
                    }
                }
            }
        }
        return score;
    }


    /**
     * Defined method toString that retrieves information about the Card
     *
     * @return a string that describes the card
     */
    public String toString() {
        return "------\n" +
                "Name: " + getName() + "\n" +
                "Description: " + getDescription() + "\n" +
                "Color: " + getColorPrivate() + "\n" +
                "------\n";
    }
}



