package it.polimi.ingsw.model.cards.publics;

import it.polimi.ingsw.model.cards.publics.PublicObjectiveCard;
import it.polimi.ingsw.model.player.PlayerBoard;

/**
 * Color Diagonals card
 * Extension of Public Objective Card
 */
public class ColorDiagonals extends PublicObjectiveCard {

    /**
     * Main constructor of the Color Diagonals public objective card
     *
     * @param name        name of the card
     * @param description description of the card
     * @param score       score of the card
     */
    public ColorDiagonals(String name, String description, int score) {

        super(name, description, score);

    }

    @Override
    public int returnScore(PlayerBoard board) {
        return 0;
    }


}
