package it.polimi.ingsw.model.cards.publics;

import it.polimi.ingsw.model.player.PlayerBoard;

import java.util.*;

import static it.polimi.ingsw.utils.Utils.MAX_COLUMN_WINDOW_PATTERN;
import static it.polimi.ingsw.utils.Utils.MAX_ROW_WINDOW_PATTERN;

/**
 * Color Variety card
 * Extension of Public Objective Card
 */
public class ColorVariety extends PublicObjectiveCard {

    /**
     * Main constructor of the Color Variety public objective card
     *
     * @param name        name of the card
     * @param description description of the card
     * @param score       score retrieved from the card
     */
    public ColorVariety(String name, String description, int score) {
        super(name,description, score);

    }

    /**
     * Method used to calculate the final score
     * "Sets of one of each color anywhere"
     *
     * @param board player window board
     * @return integer as the score calculate
     */
    @Override
    public int returnScore(PlayerBoard board) {
        Map<String, Integer> values = new HashMap<>();
        values.put("red", 0);
        values.put("yellow", 0);
        values.put("blue", 0);
        values.put("green", 0);
        values.put("purple", 0);
        ArrayList<String> theList = new ArrayList<>();
        for (int i = 0; i < MAX_ROW_WINDOW_PATTERN; i++) {
            for (int j = 0; j < MAX_COLUMN_WINDOW_PATTERN; j++) {
                if (board.getWindowboard().getCell(i, j).getDice() != null) {
                    theList.add(board.getWindowboard().getCell(i, j).getDice().getColor());
                }
            }
        }
        for (String k : theList) {
            values.put(k, values.get(k) + 1);
        }
        List<Integer> list = new ArrayList<>(Arrays.asList(values.get("red"), values.get("yellow"), values.get("blue"), values.get("green"), values.get("purple")));
        return Collections.min(list) * getScore();
    }


}