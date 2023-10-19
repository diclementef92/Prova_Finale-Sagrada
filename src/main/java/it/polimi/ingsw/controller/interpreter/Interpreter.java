package it.polimi.ingsw.controller.interpreter;

import it.polimi.ingsw.controller.message.requests.*;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.dice.Dice;
import it.polimi.ingsw.model.dice.DraftPool;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.utils.Color;
import it.polimi.ingsw.utils.Utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;


/**
 * Interpreter Class
 */
public class Interpreter implements Serializable {

    private Player player;
    private Model model;
    private Scanner in;
    private PrintWriter out;
    Request returnedRequest = null;
    private InfoInterpreter infoInterpreter = null;

    private static final String ASK_IF_PLACE_DICE = "Do you want to use the dice to activate the ToolCard?";
    private static final String YES_NO_ANSWER = "Insert: \n0 - YES\n1 - NO\nIf you select NO, you will lose the dice.\nthe action can not be canceled\n?\n";
    private static final int YES = 0;
    public static final int NO = 1;
    public static final String PASS_REQUEST = "I pass the Turn";
    public static final String TOOLCARD_REQUEST = "ToolCard Usage Message";

    /**
     * @param outputStream
     * @param model
     * @param player
     * @param in
     */
    public Interpreter(OutputStream outputStream, Model model, Player player, InputStream in) {
        this.out = new PrintWriter(outputStream, true);
        this.model = model;
        this.player = player;
        this.in = new Scanner(in);
        this.infoInterpreter = new InfoInterpreter(model, player, outputStream, in);

    }

