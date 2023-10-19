package it.polimi.ingsw.model.cards.publics;

import it.polimi.ingsw.model.player.PlayerBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static it.polimi.ingsw.utils.Utils.MAX_COLUMN_WINDOW_PATTERN;
import static it.polimi.ingsw.utils.Utils.MAX_ROW_WINDOW_PATTERN;

/**
 * Shade Variety card
 * Extension of Public Objective Card
 */
public class ShadeVariety extends PublicObjectiveCard {

    /**
     * Main constructor of the Shade Variety public objective card
     *
     * @param name        name of the card
     * @param description description of the card
     * @param score       score retrieved from the card
     */
    public ShadeVariety(String name, String description, int score) {
        super(name,description, score);
    }

    /**
     * Method used to calculate the final score
     * "Sets of one of each value anywhere"
     *
     * @param board player window board
     * @return integer as the score calculate
     */
    @Override
    public int returnScore(PlayerBoard board) {
        Integer[] values = {0, 0, 0, 0, 0, 0};
        ArrayList<Integer> theList = new ArrayList<>();
        for (int i = 0; i < MAX_ROW_WINDOW_PATTERN; i++) {
            for (int j = 0; j < MAX_COLUMN_WINDOW_PATTERN; j++) {
                if (board.getWindowboard().getCell(i, j).getDice() != null) {
                    theList.add(board.getWindowboard().getCell(i, j).getDice().getValue());
                }
            }
        }
        for (int k : theList) {
            values[k - 1]++;
        }
        List<Integer> list = Arrays.asList(values);
        return Collections.min(list) * getScore();
    }

}
