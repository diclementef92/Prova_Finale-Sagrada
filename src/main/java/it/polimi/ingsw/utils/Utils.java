package it.polimi.ingsw.utils;

import java.io.Serializable;

/**
 * Class with all the static finals of the game
 */
public final class Utils implements Serializable {


    ///////////////////////////////////

    public static final String SINGLE_PLAYER_MODE ="SINGLE PLAYER MODE!";
    public static final String RED = "red";
    public static final String FINAL_SCORE = "Final score: ";
    public static final String COLOR_VARIETY = "Color Variety";
    public static final String COLOR_VARIETY_DESCRIPTION = "Sets of one of each color anywhere";
    public static final String SHADE_VARIETY = "Shade Variety";
    public static final String SHADE_VARIETY_DESCRIPTION = "Sets of one of each value anywhere";
    public static final String ROW_COLOR_VARIETY = "Row Color Variety";
    public static final String ROW_COLOR_VARIETY_DESCRIPTION = "Rows with no repeated colors";
    public static final String ROW_SHADE_VARIETY = "Row Shade Variety";
    public static final String ROW_SHADE_VARIETY_DESCRIPTION = "Row with no repeated values";
    public static final String COLUMN_COLOR_VARIETY = "Column Color Variety";
    public static final String COLUMN_COLOR_VARIETY_DESCRIPTION = "Column with no repeated colors";
    public static final String COLUMN_SHADE_VARIETY = "Column Shade Variety";
    public static final String COLUMN_SHADE_VARIETY_DESCRIPTION = "Column with no repeated values";
    public static final String LIGHT_SHADES = "Light Shades";
    public static final String LIGHT_SHADES_DESCRIPTION = "Sets of 1 & 2 values anywhere";
    public static final String MEDIUM_SHADES = "Medium Shades";
    public static final String MEDIUM_SHADES_DESCRIPTION = "Sets of 3 & 4 values anywhere";
    public static final String DEEP_SHADES = "DeepShades";
    public static final String DEEP_SHADES_DESCRIPTION = "Sets of 5 & 6 values anywhere";
    public static final String CELL_IS_EMPTY = "empty cell";
    public static final String NO_DICE_IN_PLAYERBOARD = "no dice in the player board";
    public static final String CELL_WITHOUT_RESTRICTION = "cell without restrictions";
    public static final String CELL_WITH_COLOR_RESTRICTION = "cell with color restriction";
    public static final String CELL_WITHOUT_COLOR_RESTRICTION = "cell without color restriction";
    public static final String DICE_WITH_VALUE_RESTRICTION = "die with value restriction";
    public static final String DICE_WITHOUT_VALUE_RESTRICTION = "die without value restriction";
    public static final String DICE_CONTAINED_IN_BORDER = "die contained in border";
    public static final String DICE_NOT_CONTAINED_IN_BORDER = "die not contained in border";
    public static final String DICE_ADJACENT = "dice adjacent";
    public static final String NO_DICE_ADJACENT = "no dice adjacent";
    public static final String DICE_WITH_SAME_COLOR_OR_VALUE = "there is a die with same color or same value";
    public static final String DICE_WITH_NO_SAME_COLOR_OR_VALUE = "there are not dice with same color or same value";

    ///////////////////////////////////

    //FINALS FOR TOURNAMENT

    public static final String INSERT_HELP_TO_SHOW_COMMANDS = "\n[Insert - help - to show a list of commands]\n";
    public static final String INSERT_COMMAND_COLOREDSTRING = (Color.ANSI_BLUE.escape() + "Insert a command:" + Color.RESET);
    public static final String NEW_TURN_ORDER = ("*** NEW ORDER IN CURRENT TURN: ");
    public static final String YOUR_TURN = ("It's your turn! ");
    public static final String NOT_YOUR_TURN = "It's NOT you turn! ";


    //
    public static final String SELECT_DICE_FROM_PLAYERBOARD = "Select a Dice from your Board";
    public static final String SELECT_DICE_FROM_DRAFTPOOL = "Select a Dice from the draftpool";
    public static final String SELECT_TOOLCARD = "Choose the ToolCard you want to use";

    public static final String YOUR_PLAYERBOARD = "This is your Board";
    public static final String INSERT_COL = "Insert the Column:";
    public static final String INSERT_ROW = "Insert the Row:";

    //FINALS PER ERRORI VARI
    public static final String EMPTY_CELL = "empty";
    public static final String DELETE_ACTION = "Deleted Action";
    public static final String PLAYERBOARD_EMPTY = "Your Board is EMPTY!";
    public static final String DRAFTPOOL_EMPTY = "DraftPool is EMPTY!";
    public static final String ROUNDTRACK_EMPTY = "RoundTrack is EMPTY!";
    public static final String INVALID_CARD_SELECTED = "You selected an invalid card, please try again!";
    public static final String NOT_ENOUGH_TOKEN = "You don'have enough Favor Token to perform this action";
    public static final String NO_MORE_TOOLCARD_ACTIONS = "There are no more UseToolCard Action left!";
    public static final String NO_MORE_DICE_PLACEMENT = "There are no more PlaceDice Action left!";
    public static final String PLACE_DICE_ACTION_COMPLETED = "PlaceDice action completed!";
    public static final String FAILED_PLACE_DICE_ACTION = "Failed PlaceDice Action!";
    public static final String TOOLCARD_ACTION_COMPLETED = "UseToolCard Action completed!";
    public static final String FAILED_TOOLCARD_ACTION = "Failed UseToolCard Action";

