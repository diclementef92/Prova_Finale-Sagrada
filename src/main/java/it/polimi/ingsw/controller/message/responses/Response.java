package it.polimi.ingsw.controller.message.responses;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.message.Message;

/**
 * Response class
 */
public class Response extends Message {


    /**
     * Main constructor for Response
     * @param message
     */
    public Response(String message) {
        super(message);
    }

    /**
     * Method used to decrypt the message
     * @param controller
     */
    @Override
    public void decrypt(Controller controller) {

    }


}
