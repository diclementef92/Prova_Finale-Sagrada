package it.polimi.ingsw.model.dice;

import it.polimi.ingsw.utils.Parser;
import it.polimi.ingsw.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Class of the Dice Bag
 */
public class DiceBag implements Serializable {

    /**
     * List of dice in the bag
     */
    private ArrayList<Dice> listOfDiceInBag;

    /**
     * Main constructor of the Dice Bag
     */
    public DiceBag() {
        listOfDiceInBag = new ArrayList<>();
        fillBag();
    }

    /**
     * Method called by the constructor used to initialize the content
     * of the bag.
     */
    private void fillBag() {
        Parser parser = new Parser();
        Iterator iterator = parser.parserDicebag();

        while (iterator.hasNext()) {
            String color = (String) iterator.next();
            for (int i = 0; i < Utils.MAX_DICE_FOR_COLOR; i++) {
                Dice d = new Dice(color);
                this.listOfDiceInBag.add(d);
            }
        }
    }

    public Dice getFirstDice(){
        return listOfDiceInBag.get(0);
    }

    /**
     * Method used to remove a single Dice from the bag and return it
     *
     * @return the Dice from the bag
     */
    public Dice removeDiceFromBag() {
        return listOfDiceInBag.remove(0);
    }

    /**
     * Method used to retrieve the number of Dice currently in the bag
     *
     * @return an integer describing the number of dices
     */
    public int diceBagSize() {
        if (listOfDiceInBag.isEmpty()) {
            System.out.println(Utils.DRAFTPOOL_EMPTY);
        }
        return listOfDiceInBag.size();
    }

    /**
     * Method used to add a single Dice in the bag
     *
     * @param dice the user want to add
     */
    public void returnDiceToDiceBag(Dice dice) {
        listOfDiceInBag.add(dice);
    }

    /**
     * Method used to shuffle the Dice bag and change their position in the array
     * It has to be called at the beginning of every round
     */
    public void shuffleDiceBag() {
        Collections.shuffle(listOfDiceInBag);
    }

    /**
     * Defined method toString that returns a string
     *
     * @return a string containing all the information of the Dice Bag
     */
    public String toString() {
        StringBuilder bld = new StringBuilder();
        for (Dice d : listOfDiceInBag)
            bld.append(d);

        return bld.toString();
    }

}
