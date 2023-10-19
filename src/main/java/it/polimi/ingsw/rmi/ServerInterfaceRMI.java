package it.polimi.ingsw.rmi;

import it.polimi.ingsw.controller.message.requests.Request;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.windowpattern.WindowPatternCard;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Server Interface RMI
 *
 * This interface is used by the client to call the below listed methods of the server.
 */
public interface ServerInterfaceRMI extends Remote {

    /**
     * Method used to register a client
     *
     * @param clientInterfaceRMI exported ClientInterfaceRMI from the ClientRMI
     * @param usernameRequested username chosen by the user
     * @return a boolean to confirm the registration
     * @throws RemoteException if a registration error occurs
     */
    boolean registerClient(ClientInterfaceRMI clientInterfaceRMI, String usernameRequested) throws RemoteException;

    /**
     * Method used to deregister a client from the server
     *
     * @param clientRMI client to deregister
     * @throws RemoteException if a deregistration error occurs
     */
    void deregisterClient(ClientRMI clientRMI) throws RemoteException;

    /**
     * Method used to check if the usernameRequested is already picked or not
     *
     * @param usernameRequested username requested by the user
     * @return a boolean for availability
     * @throws RemoteException if a Remote error occurs
     */
    boolean checkAlreadyExistingUsername(String usernameRequested) throws RemoteException;

    /**
     * Method to get the model in the current game from the client
     * All the actions using this model will be precede by this method
     *
     * @return the model of th current game
     * @throws RemoteException if an error occures
     */
    Model getModelOfCurrentGame() throws RemoteException;

    /**
     * @throws RemoteException
     */
    void acceptWindowPatternCard() throws RemoteException;

    /**
     * @param request
     * @param player
     * @throws RemoteException
     */
    void getRequestFromClient(Request request, Player player) throws RemoteException;


    /**
     * Method used to send from the client to the server the chosen window pattern card
     * @param windowPatternCard selected window pattern card
     * @param player player that have chosen the card
     * @throws RemoteException if an error occure
     */
    boolean getWindowPatternFromPlayer(WindowPatternCard windowPatternCard, Player player) throws RemoteException;

}
