package it.polimi.ingsw.controller.interpreter;

import it.polimi.ingsw.controller.message.requests.*;
import it.polimi.ingsw.controller.message.responses.ActionResponse;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.dice.DraftPool;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.utils.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

/**
 * ActionInterpreter for the toolcards actions
 */
public class ActionInterpreter implements Serializable {

    private Player player;
    private Model model;
    private Scanner in;
    private PrintWriter out;
    ActionRequest returnedRequest = null;
    private int startRow = -1;
    private int startCol = -1;
    private int finalRow = -1;
    private int finalCol = -1;
    private int valueOfChange = 0;

    private static final String ASK_IF_PLACE_DICE = "Do you want to place the dice?";
    private static final String REALLY_RERELLO_ALL = "Do you really want to reroll all the dice?";
    private static final String YES_NO_ANSWER = "Insert: \n0 - YES\n1 - NO\nIf you select NO, you will lose your Favor Token\n";
    private static final String TOOLCARD_ONLY_SECOND_TURN_BEFORE_DRAFTING = "This card may be used on your second before drafting";
    private static final int YES = 0;
    public static final int NO = 1;

    public ActionInterpreter(OutputStream outputStream, Model model, Player player, InputStream in) {
        this.out = new PrintWriter(outputStream, true);
        this.model = model;
        this.player = player;
        this.in = new Scanner(in);
    }