    /**
     * Default method for parsing messages
     *
     * @param command
     * @return
     */
    public Request parse(String command) {
        returnedRequest = null;
        for (Player player1 : model.getPlayers()) {
            if (player.getPlayerNickname().equals(player1.getPlayerNickname())) {
                player = player1;
            }
        }
        switch (command.toLowerCase()) {

            /**
             * INFO-INTERPRETER
             */
            case "help":

            case "showhelp":

            case "stat":

            case "draftpool":

            case "roundtrack":

            case "myprivate":

            case "privates":

            case "publics":

            case "toolcards":

            case "otherboards":

                infoInterpreter.parseInfo(command, player, model);
                break;

            case "chat":
                if (model.getPlayers().size() != 1) {
                    out.println("Write a message");
                    String text = in.nextLine();
                    returnedRequest = new ChatMessageRequest(Color.ANSI_RED.escape() + "[" + Utils.getTime() + "][All] " + Color.ANSI_BLUE.escape() + player.getPlayerNickname() + ": " + text + Color.RESET, player);
                }
                break;

            /**
             Main action:
             1- pass
             2- placedice
             3- usetool
             */

            case "pass":
                if (!player.getPlayerNickname().equals(model.getTournament().getRoundPlayers().get(model.getTournament().getCurrentRoundPlayerNumber()).getPlayerNickname())) {
                    out.println(Utils.NOT_YOUR_TURN);
                    return returnedRequest;

                }
                returnedRequest = new PassTurnRequest(PASS_REQUEST, player);
                break;


            case "placedice":

                /**
                 *  Check
                 *  - MY TURN
                 *  - JUST USED PLACEDICE ACTION
                 *  - DRAFPOOL NOT EMPTY
                 *
                 */
                if (!player.getPlayerNickname().equals(model.getTournament().getRoundPlayers().get(model.getTournament().getCurrentRoundPlayerNumber()).getPlayerNickname())) {
                    out.println(Utils.NOT_YOUR_TURN);
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;

                } else if (model.getTournament().getCurrentTurn().getSelectDiceActionCounter() == 0) {
                    out.println(Utils.NO_MORE_DICE_PLACEMENT);
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;

                } else if (model.getGameBoard().getDraftPool().getListDraftPoolDice().isEmpty()) {
                    out.println(Utils.DRAFTPOOL_EMPTY);
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;
                }
                /**
                 *  END CHECK
                 */

                out.println(Utils.YOUR_PLAYERBOARD);
                out.println(player.getPlayerBoard().fullColoredString());


                out.println(Utils.SELECT_DICE_FROM_DRAFTPOOL);
                printSelectDiceDraftpool(model);
                /*for (Dice dice : model.getGameBoard().getDraftPool().getListDraftPoolDice()) {
                    out.println(model.getGameBoard().getDraftPool().getListDraftPoolDice().indexOf(dice) + ":\n" + dice.toColorString());
                }*/

                int numberDice = -1;
                numberDice = selectNumberForRequest(numberDice, 0, model.getGameBoard().getDraftPool().getListDraftPoolDice().size());
                out.println("You select : \n" + model.getGameBoard().getDraftPool().getListDraftPoolDice().get(numberDice).toColorString());


                int row = -1;
                int col = -1;

                out.println(Utils.INSERT_ROW);
                row = selectNumberForRequest(row, 0, Utils.MAX_ROW_WINDOW_PATTERN);

                out.println(Utils.INSERT_COL);
                col = selectNumberForRequest(col, 0, Utils.MAX_COLUMN_WINDOW_PATTERN);

                returnedRequest = new PlaceDiceRequest("", model.getGameBoard().getDraftPool().getListDraftPoolDice().get(numberDice),
                        row, col, player);
                break;

            case "usetool":

                /**
                 * VARI CONTROLLI PER USARE UNA TOOLCARD:
                 *  - CONTROLLO SE E' IL MIO TURNO
                 *  - CONTROLLO SE HO GIA' USATO AZIONI TOOLCARD
                 *
                 * /
                 */

                if (!player.getPlayerNickname().equals(model.getTournament().getRoundPlayers().get(model.getTournament().getCurrentRoundPlayerNumber()).getPlayerNickname())) {
                    out.println(Utils.NOT_YOUR_TURN);
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;

                }

                //DA RIMUOVERE PER USARE LA TOOLCARD
                if (model.getTournament().getCurrentTurn().getUseToolCardActionsCounter() == 0) {
                    out.println(Utils.NO_MORE_TOOLCARD_ACTIONS);
                    out.println(Utils.DELETE_ACTION);
                    return returnedRequest;
                }

                /**
                 *  FOR single player
                 *  - DRAFTPOOL NOT EMPTY
                 *  - TOOLCARD IN GAME NOT 0
                 *
                 */
                if (model.getPlayers().size() == 1) {
                    if (model.getGameBoard().getDraftPool().getListDraftPoolDice().isEmpty()) {
                        out.println(Utils.DRAFTPOOL_EMPTY);
                        out.println(Utils.DELETE_ACTION);
                        return returnedRequest;
                    }

                    if (model.getGameBoard().getToolcardsInGame().isEmpty()) {
                        out.println(Utils.NO_MORE_TOOLCARDINGAME);
                        out.println(Utils.DELETE_ACTION);
                        return returnedRequest;
                    }
                }

                /**
                 * FINISH CHECK
                 */

                out.println(Utils.SELECT_TOOLCARD);
                StringBuilder sp = new StringBuilder();
                for (int j = 0; j < model.getGameBoard().getToolcardsInGame().size(); j++) {
                    sp.append("[").append(j).append("]:\n").append(model.getGameBoard().getToolcardsInGame().get(j).toString());
                }
                out.println(sp.toString());


                int numberToolcard = -1;
                numberToolcard = selectNumberForRequest(numberToolcard, 0, model.getGameBoard().getToolcardsInGame().size());
                out.println("You select : \n" + model.getGameBoard().getToolcardsInGame().get(numberToolcard).toString());

                returnedRequest = new ToolcardUsageRequest(TOOLCARD_REQUEST, numberToolcard, player);
                break;
        }

        return returnedRequest;
    }


    /**
     * Method used to receive the input from the player
     * The number must stay within the range
     *
     * @param number
     * @param min
     * @param max
     * @return
     */
    public int selectNumberForRequest(int number, int min, int max) {

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
     *
     * @param model
     */
    public void printSelectDiceDraftpool(Model model) {
        DraftPool draftPool = model.getGameBoard().getDraftPool();
        StringBuilder sr = new StringBuilder();
        for (int i = 0; i < draftPool.getListDraftPoolDice().size(); i++) {
            sr.append(i).append(":       ");
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