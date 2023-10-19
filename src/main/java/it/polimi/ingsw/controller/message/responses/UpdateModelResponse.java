package it.polimi.ingsw.controller.message.responses;

import it.polimi.ingsw.controller.message.Message;
import it.polimi.ingsw.model.Model;

/**
 * Update Model Response class
 */
public class UpdateModelResponse extends Response {

    private Model model;
    private Message passedMessage;

    /**
     * main constructor for Update Model Response
     * @param message
     * @param model
     * @param passedMessage
     */
    public UpdateModelResponse(String message, Model model, Message passedMessage) {
        super(message);
        this.model = model;
        this.passedMessage = passedMessage;

    }

    /**
     * Method used to get the passed message
     * @return the message
     */
    public Message getPassedMessage() {
        return passedMessage;
    }

    /**
     * Method used to get the model
     * @return the current model
     */
    public Model getModel() {
        return model;
    }
}
