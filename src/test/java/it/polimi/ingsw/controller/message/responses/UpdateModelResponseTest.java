package it.polimi.ingsw.controller.message.responses;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.player.Player;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UpdateModelResponseTest {

    /**
     * Verify that the update method setting correctly the model
     */
    @Test
    public void getModelTest() {
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("John");
        players.add(player1);
        Model model = new Model(players);
        UpdateModelResponse updateModelResponse = new UpdateModelResponse("test", model, new ChatMessageResponse("ciao"));
        assertNotNull(updateModelResponse.getModel());
    }
    /**
     * Verify that the update method recognize correctly a chat message
     */
    @Test
    public void getModelTest1() {
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("John");
        players.add(player1);
        Model model = new Model(players);
        UpdateModelResponse updateModelResponse = new UpdateModelResponse("test", model, new ChatMessageResponse("ciao"));
        assertTrue(updateModelResponse.getPassedMessage() instanceof ChatMessageResponse);
    }
}
