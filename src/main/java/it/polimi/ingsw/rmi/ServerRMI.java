package it.polimi.ingsw.rmi;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.message.requests.Request;
import it.polimi.ingsw.controller.message.responses.Response;
import it.polimi.ingsw.controller.message.responses.UpdateModelResponse;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.cards.privates.PrivateObjectiveCard;
import it.polimi.ingsw.model.cards.toolcards.ToolCard;
import it.polimi.ingsw.model.cards.toolcards.actions.Action;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.PlayerBoard;
import it.polimi.ingsw.model.windowpattern.WindowPatternCard;
import it.polimi.ingsw.utils.Color;
import it.polimi.ingsw.utils.Utils;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.rmi.NetworkUtils.MIN_PLAYER_TO_START;
import static it.polimi.ingsw.rmi.NetworkUtils.TIMER_DELAY;

/**
 * Main class of the Server RMI
 * It is used to start the game
 */
public class ServerRMI implements ServerInterfaceRMI {

    private static final Logger logger = Logger.getLogger(ServerRMI.class.getName());

    private boolean gameStarted = false;

    private Map<ClientInterfaceRMI, Player> connections = new HashMap<>();

    private List<PlayerViewOnServer> playersViewOnServer = new ArrayList<>();

    private Model modelOfCurrentGame;
    private boolean timerStarted = false;

    private Timer timer;

    private List<WindowPatternCard> allWindowPatterns = new ArrayList<>();


    private int acceptedWindowPattern = 0;

    private List<Player> completedPlayerList = new ArrayList<>();


    /**
     * Method used to check if the username requested by the user already exists
     *
     * @param usernameRequested username requested by the user
     * @return a boolean value for availability
     */
    @Override
    public boolean checkAlreadyExistingUsername(String usernameRequested) {
        System.out.println("Attempting to connect the new player " + usernameRequested);
        for (Player player : connections.values()) {
            if (player.getPlayerNickname().equals(usernameRequested)) {
                System.out.println("Failed, a player with that username was already logged");
                return false;
            }
        }
        System.out.println("Success! The player " + usernameRequested + " is now logged");
        return true;
    }

    @Override
    public Model getModelOfCurrentGame() throws RemoteException {
        return modelOfCurrentGame;
    }

    /**
     * @throws RemoteException
     */
    @Override
    public void acceptWindowPatternCard() throws RemoteException {

    }

    @Override
    public void getRequestFromClient(Request request, Player player) throws RemoteException {
        for (PlayerViewOnServer playerViewOnServer : playersViewOnServer) {
            if (playerViewOnServer.getPlayer().getPlayerNickname().equals(player.getPlayerNickname())) {
                playerViewOnServer.notifyAllTheObservers(request);
            }
        }
    }

    /**
     * Method used to send from the client to the server the chosen window pattern card
     *
     * @param windowPatternCard selected window pattern card
     * @param player            player that have chosen the card
     * @throws RemoteException if an error occure
     */
    @Override
    public boolean getWindowPatternFromPlayer(WindowPatternCard windowPatternCard, Player player) throws RemoteException {
        for (WindowPatternCard single : allWindowPatterns) {
            if (single.equals(windowPatternCard)) {
                player.setPlayboard(new PlayerBoard(Utils.RED, single));
                player.setFavorTokenAmount(single.getDifficulty());
                completedPlayerList.add(player);
                acceptedWindowPattern += 1;
                modelOfCurrentGame.notifyViews(new Response(player.getPlayerNickname() + " has chosen a windowPatternCard!"));

                modelOfCurrentGame.notifyViews(new Response(player.getPlayerBoard().getWindowboard().fullColoredString() + " was chosen as windowPatternCard!"));


                return true;
            }
        }
        return false;
    }

