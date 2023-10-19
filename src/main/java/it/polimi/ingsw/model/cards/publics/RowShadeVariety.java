package it.polimi.ingsw.model.cards.publics;

import it.polimi.ingsw.model.cards.publics.PublicObjectiveCard;
import it.polimi.ingsw.model.player.PlayerBoard;

import java.util.HashSet;

import static it.polimi.ingsw.utils.Utils.MAX_COLUMN_WINDOW_PATTERN;
import static it.polimi.ingsw.utils.Utils.MAX_ROW_WINDOW_PATTERN;

/**
 * Row Shade Variety card
 * Extension of Public Objective Card
 */
public class RowShadeVariety extends PublicObjectiveCard {

    /**
     * Main constructor of the Row Shade Variety public objective card
     *
     * @param name        name of the card
     * @param description description of the card
     * @param score       score of the card
     */
    public RowShadeVariety(String name, String description, int score) {
        super(name,description, score);

    }

    /**
     * Method used for calculate the final score obtained by the player given is window board
     * "Row with no repeated values"
     *
     * @param board player window board
     * @return integer as the score calculate
     */
    @Override
    public int returnScore(PlayerBoard board) {
        int finalScore = 0;
        for (int i = 0; i < MAX_ROW_WINDOW_PATTERN; i++) {
            HashSet temp = new HashSet();
            for (int j = 0; j < MAX_COLUMN_WINDOW_PATTERN; j++) {
                if (board.getWindowboard().getCell(i, j).getDice() != null) {
                    temp.add(board.getWindowboard().getCell(i, j).getDice().getValue());
                }
            }
            if (temp.size() == MAX_COLUMN_WINDOW_PATTERN) {
                finalScore += this.getScore();
            }

        }

        return finalScore;

    }
}