    //FINALS FOR ACTION

    public static final String ACTION_PLACE_DICE = "placeDice";

    public static final String ACTIONTYPE_PLACE_DICE_WITH_ALL_RESTRICTIONS = "placeWithAllRestrictions";
    public static final String ACTIONTYPE_PLACE_DICE_IGNORE_ADJACENT = "placeIgnoreAdjacent";


    public static final String ACTION_CHANGE_DICE_VALUE = "changeDiceValue";

    public static final String ACTIONTYPE_SELECT_DICE_VALUE = "selectDiceValue";
    public static final String ACTIONTYPE_INCREASE_DICE_VALUE = "increaseDiceValue";
    public static final String ACTIONTYPE_FLIP_DICE = "flipDice";
    public static final String ACTIONTYPE_REROLL_DICE = "reRollDice";
    public static final String ACTIONTYPE_REROLL_ALL = "reRollAllDice";

    public static final String ACTION_MOVE_DICE = "actionMoveDice";

    public static final String ACTIONTYPE_MOVE_IGNORE_COLOR = "moveIgnoreColor";
    public static final String ACTIONTYPE_MOVE_IGNORE_VALUE = "moveIgnoreValue";
    public static final String ACTIONTYPE_MOVE_MATCHING_ROUNDTRACKDICE = "moveMatchingColorRoundTrackDice";
    public static final String ACTIONTYPE_MOVE_WITH_ALL_RESTRICTIONS = "moveWithAllRestrictions";

    public static final String ACTION_SWAP_DICE = "swapDiceAction";

    public static final String ACTIONTYPE_SWAP_DICE_FROM_DICEBAG = "swapDiceWithDiceBag";
    public static final String ACTIONTYPE_SWAP_DICE_FROM_ROUNDTRACK = "swapDiceWithRoundTrack";


    public static final String ACTION_SKIP_TURN = "skipTurn";
    public static final String ACTIONTYPE_SKIP_NEXT_TURN = "skipNextTurn";


    public static final String INVALID_INPUT_NOTANUMBER = "Invalid input! Insert a number";
    public static final String INVALID_INPUT_NUMBEROUT_OF_RANGE = "Value out of range: insert a new Value";

    public static final String ABORTED_REQUEST = "Aborted";

    //FINALS PER LE TOOLCARD
    public static final int TOKEN_NEEDED_IF_EMPTY = 1;
    public static final int TOKEN_NEEDED_IF_NOT_EMPTY = 2;


    //FINALS PER I DADI
    public static final int MAX_DICE_FOR_TURN = 1;
    public static final int MAX_DICE_FOR_COLOR = 18;
    public static final int MAX_DICE_FOR_DICEBAG = 90;
    public static final int NUMBER_DICE_SHADES = 6;
    public static final int MIN_SHADE_DICE = 1;

    //FINALS PER LE WINDOW PATTERN

    public static final int MAX_WINDOW_PATTERN_PLAYER = 4;
    public static final int MAX_ROW_WINDOW_PATTERN = 4;
    public static final int MAX_COLUMN_WINDOW_PATTERN = 5;
    public static final int MAX_NUMBER_WINDOWPATTERN = 24;

    //FINAL PER GAMEBOARD

    public static final int MAX_USETOOLCARD_FOR_TURN = 1;
    public static final int MAX_ROUND = 10;
    public static final int MAX_CARD_ON_BOARD = 3;
    public static final int MAX_PLAYERS_MULTIPLAYER = 4;

    //FINAL PER SINGLE PLAYER

    public static final int MAX_CARD_ON_BOARD_SINGLEPLAYER = 2;
    public static final int MAX_DICE_FOR_DRAFTPOOL_SINGLEPLAYER = 4;
    public static final String DICE_NOT_MATCHING = "The selected Dice does not respect the color of the ToolCard";
    public static final String NO_MORE_TOOLCARDINGAME = "There are no more ToolCard in game! You can't use this command";


    public static final String RESOURCES_JSON_PATH  = "resources/json/";

    //FINAL PER DIMENSIONE FRAME CARDS IN CLI
    public static final int CARD_FRAME_DIMENSION = 24;
    public static final String YOUR_ACTUAL_DRAFTPOOL = "This is your actual draftpool";

    public static String getTime() {

        java.util.Calendar c = java.util.Calendar.getInstance();
        int hour = c.get(java.util.Calendar.HOUR_OF_DAY);
        int minutes = c.get(java.util.Calendar.MINUTE);
        return hour + ":" + minutes;
    }

}
