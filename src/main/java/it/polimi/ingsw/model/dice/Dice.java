package it.polimi.ingsw.model.dice;

import it.polimi.ingsw.utils.Color;

import java.io.Serializable;
import java.util.Random;

import static it.polimi.ingsw.utils.Utils.MIN_SHADE_DICE;
import static it.polimi.ingsw.utils.Utils.NUMBER_DICE_SHADES;

/**
 * Class of the Dice
 */
public class Dice implements Serializable {

    /**
     * Color of the dice
     */
    private String color;


    /**
     * Value held by the dice
     */
    private int value;

    /**
     * Main constructor of the Dice
     * It takes only one parameter (the color) which cannot be changed during the game
     * On the other hand, the value of the dice can be changed
     *
     * @param color of the dice
     */
    public Dice(String color) {
        this.color = color;
    }

    /**
     * Getter of the color of the dice
     *
     * @return a string containing the color of the dice
     */
    public String getColor() {
        return color;
    }

    /**
     * Getter of the value of the Dice
     *
     * @return an integer containing the value of this Dice
     */
    public int getValue() {
        return value;
    }

    /**
     * Setter of the Dice value
     * If not used the control, delete this method and keep using rollDice
     *
     * @param value integer required to change the value of the dice
     */
    public boolean setValue(int value) {
        if (value < MIN_SHADE_DICE || value > NUMBER_DICE_SHADES) {
            return false;
        } else {
            this.value = value;
            return true;
        }
    }

    /**
     * Method to roll a Dice and change it values
     */
    public void rollDice() {
        Random draft = new Random();
        this.value = 1 + draft.nextInt(NUMBER_DICE_SHADES);
    }

    /**
     * Method used to increase the value of this Dice
     *
     * @return a bool to know if the operation was successful or not
     */
    public boolean increaseValue() {
        if (value != NUMBER_DICE_SHADES) {
            value++;
            return true;
        } else
            return false;

    }

    /**
     * Method used to decrease the value of this Dice
     *
     * @return a bool to know if the operation was successful or not
     */
    public boolean decreaseValue() {
        if (value != MIN_SHADE_DICE) {
            value--;
            return true;
        } else
            return false;
    }


    /**
     * Defined method toString to retrieve the information of this Dice
     *
     * @return a string that describe the content of this dice
     */
    public String toString() {
        return "\n=======\n" +
                "Dice value: " + getValue() + "\n" +
                "Dice color: " + getColor() + "\n" +
                "=======\n";
    }

    /**
     * Method toString with colors to retrieve the information of this Dice
     *
     * @return a string with colored dice
     */
    public String toColorString(){
        StringBuilder sr = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sr.append(Color.returnASCIIStringFormatted(getColor(), diceFaces(getValue())[i]));
            sr.append("\n");
        }
        return sr.toString();
    }

    /**
     * Method used to draw the dice faces
     *
     * @param value of the dice
     * @return a string of the dice drawn
     */
    public static String[] diceFaces(int value) {
        switch (value) {
            case 1:
                return new String[]{"┏━━━━━━━┓", "┃       ┃", "┃   ●   ┃", "┃       ┃", "┗━━━━━━━┛"};
            case 2:
                return new String[]{"┏━━━━━━━┓","┃ ●     ┃", "┃       ┃", "┃     ● ┃", "┗━━━━━━━┛"};
            case 3:
                return new String[]{"┏━━━━━━━┓","┃ ●     ┃","┃   ●   ┃","┃     ● ┃","┗━━━━━━━┛"};
            case 4:
                return new String[]{"┏━━━━━━━┓","┃ ●   ● ┃","┃       ┃","┃ ●   ● ┃","┗━━━━━━━┛"};
            case 5:
                return new String[]{"┏━━━━━━━┓","┃ ●   ● ┃", "┃   ●   ┃","┃ ●   ● ┃","┗━━━━━━━┛"};
            case 6:
                return new String[] {"┏━━━━━━━┓","┃ ●   ● ┃","┃ ●   ● ┃","┃ ●   ● ┃","┗━━━━━━━┛"};
        }
        return new String[]{"", ""};
    }

}


