package it.polimi.ingsw.controller.interpreter;

import it.polimi.ingsw.controller.message.requests.Request;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.utils.Color;
import it.polimi.ingsw.utils.Utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

/**
 * InfoInterpreter class
 */
public class InfoInterpreter implements Serializable {

    private Player player;
    private Model model;
    private Scanner in;
    private PrintWriter out;
    Request returnedRequest = null;

    public InfoInterpreter(Model model, Player player, OutputStream outputStream, InputStream inputStream) {
        this.model = model;
        this.player = player;
        this.out = new PrintWriter(outputStream, true);
        this.in = new Scanner(inputStream);

    }

    /**
     * Method used to parse info about the general state of the game
     *
     * @param info
     * @param player
     * @param model
     * @return
     */
    public Request parseInfo(String info, Player player, Model model) {
        returnedRequest = null;
        this.player = player;
        this.model = model;
        for (Player player1: model.getPlayers()){
            if (player.getPlayerNickname().equals(player1.getPlayerNickname())){
                player = player1;
            }
        }

        switch (info.toLowerCase()) {
            case "help":
                out.println("Hello " + Color.ANSI_BLUE.escape() + player.getPlayerNickname() + Color.RESET +
                        " !\nOn your turn, you can perform each of the following action in any order:\n\n" +
                        "  placedice    - Select 1 dice from the DraftPool and place it in an open space on your Board\n" +
                        "  usetool      - Use 1 ToolCard by spending Favor Token\n" +
                        "  pass         - Pass your turn\n\n" + Color.ANSI_RED.escape() +
                        "NOTE: " + Color.RESET + "you can place ONLY 1 DICE in each turn!! \n" + Color.ANSI_YELLOW.escape() +
                        "[ For extra info, use - showhelp- command ]\n" + Color.RESET);
                break;

            case "showhelp":
                if (model.getPlayers().size() == 1) {
                    out.println("Commands to print the current state of game: \n"
                            + "  stat        - Print your current status \n"
                            + "  draftpool   - Print the draftpool of the current round\n"
                            + "  roundtrack  - Print the roundtrack\n"
                            + "  publics     - Print all the public objective cards in the current game\n"
                            + "  privates    - Print all the private objective cards in the current game\n"
                            + "  toolcards   - Print all the tool cards in the current game\n"
                    );

                } else {
                    out.println("Commands to print the current state of game: \n"
                            + "  chat        - Write a message to all \n"
                            + "  stat        - Print your current status \n"
                            + "  draftpool   - Print the draftpool of the current round\n"
                            + "  roundtrack  - Print the roundtrack\n"
                            + "  publics     - Print all the public objective cards\n"
                            + "  myprivate   - Print your private objective card\n"
                            + "  toolcards   - Print all the tool cards\n"
                            + "  otherboards - Print all the boards in the current game (non la tua)\n"
                    );
                }
                break;
            case "stat":
                if (model.getPlayers().size() != 1) {
                    out.println("My Favor Token: " + player.getFavorTokenAmount());
                }
                out.println(player.getPlayerBoard().getWindowboard().getName());
                out.println(player.getPlayerBoard().fullColoredString() + "\n");
                out.println("Now you have: \n" +
                        "- " + model.getTournament().getCurrentTurn().getSelectDiceActionCounter() + " placedice actions left\n" +
                        "- " + model.getTournament().getCurrentTurn().getUseToolCardActionsCounter() + " usetool actions left\n\nLET'S GO!\n");
                break;
            case "draftpool":
                out.println("Actual drafpool:");
                out.println(model.getGameBoard().getDraftPool().toString());
                break;

            case "roundtrack":
                if (model.getGameBoard().getRoundTrack().getNumberofRound() == 1) {
                    out.println(Utils.ROUNDTRACK_EMPTY);
                } else {
                    out.println(model.getGameBoard().getRoundTrack().fullColoredString());
                }
                break;

            case "myprivate":
                if (model.getPlayers().size() != 1) {
                    out.println("Your private objective card:");
                    out.println(player.getPrivateCard());
                }
                break;
            case "privates":
                if (model.getPlayers().size() == 1) {
                    out.println("Private Objective Cards in current game:");
                    StringBuilder priv = new StringBuilder();
                    for (Card card : model.getGameBoard().getPrivatesInGame()) {
                        priv.append(card.toString());
                    }
                    out.println(priv.toString());
                }
                break;
            case "publics":
                out.println("Public Objective Cards in current game:");
                StringBuilder pub = new StringBuilder();
                for (Card card : model.getGameBoard().getPublicsInGame()) {
                    pub.append(card.toString());
                }
                out.println(pub.toString());
                break;

            case "toolcards":
                if (model.getGameBoard().getToolcardsInGame().isEmpty()) {
                    out.println(Utils.NO_MORE_TOOLCARDINGAME);
                } else {
                    out.println("Toolcards in current game:");
                    StringBuilder tool = new StringBuilder();
                    for (Card card : model.getGameBoard().getToolcardsInGame()) {
                        tool.append(card.toString());
                    }
                    out.println(tool.toString());
                }
                break;

            case "otherboards":
                if (model.getPlayers().size() != 1) {

                    for (Player p : model.getTournament().getRoundPlayers()) {
                        if (!p.getPlayerNickname().equals(player.getPlayerNickname())) {
                            out.println("Player: " + p.getPlayerNickname() + "\n"
                                    + "Favor Token: " + p.getFavorTokenAmount() + "\n"
                                    + "Name of window pattern card " + p.getPlayerBoard().getWindowboard().getName() + "\n"
                                    + p.getPlayerBoard().fullColoredString());
                            /*out.println("Actions: \n" +
                                    "- " + model.getTournament().getCurrentTurn().getSelectDiceActionCounter() + " placedice actions left\n" +
                                    "- " + model.getTournament().getCurrentTurn().getUseToolCardActionsCounter() + " usetool actions left\n");
                        */
                        }
                    }
                }
                break;
        }

        return null;
    }
}