    /**
     * Method used to register the client into the server
     *
     * @param clientInterfaceRMI interface of the client rmi. It is used in order to call methods of the client
     *                           rmi interface stub
     * @param usernameRequested  username requested by the user for the game
     * @return a boolean
     */
    @Override
    public boolean registerClient(ClientInterfaceRMI clientInterfaceRMI, String usernameRequested) {
        try {
            if (checkAlreadyExistingUsername(usernameRequested) && !gameStarted) {
                connections.put(clientInterfaceRMI, new Player(usernameRequested));
                clientInterfaceRMI.print("You registered as " + usernameRequested);
                clientInterfaceRMI.print("As soon as there will be enough player the game will start..");

                for (ClientInterfaceRMI cir : connections.keySet()) {
                    for (Player p : connections.values()
                            ) {
                        if (p.getPlayerNickname().equals(usernameRequested)) {
                            cir.print(Color.ANSI_PURPLE.escape() + "Player " +
                                    usernameRequested + " added in the queue! Numbers of player connected: " +
                                    connections.keySet().size() + Color.RESET);
                        }

                    }
                }
                checkIfReady();
                return true;
            }
            if (gameStarted) {
                clientInterfaceRMI.print("The game is already started. Try later");
                return false;
            }
            if (!checkAlreadyExistingUsername(usernameRequested)) {
                clientInterfaceRMI.print("The username you choose was already picked. Try another");
                return false;
            }
        } catch (Exception e) {
            System.out.println("An error with registration occured");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Method used to check if the players are enought to start the game
     */
    private void checkIfReady() {
        try {
            if (connections.size() == NetworkUtils.MAX_PLAYER_TO_START) {
                gameStarted = true;
                for (ClientInterfaceRMI clientInterfaceRMI : connections.keySet()) {
                    clientInterfaceRMI.print("Game starting..");
                    clientInterfaceRMI.print("Wait your turn of choosing the Window Pattern Card for this game");
                }
                startSagrada();
            } else if (connections.size() >= MIN_PLAYER_TO_START) {
                // Start timer if not already started
                if (!timerStarted) {
                    timerStarted = true;
                    timer = new Timer();
                    logger.log(Level.SEVERE, "Timer started...");
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (!gameStarted) {
                                gameStarted = true;
                                for (ClientInterfaceRMI clientInterfaceRMI : connections.keySet()) {
                                    try {
                                        clientInterfaceRMI.print("Game starting..");
                                        clientInterfaceRMI.print("Wait your turn of choosing the Window Pattern Card for this game");
                                    } catch (RemoteException e) {
                                        logger.log(Level.SEVERE, "An error occured in starting game with timer");
                                    }
                                }
                                startSagrada();
                            }
                        }
                    }, TIMER_DELAY * 1000);
                }

            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    /**
     * Method used to deregister a client from the server and end its connection
     *
     * @param clientRMI
     */
    @Override
    public void deregisterClient(ClientRMI clientRMI) {
        connections.remove(clientRMI);
    }


    /**
     * Method used to start the game
     * <p>
     * Here happens the creation of the modl, controller and all the views of the players
     */
    private void startSagrada() {
        try {


            List<Player> playersInGame = new ArrayList<>(connections.values());

            modelOfCurrentGame = new Model(playersInGame);
            Controller controller = new Controller(modelOfCurrentGame);

            playersViewOnServer = new ArrayList<>();
            for (Map.Entry<ClientInterfaceRMI, Player> connection : connections.entrySet()) {
                PlayerViewOnServer playerViewOnServer = new PlayerViewOnServer(connection.getKey(), connection.getValue());
                playersViewOnServer.add(playerViewOnServer);

                modelOfCurrentGame.addObserver(playerViewOnServer);
                playerViewOnServer.addObserver(controller);
            }

            for (Card card : modelOfCurrentGame.getGameBoard().getToolcardsInGame()) {
                for (Action action : ((ToolCard) card).getActionToPerform()) {
                    action.setModel(modelOfCurrentGame);
                }
            }

            allWindowPatterns = modelOfCurrentGame.getGameBoard().getWindowpatterndeck().getListofwindowpattern();
            int incre = 0;
            for (PlayerViewOnServer playerViewOnServer : playersViewOnServer) {
                List<WindowPatternCard> windowPatternCards = new ArrayList<>();
                for (int i = 0; i < Utils.MAX_WINDOW_PATTERN_PLAYER; i++) {
                    windowPatternCards.add(modelOfCurrentGame.getGameBoard().getWindowpatterndeck().getListofwindowpattern().get(incre + i));
                }
                playerViewOnServer.sendWindowPatternCards(windowPatternCards);
                incre += Utils.MAX_WINDOW_PATTERN_PLAYER;
            }

            while (acceptedWindowPattern < playersInGame.size()) {
                //Thread.sleep(100);
            }


            for (ClientInterfaceRMI clientInterfaceRMI : connections.keySet()) {
                clientInterfaceRMI.parseMessage(new Response(printSagradaLogo()));
                clientInterfaceRMI.startGameInClient();
            }

            for (Player player : completedPlayerList) {
                player.setPrivateCard(((PrivateObjectiveCard) modelOfCurrentGame.getGameBoard().getPrivates().pickCard()));
            }

            modelOfCurrentGame.setPlayers(completedPlayerList);


            modelOfCurrentGame.notifyViews(new UpdateModelResponse("updateModelMessage", modelOfCurrentGame, null));

            modelOfCurrentGame.getTournament().startGame();

            modelOfCurrentGame.notifyViews(new UpdateModelResponse("updateModelMessage", modelOfCurrentGame, null));


        } catch (Exception e) {
            System.out.println("An error occured in startSagrada method");
            e.printStackTrace();
        }
    }

    /**
     * Main method called to start the server
     *
     * @param args optional arguments
     */
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Insert your local IP address");
            String localIP = scanner.nextLine();

            System.setProperty("java.rmi.server.hostname", localIP);

            ServerRMI serverRMI = new ServerRMI();
            ServerInterfaceRMI stub = (ServerInterfaceRMI) UnicastRemoteObject.exportObject(serverRMI, NetworkUtils.RMI_PORT);

            Registry registry = LocateRegistry.createRegistry(NetworkUtils.RMI_PORT);
            registry.bind("ServerInterfaceRMI", stub);
            logger.log(Level.SEVERE, "Server ready!");

        } catch (Exception e) {
            System.out.println("Server error");
            e.printStackTrace();
        }
    }

