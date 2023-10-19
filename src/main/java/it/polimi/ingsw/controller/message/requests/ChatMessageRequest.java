package it.polimi.ingsw.controller.message.requests;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.message.responses.ChatMessageResponse;
import it.polimi.ingsw.model.player.Player;

/**
 * Public class for chat message request
 */
public class ChatMessageRequest extends Request {

    /**
     * Main constructor of the chat message request
     *
     * @param message: chat message request message
     * @param player: player whom requested it
     */
    public ChatMessageRequest(String message, Player player) {
        super(message, player);
    }

    /**
     *
     * Decrypt the message received and notify the controller of the chat message
     *
     * @param controller: current controller used
     */
    @Override
    public void decrypt(Controller controller) {
        ChatMessageResponse publicChatMessage = new ChatMessageResponse(getMessageContent());
        controller.getModel().notifyViews(publicChatMessage);

    }
}
