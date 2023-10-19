package it.polimi.ingsw.rmi;

import it.polimi.ingsw.controller.message.Message;
import it.polimi.ingsw.controller.message.responses.ActionResponse;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.windowpattern.WindowPatternCard;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Client Interface RMI class
 * <p>
 * This interface is used by the SERVER in order to call the below listed methods
 * and communicate with the client
 */
public interface ClientInterfaceRMI extends Remote {

    /**
     * Method used to print information on the user screen
     *
     * @param infoString string to be displayed
     * @throws RemoteException if a Remote error occurs
     */
    void print(String infoString) throws RemoteException;


    /**
     * Method used to parse the message
     *
     * @param message type of Response: (Response, Message, PrivateResponse)
     * @throws RemoteException
     */
    void parseMessage(Message message) throws RemoteException;

    /**
     * Method used to export the ClientInterfaceRMI to the server
     *
     * @throws RemoteException if a Remote error occurs
     */
    void connect() throws RemoteException;

    /**
     * Method used to set the player by the server
     *
     * @param player player to set
     * @throws RemoteException if an error occur
     */
    void setPlayer(Player player) throws RemoteException;


    void startGameInClient() throws RemoteException;

    void setReceivedWindowPattern() throws RemoteException;

    /**
     * Method used to parse the action message
     *
     * @param actionResponse (used for the toolcards)
     * @throws RemoteException
     */
    void parseAction(ActionResponse actionResponse) throws RemoteException;


    /**
     * Method used to send to the player an array of choices of window pattern cars
     * When the player will make the choice it will send it to the server
     *
     * @param windowPatternCards list of window pattern cards
     * @throws RemoteException if an error occures
     */
    void sendWindowChoicesToPlayer(List<WindowPatternCard> windowPatternCards) throws RemoteException;
}
