package it.polimi.ingsw.rmi;

import it.polimi.ingsw.controller.message.Message;
import it.polimi.ingsw.controller.message.responses.ActionResponse;
import it.polimi.ingsw.controller.message.responses.PrivateResponse;
import it.polimi.ingsw.controller.message.responses.Response;
import it.polimi.ingsw.controller.message.responses.UpdateModelResponse;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.windowpattern.WindowPatternCard;
import it.polimi.ingsw.view.View;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Observable;

/**
 * Player view on server class
 * <p>
 * This class is an extension of the View class and is instantiated once for each player
 * when he comes in game. This view is notified by the model of the current game in order
 * to communicate to the player all relevant updates
 */
public class PlayerViewOnServer extends View {

    private ClientInterfaceRMI clientInterfaceRMI;
    private Player player;


    /**
     * Main constructor of the class Player View On server
     * It receives as parameters all the information require to create the object
     *
     * @param clientInterfaceRMI Client Interface RMI of the player joining the game
     * @param player             Instance of the player in the game
     */
    public PlayerViewOnServer(ClientInterfaceRMI clientInterfaceRMI, Player player) {
        this.clientInterfaceRMI = clientInterfaceRMI;
        this.player = player;
        try {
            clientInterfaceRMI.setPlayer(player);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to obtain information through the notify method of the model
     * After receiving those information it sends them to the client
     *
     * @param observable the model observed in that moment
     * @param o          the message received by the notify method
     */
    @Override
    public void update(Observable observable, Object o) {


        if (observable instanceof Model && o instanceof Message) {
            try {
                Model model = (Model) observable;
                for (Player player : model.getTournament().getRoundPlayers()){
                    if (player.getPlayerNickname().equals(this.player.getPlayerNickname())){
                        clientInterfaceRMI.setPlayer(player);
                    }
                }
                if (o instanceof PrivateResponse){
                    if (player.getPlayerNickname().equals(((PrivateResponse) o).getPlayer().getPlayerNickname())){
                        clientInterfaceRMI.parseMessage((PrivateResponse) o);
                    }
                }
                else if (o instanceof UpdateModelResponse) {
                    clientInterfaceRMI.startGameInClient();
                    clientInterfaceRMI.setPlayer(player);

                    System.out.println("I notified the client of "+ ((Message) o).getMessageContent()+"with player " + player.getPlayerNickname());
                }
                else if (o instanceof ActionResponse){

                    if (player.getPlayerNickname().equals(model.getTournament().getRoundPlayers().get(
                            model.getTournament().getCurrentRoundPlayerNumber()
                    ).getPlayerNickname())) {
                        clientInterfaceRMI.parseAction((ActionResponse) o);
                    }
                }
                else if (o instanceof Response){
                    clientInterfaceRMI.parseMessage(((Response) o));
                }
                else {
                    clientInterfaceRMI.parseMessage((Message) o);
                }
            } catch (RemoteException e) {
                System.out.println("An error occured with the PlayerViewOnServer class while receiving an update from the model");
                e.printStackTrace();
            }
        }
    }


    /**
     * Getter used to retrieve the information of the player
     *
     * @return the player contained in this class
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Getter used to retrieve the information about the ClientInterfaceRMI
     * contained in this class
     *
     * @return the ClientInformationRMI contained in this class
     */
    public ClientInterfaceRMI getClientInterfaceRMI() {
        return clientInterfaceRMI;
    }


    /**
     * Method used to send the choices of window pattern cards to th single player of this playerview
     * @param windowPatternCards list of windowpattern cards
     */
    public void sendWindowPatternCards(List<WindowPatternCard> windowPatternCards){
        try {
            clientInterfaceRMI.sendWindowChoicesToPlayer(windowPatternCards);
            clientInterfaceRMI.setReceivedWindowPattern();
        } catch (RemoteException e) {
            //e.printStackTrace();
        }
    }



}
