package it.polimi.ingsw.rmi;

import it.polimi.ingsw.controller.interpreter.ActionInterpreter;
import it.polimi.ingsw.controller.interpreter.Interpreter;
import it.polimi.ingsw.controller.message.Message;
import it.polimi.ingsw.controller.message.requests.ActionRequest;
import it.polimi.ingsw.controller.message.requests.Request;
import it.polimi.ingsw.controller.message.responses.ActionResponse;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.windowpattern.WindowPatternCard;
import it.polimi.ingsw.utils.Utils;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.utils.Utils.*;

/**
 * Definition of the class of the Client RMI
 */
public class ClientRMI implements ClientInterfaceRMI, Runnable {


    private static final Logger logger = Logger.getLogger(ClientRMI.class.getName());

    private Registry registry;
    private ServerInterfaceRMI serverStub;
    private String usernameRequested;


    private List<WindowPatternCard> windowPatternCards = new ArrayList<>();

    private volatile boolean gameIsStarted = false;


    private volatile boolean receivedWindowPattern = false;

    private Player player;

    private ClientInterfaceRMI myClientInterfaceRMI;

    String serverAddress = NetworkUtils.RMI_SERVER_ADDRESS;
    int port = NetworkUtils.RMI_PORT;
    private Scanner scanner;


    /**
     * Main class for the execution of the client in RMI mode
     */
    public ClientRMI() {
        this.run();
    }

    /**
     * Method used to print information to the client
     *
     * @param stringa string to be printed
     */
    @Override
    public void print(String stringa) {
        System.out.println(stringa);
    }

    @Override
    public void parseMessage(Message message) throws RemoteException {
        //if (message instanceof Response) {
        System.out.println(message.getMessageContent());
        //}
    }

    /**
     * Method used to export a ClientInterfaceRMI serverStub to the server and make it accessible to it
     *
     * @throws RemoteException for Client error
     */
    @Override
    public void connect() throws RemoteException {
        try {
            myClientInterfaceRMI = (ClientInterfaceRMI) UnicastRemoteObject.exportObject(this, 0);
        } catch (Exception e) {
            System.out.println("RMI Client Error occured");
            e.printStackTrace();
        }
    }

    @Override
    public void setPlayer(Player player) throws RemoteException {
        this.player = player;
    }


    @Override
    public void startGameInClient() throws RemoteException {
        gameIsStarted = true;
    }

    @Override
    public void setReceivedWindowPattern() throws RemoteException {
        this.receivedWindowPattern = true;
    }

    @Override
    public void parseAction(ActionResponse actionResponse) throws RemoteException {
        ActionInterpreter actionInterpreter = new ActionInterpreter(System.out, serverStub.getModelOfCurrentGame(), player, System.in);
        ActionRequest actionRequest = actionInterpreter.parseAction(actionResponse);
        if (actionRequest != null) {
            serverStub.getRequestFromClient(actionRequest, player);
        }

    }

    /**
     * Method used to send to the player an array of choices of window pattern cars
     * When the player will make the choice it will send it to the server
     *
     * @param windowPatternCards list of window pattern cards
     * @throws RemoteException if an error occures
     */
    @Override
    public void sendWindowChoicesToPlayer(List<WindowPatternCard> windowPatternCards) throws RemoteException {
        this.windowPatternCards = windowPatternCards;

        receivedWindowPattern = true;

        boolean acceptedWindowPattern = false;
        while (!acceptedWindowPattern) {
            System.out.println("Please select a window board\n");
            for (int i = 0; i < Utils.MAX_WINDOW_PATTERN_PLAYER; i++) {
                System.out.println(windowPatternCards.get(i).fullColoredString());
            }

            int val = 0;

            val = Integer.parseInt(scanner.nextLine());
            if(val<0||val>Utils.MAX_WINDOW_PATTERN_PLAYER){
                WindowPatternCard selected = windowPatternCards.get(0);
                acceptedWindowPattern = serverStub.getWindowPatternFromPlayer(selected, player);
            } else {
                WindowPatternCard selected = windowPatternCards.get(val);
                acceptedWindowPattern = serverStub.getWindowPatternFromPlayer(selected, player);
            }

        }
        System.out.println("Windowcard accepted");


    }

    /**
     * Main method used to run the client RMI
     *
     * @param args optional arguments
     */
    public static void main(String[] args) {

        ClientRMI clientRMI = new ClientRMI();
    }


    /**
     * @param number default value
     * @param min    min integer number
     * @param max    max integer number
     * @return an integer
     */
    public int selectNumberForRequest(int number, int min, int max) {

        do {
            try {
                //scanner = new Scanner(System.in);
                if (scanner.hasNextInt()) {
                    String input;
                    input = scanner.nextLine();
                    number = Integer.parseInt(input.trim());

                    if (number < min || number >= max) {
                        System.out.println(Utils.INVALID_INPUT_NUMBEROUT_OF_RANGE);
                    }
                } else {
                    System.out.println(Utils.INVALID_INPUT_NOTANUMBER);
                }
            } catch (Exception e) {
                System.out.println(Utils.INVALID_INPUT_NOTANUMBER);
            }
        } while (number < min || number >= max);


        return number;

    }

    @Override
    public void run() {
        Scanner in = new Scanner(System.in);

        try {
            scanner = new Scanner(System.in);

            System.out.println("Insert your local IP address");
            String localIP = scanner.nextLine();
            System.out.println("Insert the server's IP");
            String serverIP = scanner.nextLine();


            System.setProperty("java.rmi.server.hostname", localIP);

            //Starting the registry
            registry = LocateRegistry.getRegistry(serverIP, port);
            //Creation of the serverStub, the shared object between server and client
            serverStub = (ServerInterfaceRMI) registry.lookup("ServerInterfaceRMI");

            connect();

            boolean acceptedUsername = false;
            while (!acceptedUsername) {
                System.out.println("Please select a unique username:");
                usernameRequested = in.nextLine();
                acceptedUsername = serverStub.registerClient(myClientInterfaceRMI, usernameRequested);
            }

            /*while (!receivedWindowPattern) {

            }*/


            while (!gameIsStarted) {
                Thread.sleep(100);
            }

            //GAMEPLAY
            // Loop for commands

            for (Player upPlayer : serverStub.getModelOfCurrentGame().getPlayers()) {
                if (player.getPlayerNickname().equals(upPlayer.getPlayerNickname())) {
                    player = upPlayer;
                }
            }
            while (gameIsStarted) {
                String command = in.nextLine();
                Interpreter interpreter = new Interpreter(System.out, serverStub.getModelOfCurrentGame(), player, System.in);
                Request request = interpreter.parse(command.trim());
                if (request != null) {
                    serverStub.getRequestFromClient(request, player);
                }

                System.out.println(INSERT_COMMAND_COLOREDSTRING);
            }


        } catch (Exception e) {
            logger.log(Level.SEVERE, "There is no server! Please restart your client and connect to a running server");
            e.printStackTrace();
        }
    }
}
