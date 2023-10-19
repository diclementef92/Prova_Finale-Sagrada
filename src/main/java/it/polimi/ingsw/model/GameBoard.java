package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.cards.Deck;
import it.polimi.ingsw.model.cards.publics.*;
import it.polimi.ingsw.model.dice.*;
import it.polimi.ingsw.model.windowpattern.WindowPatternDeck;
import it.polimi.ingsw.utils.Parser;
import it.polimi.ingsw.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.utils.Utils.MAX_CARD_ON_BOARD_SINGLEPLAYER;
import static it.polimi.ingsw.utils.Utils.MAX_CARD_ON_BOARD;

/**
 * Game Board Class
 * This board contain all the information relative to the current game
 */
public class GameBoard implements Serializable {

    /**
     * Bag containing all the dice
     */
    private DiceBag bag;

    /**
     * Draft pool containing dices
     */

    private DraftPool draftPool;

    /**
     * Object that tracks the progress of the game
     */
    private RoundTrack roundTrack;

    /**
     * Initial deck setup
     */

    private WindowPatternDeck windowpatterndeck;

    private Deck privates;

    private Deck publics;

    private Model model;

    private Deck toolcards;


    /**
     * Cards on the board in current game
     */

    private List<Card> publicsInGame = new ArrayList<>();

    private List<Card> toolcardsInGame = new ArrayList<>();

    private List<Card> privatesInGame = new ArrayList<>();

    /**
     * Numbers of player in the current game
     */

    private int numberOfPlayers;


    /**
     * Main constructor of the Gameboard class
     */
    public GameBoard(Model model, int numberOfPlayers) {

        this.numberOfPlayers = numberOfPlayers;
        this.model = model;
        /**
         * Creation of a dicebag
         */
        bag = new DiceBag();

        draftPool = new DraftPool(bag, numberOfPlayers);

        /**
         * Creation of the round track
         */
        roundTrack = new RoundTrack();

        /**
         * Creation of all the window pattern cards - deck
         */

        windowpatterndeck = new WindowPatternDeck();

        /**
         * Creation of all the private cards - deck
         */
        privates = new Deck();
        createPrivateCards();


        /**
         * Creation of all the public cards - deck
         */
        publics = new Deck();
        createPublicCards();

        /**
         * Creation of all toolcards - deck
         */

        toolcards = new Deck();
        createToolCards();
        toolcards.shuffleDeck();


        windowpatterndeck.shuffleWindowPatternDeck();
        privates.shuffleDeck();
        publics.shuffleDeck();

        /**
         * Setup of the public cards in the current game
         */

        if (numberOfPlayers == 1) {
            setupSinglePlayer();

        } else {
            setupMultiPlayer();
        }


    }

    /**
     * Setup of the public cards in the current game
     */
    private void setupSinglePlayer() {

        //Piazza 2 Obiettivi Pubblici e 2 Obiettivi Privati a faccia in su
        for (int i = 0; i < MAX_CARD_ON_BOARD_SINGLEPLAYER; i++) {
            publicsInGame.add(publics.pickCard());
            privatesInGame.add(privates.pickCard());
            //DA MODIFICARE: A SCELTA DEL PLAYER
            toolcardsInGame.add(toolcards.pickCard());

        }
    }

    /**
     * Method used for setup a multi player game
     */
    private void setupMultiPlayer() {

        //this if just for debug
        for (int i = 0; i < MAX_CARD_ON_BOARD; i++) {
            publicsInGame.add(publics.pickCard());
           toolcardsInGame.add(toolcards.pickCard());
        }
    }


    /**
     * Getter that retrieves the Dice Bag object
     *
     * @return a Dice Bag held in this Game Board
     */
    public DiceBag getDiceBag() {
        return bag;
    }

    /**
     * Getter of the round track in this Game board
     *
     * @return this Round Track
     */
    public RoundTrack getRoundTrack() {
        return roundTrack;
    }

    /**
     * Getter of the current draft pool
     *
     * @return the current draft pool
     */
    public DraftPool getDraftPool() {
        return draftPool;
    }

    /**
     * Getter of the tool cards in game
     * @return deck of tool cards
     */
    public Deck getToolcards() {
        return toolcards;
    }

    /**
     * Getter of the public cards in game
     * @return deck of public cards
     */
    public Deck getPublics() {
        return publics;
    }

    /**
     * Getter of the privates cards in game
     * @return deck of privates cards
     */
    public Deck getPrivates() {
        return privates;
    }

