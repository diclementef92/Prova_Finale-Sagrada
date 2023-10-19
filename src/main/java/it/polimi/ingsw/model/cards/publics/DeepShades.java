package it.polimi.ingsw.model.cards.publics;

import it.polimi.ingsw.model.player.PlayerBoard;

import java.util.ArrayList;

import static it.polimi.ingsw.utils.Utils.MAX_COLUMN_WINDOW_PATTERN;
import static it.polimi.ingsw.utils.Utils.MAX_ROW_WINDOW_PATTERN;

/**
 * Deep Shades card
 * Extension of Public Objective Card
 */
public class DeepShades extends PublicObjectiveCard {

    /**
     * Main constructor of the Deep Shades public objective card
     *
     * @param name        name of the card
     * @param description description of the card
     * @param score       score retrieved from the card
     */
    public DeepShades(String name, String description, int score) {
        super(name,description, score);

    }

    /**
     * Method used for calculate the final score obtained by the player given is window board
     * "Set of 5 or 6 values anywhere"
     *
     * @param board player window board
     * @return integer as the score calculate
     */
    @Override
    public int returnScore(PlayerBoard board) {
        int firstcount = 0;
        int secondcount = 0;
        ArrayList<Integer> theList = new ArrayList<>();
        for (int i = 0; i < MAX_ROW_WINDOW_PATTERN; i++) {
            for (int j = 0; j < MAX_COLUMN_WINDOW_PATTERN; j++) {
                if (board.getWindowboard().getCell(i, j).getDice() != null) {
                    theList.add(board.getWindowboard().getCell(i, j).getDice().getValue());
                }
            }
        }
        for (int k : theList) {
            if (k == 5) {
                firstcount++;
            }
            if (k == 6) {
                secondcount++;
            }
        }
        return Math.min(firstcount, secondcount) * getScore();
    }
}
