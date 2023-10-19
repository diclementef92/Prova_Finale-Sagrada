package it.polimi.ingsw.model.dice;

import it.polimi.ingsw.utils.Color;
import it.polimi.ingsw.utils.Utils;

import javax.rmi.CORBA.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.utils.Utils.DRAFTPOOL_EMPTY;
import static it.polimi.ingsw.utils.Utils.MAX_DICE_FOR_DRAFTPOOL_SINGLEPLAYER;

/**
 * DraftPool Class
 * The Draft Pool is the place where all the dice are placed to be chosen
 * for every turn
 */
public class DraftPool implements Serializable {

    /**
     * Array list containing all the Dices that can be chosen by the players during that turn
     */
    private List<Dice> listDraftPoolDice;
    /**
     * Bag of all dices
     */
    private DiceBag diceBag;
    /**
     * Number of players in the current game
     */
    private int numberOfPlayer;
    /**
     * Size of the DraftPool in the current game
     */
    private int sizeOfDraftpool;

    /**
     * Main constructor of the class Draft Pool
     * It takes as parameter the dice bag and the number of players in order to initialize correctly
     * the object
     * <p>
     * The number of players is used to roll the draftpool dice (2 * numberOfPlayer + 1) and for the rounds
     * of the players at each round (2 * numberOfPlayer)
     *
     * @param diceBag Bag of all dices
     * @param numberOfPlayer to calculate the number of dices
     */
    public DraftPool(DiceBag diceBag, int numberOfPlayer) {

        listDraftPoolDice = new ArrayList<>();
        this.diceBag = diceBag;
        this.numberOfPlayer = numberOfPlayer;

        if (numberOfPlayer ==1){
            this.sizeOfDraftpool=MAX_DICE_FOR_DRAFTPOOL_SINGLEPLAYER;
        }
        else {
            this.sizeOfDraftpool=2 * numberOfPlayer + 1;
        }
    }

    /**
     * Method used to roll the draft pool dice
     */
    public void draftDice() {

        /** shuffle ALWAYS before pulling dice, so in case of reinserting dice (ex: toolcards)
         *the shuffle avoids resuming the same dice
         */
        diceBag.shuffleDiceBag();

        for (int i = 0; i < sizeOfDraftpool; i++) {
            listDraftPoolDice.add(diceBag.removeDiceFromBag());
        }
        for (Dice d : listDraftPoolDice) {
            d.rollDice();
        }

    }

    /**
     * Method used to roll again all the dices in the draft pool
     */
    public void reRollAll() {
        for (Dice d : listDraftPoolDice) {
            d.rollDice();
        }
    }

    /**
     * Method use to remove a selected Dice from the Draft Pool and return it
     *
     * @return the selected Dice removed from the Draft Pool
     */
    public Dice removeSelectedDice(int index) {
        return listDraftPoolDice.remove(index);
    }

    /**
     * Method use to remove a single Dice from the Draft Pool and return it
     *
     * @return the Dice removed from the Draft Pool
     */
    public Dice removeFromDraftPool() {
        return listDraftPoolDice.remove(0);
    }

    /**
     * Method used to retrieve the number of Dice currently in the draftpool
     *
     * @return an integer describing the number of dices
     */
    public int getSizeDraftpool() {
        if (listDraftPoolDice.isEmpty()) {
            System.out.println(DRAFTPOOL_EMPTY);
        }
        return listDraftPoolDice.size();
    }


    /**
     * Return the list of the dice inside the draftpool
     *
     * @return an array list of dices
     */
    public List<Dice> getListDraftPoolDice() {
        return listDraftPoolDice;
    }



    /**
     * Defined method toString to return a description of the Draft Pool content
     *
     * @return a string that describe the Draft Pool content
     */
    public String toString() {
        if (listDraftPoolDice.isEmpty()){
            return Utils.DRAFTPOOL_EMPTY;
        }else{
            StringBuilder sb = new StringBuilder();
            for (int k = 0; k < 5; k++){
                for (Dice dice : listDraftPoolDice){
                    sb.append(Color.returnASCIIStringFormatted(dice.getColor(), Dice.diceFaces(dice.getValue())[k]));
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }

}