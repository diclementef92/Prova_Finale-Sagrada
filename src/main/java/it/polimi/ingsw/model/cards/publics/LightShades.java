package it.polimi.ingsw.model.cards.publics;

import it.polimi.ingsw.model.player.PlayerBoard;

import java.util.ArrayList;

import static it.polimi.ingsw.utils.Utils.MAX_COLUMN_WINDOW_PATTERN;
import static it.polimi.ingsw.utils.Utils.MAX_ROW_WINDOW_PATTERN;

/**
 * Light Shades card
 * Extension of Public Objective Card
 */
public class LightShades extends PublicObjectiveCard {

    /**
     * Main constructor of the Light Shades public objective card
     *
     * @param name        name of the card
     * @param description description of the card
     * @param score       score of the card
     */
    public LightShades(String name, String description, int score) {
        super(name,description, score);

    }

    /**
     * Method used for calculate the final score obtained by the player given is window board
     * "Sets of 1 & 2 values anywhere"
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
            if (k == 1) {
                firstcount++;
            }
            if (k == 2) {
                secondcount++;
            }
        }
        return Math.min(firstcount, secondcount) * getScore();
    }


}
