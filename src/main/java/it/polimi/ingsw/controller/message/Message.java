package it.polimi.ingsw.controller.message;

import it.polimi.ingsw.controller.Controller;

import java.io.Serializable;


/**
 * Abstract class for the Message
 */
public abstract class Message implements Serializable {

    private String messageContent;

    /**
     * Main constructor for the Message
     * @param message
     */
    public Message(String message) {
        messageContent = message;
    }

    /**
     * Method used to decrypt the message
     * @param controller
     */
    public abstract void decrypt(Controller controller);

    /**
     * Method used to get the message content
     * @return a string with the message content
     */
    public String getMessageContent() {
        return messageContent;
    }
}
