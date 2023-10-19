package it.polimi.ingsw.model.dice;

import it.polimi.ingsw.utils.Color;
import it.polimi.ingsw.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Round track class
 * The round track contains the information about the current game progress
 * There only one round track per game
 */
public class RoundTrack implements Serializable {

    /**
     * Dice in the round track
     * It is mapped as a Map of integer and list of dices as for every turn there
     * can be more than one dice
     */
    private Map<Integer, List<Dice>> diceRoundtrack;
    /**
     * Number of round of the current game
     */
    private int numberOfRound;

    /**
     * Main constructor of the Round Track class
     *
     */
    public RoundTrack() {
        this.numberOfRound = 1;
        this.diceRoundtrack = new HashMap<>();
    }

    /**
     * Getter for the number of round in game
     * It is progressive, so if called during the third round, it will return 3
     *
     * @return the number of the current round
     */
    public int getNumberofRound() {
        return numberOfRound;
    }

    /**
     * Increment the value of round at the end of all player turns
     */
    public void incrementRound() {
        numberOfRound++;
    }

    /**
     * Method called at the end of the players' turns, in order to add the remaining dices from the draft pool
     * to this Round Track
     *
     * @param pool request a Draft Pool to be passed in order to get the remaining dices
     */
    public void insertDiceInRoundtrack(DraftPool pool) {
        List<Dice> temp = new ArrayList<>();
        int poolDimension = pool.getSizeDraftpool();
        for (int i = 0; i < poolDimension; i++) {
            temp.add(pool.removeFromDraftPool());
        }
        diceRoundtrack.put(numberOfRound, temp);
    }

    /**
     * Method used to request the Dice contained inside the Round Track for a single round
     * It returns a list of Dices for the requested round
     *
     * @param round an integer for the round required
     * @return a list of Dices present in the round track at the requested round
     */

    public List<Dice> getDicePerRound(int round) {
        return diceRoundtrack.get(round);
    }


    /**
     * Method used to print all the information of the round track up the last round in the game
     *
     * @return a string containing all the information
     */
    public String getDiceRoundtrack() {

        if (getNumberofRound() == 1) {
            return Utils.ROUNDTRACK_EMPTY;
        } else {
            StringBuilder bld = new StringBuilder();
            //nota: conta da 1 così è piu semplice per altri passaggi
            for (int i = 1; i < numberOfRound; i++) {

                /**
                 * The i + 1 is used to display the correct value
                 * DON'T CHANGE THE i + 1
                 */
                bld.append("Round: ").append(i).append(" - Dice: ").append(diceRoundtrack.get(i).toString());
            }

            return bld.toString();
        }
    }

    /**
     * Defined method toString that return a string with all the information of the Round Track up to that moment
     *
     * @return a string that describe the content of the Round Track up to that moment
     */
    public String toString() {

        if (numberOfRound == 1) {
            return Utils.ROUNDTRACK_EMPTY;
        } else
            return getDiceRoundtrack() + " " + getNumberofRound();
    }


    /**
     * Method toString with colors that return a string with all the information of the Roundtrack
     * It is divided in: number of round and an associated list of dice
     *
     * @return a string that describe the content of the Round Track up to that moment
     */
    public String fullColoredString() {

        if (getNumberofRound() == 1) {
            return Utils.ROUNDTRACK_EMPTY;
        } else {
            StringBuilder bld = new StringBuilder();

            //note: the number of round starts from 1
            for (int i = 1; i < numberOfRound; i++) {
                bld.append("ROUND ").append(i).append("\n");
                for (int k = 0; k < 5; k++) {
                    for (int j = 0; j < getDicePerRound(i).size(); j++) {
                        bld.append(Color.returnASCIIStringFormatted(getDicePerRound(i).get(j).getColor(), Dice.diceFaces(getDicePerRound(i).get(j).getValue())[k]));
                    }
                    bld.append("\n");
                }
            }
            return bld.toString();
        }
    }
}