    /**
     * Method used to parse toolcards actions
     *
     * @param actionResponse
     * @return
     */
    public ActionRequest parseAction(ActionResponse actionResponse) {
        String action = actionResponse.getMessageContent();
        String actionType = actionResponse.getActionType();
        Dice actionResponseDice = actionResponse.getDice();
        returnedRequest = null;

        ////// PLACE DICE ACTION ////////

        if (Utils.ACTION_PLACE_DICE.equals(action)) {

            Dice selectedDice = actionResponseDice;

            if (model.getTournament().getCurrentTurn().getSelectDiceActionCounter() == 0) {
                out.println(Utils.NO_MORE_DICE_PLACEMENT);
                return returnedRequest;
            }

            if (selectedDice == null) {

                if (model.getGameBoard().getDraftPool().getListDraftPoolDice().isEmpty()) {
                    out.println(Utils.DRAFTPOOL_EMPTY);
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;
                }
                out.println(Utils.SELECT_DICE_FROM_DRAFTPOOL);
                printSelectDiceDraftpool(model);
                /*for (Dice dice : model.getGameBoard().getDraftPool().getListDraftPoolDice()) {
                    out.println(model.getGameBoard().getDraftPool().getListDraftPoolDice().indexOf(dice) + ":\n" + dice.toColorString());
                }*/

                int numberDice = -1;
                numberDice = selectNumberForRequest(numberDice, 0, model.getGameBoard().getDraftPool().getListDraftPoolDice().size());
                selectedDice = model.getGameBoard().getDraftPool().getListDraftPoolDice().get(numberDice);
            }

            if (actionType.equals(Utils.ACTIONTYPE_PLACE_DICE_IGNORE_ADJACENT)) {
                if (player.getPlayerBoard().checkisFirstDice()) {
                    out.println(Utils.PLAYERBOARD_EMPTY);
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;
                }
            }

            if (actionType.equals(Utils.ACTIONTYPE_PLACE_DICE_WITH_ALL_RESTRICTIONS) || actionType.equals(Utils.ACTIONTYPE_PLACE_DICE_IGNORE_ADJACENT)) {

                out.println("You can place the selected Dice:\n" + selectedDice.toColorString());
                out.println(Utils.YOUR_PLAYERBOARD);
                out.println(player.getPlayerBoard().fullColoredString());

                out.println(ASK_IF_PLACE_DICE);
                out.println(YES_NO_ANSWER);
                int answer = -1;
                answer = selectChoiceForRequest(answer, YES, NO);

                if (answer == NO) {
                    return new AbortRequest(Utils.ABORTED_REQUEST, player);
                }

                out.println(Utils.INSERT_ROW);
                startRow = selectNumberForRequest(startRow, 0, Utils.MAX_ROW_WINDOW_PATTERN);

                out.println(Utils.INSERT_COL);
                startCol = selectNumberForRequest(startCol, 0, Utils.MAX_COLUMN_WINDOW_PATTERN);

                if (player.getPlayerBoard().getWindowboard().getCell(startRow, startCol).getDice() != null) {
                    out.println("There is a dice in this cell");
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;
                }

                return new PlaceDiceRequest(actionType, selectedDice,
                        startRow, startCol, player);
            }
        }

        ////// CHANGE DICE VALUE ACTION ////////

        if (action.equals(Utils.ACTION_CHANGE_DICE_VALUE)) {

            if (model.getTournament().getCurrentTurn().getSelectDiceActionCounter() == 0) {
                out.println(Utils.NO_MORE_DICE_PLACEMENT);
                return returnedRequest;
            }

            Dice selectedDice = actionResponseDice;
            int answer = -1;

            if (Utils.ACTIONTYPE_SELECT_DICE_VALUE.equals(actionType)) {
                selectedDice = model.getTournament().getCurrentTurn().getDiceToBePlaced();

                out.println("The new Dice is:\n" + model.getGameBoard().getDiceBag().getFirstDice().toColorString());
                out.println("Select the new value of the dice");
                valueOfChange = selectNumberForRequest(valueOfChange, 0, Utils.NUMBER_DICE_SHADES+1);

                return new ChangeDiceValueRequest(Utils.ACTIONTYPE_SELECT_DICE_VALUE, selectedDice, valueOfChange, player);


            } else

            if (Utils.ACTIONTYPE_REROLL_ALL.equals(actionType)) {


                if (!model.getTournament().isCounterClockwise()) {
                    out.println(TOOLCARD_ONLY_SECOND_TURN_BEFORE_DRAFTING);
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;
                }

                if (model.getTournament().getCurrentTurn().getSelectDiceActionCounter() == 0) {
                    out.println(TOOLCARD_ONLY_SECOND_TURN_BEFORE_DRAFTING);
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;
                }

                if (model.getGameBoard().getDraftPool().getListDraftPoolDice().isEmpty()) {
                    out.println(Utils.DRAFTPOOL_EMPTY);
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;
                }


                out.println(Utils.YOUR_ACTUAL_DRAFTPOOL);
                out.println(model.getGameBoard().getDraftPool().toString());
                /*for (Dice dice : model.getGameBoard().getDraftPool().getListDraftPoolDice()) {
                    out.println(model.getGameBoard().getDraftPool().getListDraftPoolDice().indexOf(dice) + ":\n" + dice.toColorString());
                }*/

                out.println(REALLY_RERELLO_ALL);
                out.println(YES_NO_ANSWER);
                answer = selectChoiceForRequest(answer, YES, NO);

                if (answer == NO) {
                    return new AbortRequest(Utils.ABORTED_REQUEST, player);
                }


                return new ChangeDiceValueRequest(Utils.ACTIONTYPE_REROLL_ALL, null, 0, player);

            } else if (Utils.ACTIONTYPE_INCREASE_DICE_VALUE.equals(actionType)) {
                if (selectedDice == null) {

                    if (model.getGameBoard().getDraftPool().getListDraftPoolDice().isEmpty()) {
                        out.println(Utils.DRAFTPOOL_EMPTY);
                        out.println(Utils.DELETE_ACTION);
                        return returnedRequest;
                    }

                    out.println(Utils.SELECT_DICE_FROM_DRAFTPOOL);
                    printSelectDiceDraftpool(model);
                    /*for (Dice dice : model.getGameBoard().getDraftPool().getListDraftPoolDice()) {
                        out.println(model.getGameBoard().getDraftPool().getListDraftPoolDice().indexOf(dice) + ":\n" + dice.toColorString());
                    }*/

                    int numberDice = -1;
                    numberDice = selectNumberForRequest(numberDice, 0, model.getGameBoard().getDraftPool().getListDraftPoolDice().size());
                    selectedDice = model.getGameBoard().getDraftPool().getListDraftPoolDice().get(numberDice);
                }

                out.println("You selected the Dice:\n" + selectedDice.toColorString());
                out.println("Insert +1 if you want to increase the value, or -1 if you want to decrease it");

                valueOfChange = selectChoiceForRequest(valueOfChange, 1, -1);

                if (selectedDice.getValue() == Utils.NUMBER_DICE_SHADES && valueOfChange == 1) {
                    out.println("You may not change 6 to 1!\n");
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;

                } else if (selectedDice.getValue() == Utils.MIN_SHADE_DICE && valueOfChange == -1) {
                    out.println("You may not change 1 to 6!\n");
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;
                }

                return new ChangeDiceValueRequest(Utils.ACTIONTYPE_INCREASE_DICE_VALUE,
                        selectedDice, valueOfChange, player);

            } else if (Utils.ACTIONTYPE_FLIP_DICE.equals(actionType) || Utils.ACTIONTYPE_REROLL_DICE.equals(actionType)) {
                if (selectedDice == null) {

                    if (model.getGameBoard().getDraftPool().getListDraftPoolDice().isEmpty()) {
                        out.println(Utils.DRAFTPOOL_EMPTY);
                        out.println(Utils.DELETE_ACTION);
                        return returnedRequest;
                    }

                    out.println(Utils.SELECT_DICE_FROM_DRAFTPOOL);
                    printSelectDiceDraftpool(model);
                    /*for (Dice dice : model.getGameBoard().getDraftPool().getListDraftPoolDice()) {
                        out.println(model.getGameBoard().getDraftPool().getListDraftPoolDice().indexOf(dice) + ":\n" + dice.toColorString());
                    }*/

                    int numberDice = -1;
                    numberDice = selectNumberForRequest(numberDice, 0, model.getGameBoard().getDraftPool().getListDraftPoolDice().size());
                    selectedDice = model.getGameBoard().getDraftPool().getListDraftPoolDice().get(numberDice);
                }

                out.println("You selected the Dice:\n" + selectedDice.toColorString());
                return new ChangeDiceValueRequest(actionType,
                        selectedDice, valueOfChange, player);
            }

        }

        ////// MOVE DICE ACTION ////////

        if ((Utils.ACTION_MOVE_DICE).equals(action)) {

            if (actionType.equals(Utils.ACTIONTYPE_MOVE_MATCHING_ROUNDTRACKDICE) || actionType.equals(Utils.ACTIONTYPE_MOVE_IGNORE_COLOR) ||
                    actionType.equals(Utils.ACTIONTYPE_MOVE_IGNORE_VALUE) || actionType.equals(Utils.ACTIONTYPE_MOVE_WITH_ALL_RESTRICTIONS)) {


                if (player.getPlayerBoard().checkisFirstDice()) {
                    out.println(Utils.PLAYERBOARD_EMPTY);
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;
                }

                if (actionType.equals(Utils.ACTIONTYPE_MOVE_MATCHING_ROUNDTRACKDICE)) {
                    if (model.getGameBoard().getRoundTrack().getNumberofRound() == 1) {
                        out.println(Utils.ROUNDTRACK_EMPTY);
                        out.println(Utils.DELETE_ACTION);
                        return returnedRequest;
                    }
                }




                if (!model.getTournament().getCurrentTurn().getCurrentToolCard().getChoseColorFromRoundrack().equals("default")) {
                    out.println("The color selected before was " + Color.returnASCIIStringFormatted(
                            model.getTournament().getCurrentTurn().getCurrentToolCard().getChoseColorFromRoundrack(),
                            model.getTournament().getCurrentTurn().getCurrentToolCard().getChoseColorFromRoundrack()));
                }

                out.println(Utils.SELECT_DICE_FROM_PLAYERBOARD);

                out.println(Utils.INSERT_ROW);
                startRow = selectNumberForRequest(startRow, 0, Utils.MAX_ROW_WINDOW_PATTERN);
                out.println(startRow);

                out.println(Utils.INSERT_COL);
                startCol = selectNumberForRequest(startCol, 0, Utils.MAX_COLUMN_WINDOW_PATTERN);
                out.println(startCol);

                if (player.getPlayerBoard().getWindowboard().getCell(startRow, startCol).getDice() == null) {
                    out.println("There is no dice on the selected cell!");
                    out.println(Utils.DELETE_ACTION);
                    returnedRequest = new AbortRequest("Abort", player);
                    return returnedRequest;
                } else {
                    out.println("You selected:\n");
                    out.println(player.getPlayerBoard().getWindowboard().getCell(startRow, startCol).getDice().toColorString());
                    if (model.getTournament().getCurrentTurn().getCurrentToolCard().getChoseColorFromRoundrack().equals("default")) {
                        out.println("The color you selected is " + Color.returnASCIIStringFormatted(
                                player.getPlayerBoard().getWindowboard().getCell(startRow, startCol).getDice().getColor(),
                                player.getPlayerBoard().getWindowboard().getCell(startRow, startCol).getDice().getColor()
                        ));
                    }
                }

                out.println("Now select the new position:");

                out.println(Utils.INSERT_ROW);
                finalRow = selectNumberForRequest(finalRow, 0, Utils.MAX_ROW_WINDOW_PATTERN);
                out.println(finalRow);

                out.println(Utils.INSERT_COL);
                finalCol = selectNumberForRequest(finalCol, 0, Utils.MAX_COLUMN_WINDOW_PATTERN);
                out.println(finalCol);


                out.println(ASK_IF_PLACE_DICE);
                out.println(YES_NO_ANSWER);


                int answer = -1;
                answer = selectChoiceForRequest(answer, YES, NO);

                /////////////////////////////////////
                if (actionType.equals(Utils.ACTIONTYPE_MOVE_MATCHING_ROUNDTRACKDICE)) {

                    int another = -1;
                    if ((model.getTournament().getCurrentTurn().getCurrentToolCard().getAnotherMoveDice() != 0)) {
                        out.println("Do you want to put another dice after this one?");
                        out.println(YES_NO_ANSWER);
                        another = selectChoiceForRequest(another, YES, NO);
                    }

                    if (answer == NO) {
                        return new AbortRequest(Utils.ABORTED_REQUEST, player);
                    }

                    return new MoveDiceRequest(actionType, startRow, startCol, finalRow, finalCol, another, player);

                }

                ////////////////////////

                // The parameter another was added to know if the player wants to add another dice
                return new MoveDiceRequest(actionType, startRow, startCol, finalRow, finalCol, 0, player);
            }
        }


        ////// SWAP DICE ACTION ////////

        if (Utils.ACTION_SWAP_DICE.equals(action)) {

            int numberDice = -1;
            int answer = -1;

            if (model.getTournament().getCurrentTurn().getSelectDiceActionCounter() == 0) {
                out.println(Utils.NO_MORE_DICE_PLACEMENT);
                return returnedRequest;
            }

            if (Utils.ACTIONTYPE_SWAP_DICE_FROM_ROUNDTRACK.equals(actionType)) {
                if (model.getGameBoard().getRoundTrack().getNumberofRound() == 1) {
                    out.println(Utils.ROUNDTRACK_EMPTY);
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;
                }

                if (model.getGameBoard().getDraftPool().getListDraftPoolDice().isEmpty()) {
                    out.println(Utils.DRAFTPOOL_EMPTY);
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;
                }

                out.println(Utils.SELECT_DICE_FROM_DRAFTPOOL);
                printSelectDiceDraftpool(model);
                /*for (Dice dice : model.getGameBoard().getDraftPool().getListDraftPoolDice()) {
                    out.println(model.getGameBoard().getDraftPool().getListDraftPoolDice().indexOf(dice)
                            + ":\n" + dice.toColorString());
                }*/

                numberDice = selectNumberForRequest(numberDice, 0, model.getGameBoard().getDraftPool().getListDraftPoolDice().size());
                out.println("You selected:\n" + model.getGameBoard().getDraftPool().getListDraftPoolDice().get(numberDice).toColorString());


                out.println("Select the dice from the RoundTrack");
                out.println(model.getGameBoard().getRoundTrack().fullColoredString());

                out.println("Select the round");

                int round = 0;
                round = selectNumberForRequest(round, 1, model.getGameBoard().getRoundTrack().getNumberofRound());

                if (model.getGameBoard().getRoundTrack().getDicePerRound(round).isEmpty()) {
                    out.println("No dice in round number:  " + round);
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;
                }

                out.println("Select the dice from the selected round");
                for (Dice dice : model.getGameBoard().getRoundTrack().getDicePerRound(round)) {
                    out.println(model.getGameBoard().getRoundTrack().getDicePerRound(round).indexOf(dice)
                            + ":\n" + dice.toColorString());
                }

                int diceToSwap = -1;
                diceToSwap = selectNumberForRequest(diceToSwap, 0, model.getGameBoard().getRoundTrack().getDicePerRound(round).size());
                out.println("You selected the dice from the roundtrack:\n" + model.getGameBoard().getRoundTrack().getDicePerRound(round).get(diceToSwap).toColorString());

                out.println("Do you want to swap the dice?");
                out.println(YES_NO_ANSWER);

                answer = selectChoiceForRequest(answer, YES, NO);
                if (answer == NO) {
                    return new AbortRequest(Utils.ABORTED_REQUEST, player);
                }

                return new SwapDiceRequest(Utils.ACTIONTYPE_SWAP_DICE_FROM_ROUNDTRACK, numberDice, round,
                        diceToSwap, player);

            }


            else if (Utils.ACTIONTYPE_SWAP_DICE_FROM_DICEBAG.equals(actionType)) {


                if (model.getGameBoard().getDraftPool().getListDraftPoolDice().isEmpty()) {
                    out.println(Utils.DRAFTPOOL_EMPTY);
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;
                }

                if (model.getGameBoard().getDiceBag().diceBagSize()==0){
                    out.println(Utils.DRAFTPOOL_EMPTY);
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;
                }

                out.println(Utils.SELECT_DICE_FROM_DRAFTPOOL);
                for (Dice dice : model.getGameBoard().getDraftPool().getListDraftPoolDice()) {
                    out.println(model.getGameBoard().getDraftPool().getListDraftPoolDice().indexOf(dice)
                            + ":\n" + dice.toColorString());
                }

                numberDice = selectNumberForRequest(numberDice, 0, model.getGameBoard().getDraftPool().getListDraftPoolDice().size());
                out.println(model.getGameBoard().getDraftPool().getListDraftPoolDice().get(numberDice).toColorString());

                out.println("Do you want to swap the SELECTED dice?");
                out.println(YES_NO_ANSWER);

                answer = selectChoiceForRequest(answer, YES, NO);
                if (answer == NO) {
                    return new AbortRequest(Utils.ABORTED_REQUEST, player);
                }

                return new SwapDiceRequest(Utils.ACTIONTYPE_SWAP_DICE_FROM_DICEBAG, numberDice, 0, 0, player);
            }
        }

        ////// SKIP TURN ACTION  ////////

        if (Utils.ACTION_SKIP_TURN.equals(action)) {

            if (actionType.equals(Utils.ACTIONTYPE_SKIP_NEXT_TURN)) {

                if (model.getTournament().isCounterClockwise()) {
                    out.println("You are in the second turn. Action Deleted");
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;
                }


                if (model.getGameBoard().getDraftPool().getListDraftPoolDice().isEmpty()) {
                    out.println(Utils.DRAFTPOOL_EMPTY);
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;
                }

                out.println(Utils.SELECT_DICE_FROM_DRAFTPOOL);
                printSelectDiceDraftpool(model);
                /*for (Dice dice : model.getGameBoard().getDraftPool().getListDraftPoolDice()) {
                    out.println(model.getGameBoard().getDraftPool().getListDraftPoolDice().indexOf(dice) + ":\n" + dice.toColorString());
                }*/

                int numberDice = -1;
                numberDice = selectNumberForRequest(numberDice, 0, model.getGameBoard().getDraftPool().getListDraftPoolDice().size());
                out.println(model.getGameBoard().getDraftPool().getListDraftPoolDice().get(numberDice).toColorString());


                out.println(Utils.YOUR_PLAYERBOARD);
                out.println(player.getPlayerBoard().fullColoredString());

                out.println(ASK_IF_PLACE_DICE);
                out.println(YES_NO_ANSWER);

                int answer = -1;
                answer = selectChoiceForRequest(answer, YES, NO);
                if (answer == NO) {
                    return new AbortRequest(Utils.ABORTED_REQUEST, player);
                }

                out.println(Utils.INSERT_ROW);
                startRow = selectNumberForRequest(startRow, 0, Utils.MAX_ROW_WINDOW_PATTERN);
                out.println(startRow);

                out.println(Utils.INSERT_COL);
                startCol = selectNumberForRequest(startCol, 0, Utils.MAX_COLUMN_WINDOW_PATTERN);
                out.println(startCol);

                if (player.getPlayerBoard().getWindowboard().getCell(startRow, startCol).getDice() != null) {
                    out.println("There is already a dice in this cell");
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;
                }

                return new SkipTurnRequest(Utils.ACTIONTYPE_SKIP_NEXT_TURN, player, model.getGameBoard().getDraftPool().getListDraftPoolDice().get(numberDice), startRow, startCol, model.getTournament().isCounterClockwise());
            }
        }

        return returnedRequest;
    }