    /**
     * Method to return a string for the main colored logo of Sagrada
     * Generated from: patorjk.com/software/taag/
     * Font: Calvin S
     *
     * @return a colored string with the logo of Sagrada
     */
    public String printSagradaLogo() {
        return (
                Color.ANSI_GREY.escape() + "\n   ▄████████    ▄████████    ▄██████▄     ▄████████    ▄████████ ████████▄     ▄████████ \n" +
                        Color.ANSI_GREY.escape() + "  ███    ███   ███    ███   ███    ███   ███    ███   ███    ███ ███   ▀███   ███    ███ \n" +
                        Color.ANSI_GREY.escape() + "  ███    █▀    ███    ███   ███    █▀    ███    ███   ███    ███ ███    ███   ███    ███ \n" +
                        Color.ANSI_GREY.escape() + "  ███          ███    ███  ▄███         ▄███▄▄▄▄██▀   ███    ███ ███    ███   ███    ███ \n" +
                        Color.ANSI_GREY.escape() + "▀███████████ ▀███████████ ▀▀███ ████▄  ▀▀███▀▀▀▀▀   ▀███████████ ███    ███ ▀███████████ \n" +
                        Color.ANSI_GREY.escape() + "         ███   ███    ███   ███    ███ ▀███████████   ███    ███ ███    ███   ███    ███ \n" +
                        Color.ANSI_GREY.escape() + "   ▄█    ███   ███    ███   ███    ███   ███    ███   ███    ███ ███   ▄███   ███    ███ \n" +
                        Color.ANSI_GREY.escape() + " ▄████████▀    ███    █▀    ████████▀    ███    ███   ███    █▀  ████████▀    ███    █▀  \n" + Color.RESET);

    }



}