    /**
     * Getter of the window patterns in game
     * @return deck of window patterns
     */
    public WindowPatternDeck getWindowpatterndeck() {
        return windowpatterndeck;
    }

    /**
     * Getter of the tool cards in game
     * @return list of tool cards
     */
    public List<Card> getToolcardsInGame() {
        return toolcardsInGame;
    }

    /**
     * Creation of private cards inside the Gameboard
     */
    private void createPrivateCards() {
        Parser parser = new Parser();
        privates = parser.parserPrivateCard();
    }

    /**
     * Creation of public cards inside the Gameboard
     */
    private void createPublicCards() {

        ColorVariety colorVariety = new ColorVariety(Utils.COLOR_VARIETY,
                Utils.COLOR_VARIETY_DESCRIPTION, 4);
        ShadeVariety shadeVariety = new ShadeVariety(Utils.SHADE_VARIETY,
                Utils.SHADE_VARIETY_DESCRIPTION, 5);
        RowColorVariety rowColorVariety = new RowColorVariety(Utils.ROW_COLOR_VARIETY,
                Utils.ROW_COLOR_VARIETY_DESCRIPTION, 6);
        RowShadeVariety rowShadeVariety = new RowShadeVariety(Utils.ROW_SHADE_VARIETY,
                Utils.ROW_SHADE_VARIETY_DESCRIPTION, 5);
        ColumnColorVariety columnColorVariety = new ColumnColorVariety(Utils.COLUMN_COLOR_VARIETY,
                Utils.COLUMN_COLOR_VARIETY_DESCRIPTION, 5);
        ColumnShadeVariety columnShadeVariety = new ColumnShadeVariety(Utils.COLUMN_SHADE_VARIETY,
                Utils.COLUMN_SHADE_VARIETY_DESCRIPTION, 4);
        LightShades lightShades = new LightShades(Utils.LIGHT_SHADES,
                Utils.LIGHT_SHADES_DESCRIPTION, 2);
        MediumShades mediumShades = new MediumShades(Utils.MEDIUM_SHADES,
                Utils.MEDIUM_SHADES_DESCRIPTION, 2);
        DeepShades deepShades = new DeepShades(Utils.DEEP_SHADES,
                Utils.DEEP_SHADES_DESCRIPTION, 2);

        publics.getDeckCards().add(colorVariety);
        publics.getDeckCards().add(columnColorVariety);
        publics.getDeckCards().add(rowColorVariety);
        publics.getDeckCards().add(rowShadeVariety);
        publics.getDeckCards().add(shadeVariety);
        publics.getDeckCards().add(lightShades);
        publics.getDeckCards().add(mediumShades);
        publics.getDeckCards().add(deepShades);
        publics.getDeckCards().add(columnShadeVariety);

    }

    /**
     * Creation of tool cards inside the Gameboard
     */

    public void createToolCards() {
        Parser parser = new Parser();
        toolcards = parser.parserToolcard();

    }

    /**
     * Defined method toString that returns all the information of the Gameboard when called
     *
     * @return String
     */
    public String toString() {
        if (numberOfPlayers == 1) {
            return "ACTUAL GAMEBOARD: \n" +
                    "\n======================\n" +
                    "Draftpool: \n" + draftPool.toString() +
                    "\n======================\n" +
                    "Roundtrack: \n" + roundTrack.fullColoredString() + "\n" +
                    "\n======================\n" +
                    "Private Objective Cards on the board: \n" + privatesInGame.toString() + "\n" +
                    "\n======================\n" +
                    "Public Objective Cards on the board: \n" + publicsInGame.toString() + "\n" +
                    "\n======================\n" +
                    "ToolCards on the board:\n " + toolcardsInGame.toString() + "\n" +
                    "\n======================\n";


        } else {

            return "ACTUAL GAMEBOARD: \n" +
                    "\n======================\n" +
                    "Draftpool: \n" + draftPool.toString() +
                    "\n======================\n" +
                    "Roundtrack: \n" + roundTrack.fullColoredString() + "\n" +
                    "\n======================\n" +
                    "Public Objective Cards on the board: \n" + publicsInGame.toString() + "\n" +
                    "\n======================\n" +
                    "ToolCards on the board:\n " + toolcardsInGame.toString() + "\n" +
                    "\n======================\n";

        }
    }

    /**
     * Getter of public cards in game
     * @return List of public cards
     */
    public List<Card> getPublicsInGame() {
        return publicsInGame;
    }

    /**
     * Getter of private cards in game
     * @return List of private cards
     */
    public List<Card> getPrivatesInGame() {
        return privatesInGame;
    }
}