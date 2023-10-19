package it.polimi.ingsw.view;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.interpreter.ActionInterpreter;
import it.polimi.ingsw.controller.interpreter.Interpreter;
import it.polimi.ingsw.controller.message.Message;
import it.polimi.ingsw.controller.message.requests.ActionRequest;
import it.polimi.ingsw.controller.message.requests.Request;
import it.polimi.ingsw.controller.message.responses.ActionResponse;
import it.polimi.ingsw.controller.message.responses.Response;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.cards.toolcards.ToolCard;
import it.polimi.ingsw.model.cards.toolcards.actions.Action;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.utils.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;


/**
 * CLASS USED TO RUN THE GAME WITHOUT CONNECTION!
 * I GIOCATORI GIOCHEREBBERO "SCAMBIANDOSI" IL COMPUTER,
 * GIOCANDO IN LOCALE.
 * QUINDI C'è UN'UNICA VIEW CHE
 * VIENE AGGIORNATA COSTANTEMENTE.
 * <p>
 * DA USARE SOLO COME TEST!!!!!!!!
 */
public class SingleTerminal extends View {

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Model && arg instanceof Response) {
            System.out.println(((Message) arg).getMessageContent());
        } else if (o instanceof Model && arg instanceof ActionResponse) {
            ActionRequest request = null;
            ActionInterpreter actionInterpreter = new ActionInterpreter(System.out, (Model) o,
                    ((Model) o).getTournament().getRoundPlayers().get(((Model) o).getTournament().getCurrentRoundPlayerNumber()), System.in);
            request = actionInterpreter.parseAction((ActionResponse) arg);
            if (request != null) {
                this.setChanged();
                this.notifyObservers(request);
            }
        }
    }

    // E' UN TEST DEL GIOCO
    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        List<Player> players = new ArrayList<>();

        int playerNumber = 0;

        boolean gameRunning = true;

        SingleTerminal.printLogo();

        System.out.println("\nInsert the number of players\n");


        //Inserimento del numero di giocatori
        playerNumber = Integer.parseInt(in.nextLine());

        //da sistemare
        for (int i = 0; i < playerNumber; i++) {
            System.out.println("Insert the name of a player\n");
            String name = in.nextLine();
            players.add(new Player(name));
        }

        //Creazione del model, controller e view/singleTerminal
        Model model = new Model(players);

        Controller controller = new Controller(model);
        SingleTerminal singleTerminal = new SingleTerminal();

        // Aggiunta degli observer
        singleTerminal.addObserver(controller);
        model.addObserver(singleTerminal);

        System.out.println("E' iniziata la partita \n");

        //per ogni toolcard nel game, prendi tutte le azioni e per ogni azione setto il model
        for (Card card : model.getGameBoard().getToolcardsInGame()) {
            for (Action action : ((ToolCard) card).getActionToPerform()) {
                action.setModel(model);
            }
        }

        model.getTournament().startGame();
        System.out.println(Color.ANSI_YELLOW.escape() + "\n[Insert - help - to show a list of commands]\n" + Color.RESET);

        while (gameRunning) {

            String comando = in.nextLine();
            Interpreter interpreter = new Interpreter(System.out, model, model.getTournament().getRoundPlayers().get(model.getTournament().getCurrentRoundPlayerNumber()), System.in);
            Request request = interpreter.parse(comando);
            if (request != null) {
                singleTerminal.setChanged();
                singleTerminal.notifyObservers(request);
            }

        }


    }

    public static void printLogo() {
        System.out.println(
                Color.ANSI_BLUE.escape() + "        _____ " + Color.ANSI_RED.escape() + "                          " + Color.ANSI_BLUE.escape() + "_              \n" +
                        Color.ANSI_BLUE.escape() + "       /  ___|" + Color.ANSI_RED.escape() + "                         " + Color.ANSI_BLUE.escape() + "| |             \n" +
                        Color.ANSI_BLUE.escape() + " ______\\ `--." + Color.ANSI_RED.escape() + "  __ _ " + Color.ANSI_YELLOW.escape() + " __ _ " + Color.ANSI_PURPLE.escape() + "_ __ __ _ " + Color.ANSI_BLUE.escape() + " __| | __ _ " + Color.ANSI_GREEN.escape() + "______ \n" +
                        Color.ANSI_BLUE.escape() + "|______|`--. \\" + Color.ANSI_RED.escape() + "/ _` |" + Color.ANSI_YELLOW.escape() + "/ _` | " + Color.ANSI_PURPLE.escape() + "'__/ _` |" + Color.ANSI_BLUE.escape() + "/ _` |/ _` |" + Color.ANSI_GREEN.escape() + "______|\n" +
                        Color.ANSI_BLUE.escape() + "       /\\__/ /" + Color.ANSI_RED.escape() + " (_| |" + Color.ANSI_YELLOW.escape() + " (_| | " + Color.ANSI_PURPLE.escape() + "| | (_| | " + Color.ANSI_BLUE.escape() + "(_| | (_| |" + Color.ANSI_GREEN.escape() + "       \n" +
                        Color.ANSI_BLUE.escape() + "       \\____/ " + Color.ANSI_RED.escape() + "\\__,_|" + Color.ANSI_YELLOW.escape() + "\\__, |" + Color.ANSI_PURPLE.escape() + "_|  \\__,_|" + Color.ANSI_BLUE.escape() + "\\__,_|\\__,_| " + Color.ANSI_GREEN.escape() + "      \n" +
                        Color.ANSI_BLUE.escape() + "                " + Color.ANSI_RED.escape() + "    " + Color.ANSI_YELLOW.escape() + " __/ |                             \n" +
                        Color.ANSI_BLUE.escape() + "                 " + Color.ANSI_RED.escape() + "   " + Color.ANSI_YELLOW.escape() + "|___/                              " + Color.RESET);
    }

}