    /**
     * Method used to receive the input from the player
     * The Dice must stay within the range
     *
     * @param number
     * @param min
     * @param max
     * @return
     */
    private int selectNumberForRequest(int number, int min, int max) {

        do {
            try {
                in = new Scanner(System.in);
                if (in.hasNextInt()) {
                    String input;
                    input = in.nextLine();
                    number = Integer.parseInt(input.trim());

                    if (number < min || number >= max) {
                        out.println(Utils.INVALID_INPUT_NUMBEROUT_OF_RANGE);
                    }
                } else {
                    out.println(Utils.INVALID_INPUT_NOTANUMBER);
                }


            } catch (Exception e) {
                out.println(Utils.INVALID_INPUT_NOTANUMBER);
            }
        } while (number < min || number >= max);


        return number;

    }

    /**
     * Method used to receive the input from the player
     * The choice must stay within the range
     *
     * @param number
     * @param a
     * @param b
     * @return
     */
    private int selectChoiceForRequest(int number, int a, int b) {

        do {
            try {
                in = new Scanner(System.in);
                if (in.hasNextInt()) {
                    String input;
                    input = in.nextLine();
                    number = Integer.parseInt(input.trim());

                    if (number != a && number != b) {
                        out.println(Utils.INVALID_INPUT_NUMBEROUT_OF_RANGE);
                    }
                } else {
                    out.println(Utils.INVALID_INPUT_NOTANUMBER);
                }


            } catch (Exception e) {
                out.println(Utils.INVALID_INPUT_NOTANUMBER);
            }
        } while (number != a && number != b);


        return number;

    }

    /**
     * Method used to print the selected dice
     * @param model
     */
    public void printSelectDiceDraftpool(Model model) {
        DraftPool draftPool = model.getGameBoard().getDraftPool();
        StringBuilder sr = new StringBuilder();
        for (int i = 0; i < draftPool.getListDraftPoolDice().size(); i++) {
            sr.append(i).append(")   ");
        }
        sr.append("\n");
        for (int k = 0; k < 5; k++) {
            for (Dice dice : draftPool.getListDraftPoolDice()) {
                sr.append(Color.returnASCIIStringFormatted(dice.getColor(), Dice.diceFaces(dice.getValue())[k]));
            }
            sr.append("\n");
        }
        out.println(sr.toString());
    }


}






