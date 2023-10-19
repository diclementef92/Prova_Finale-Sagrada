package it.polimi.ingsw.controller.message.responses;

import it.polimi.ingsw.model.player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrivateResponseTest {

    /**
     * Verify that the player to send private response is setting correctly
     */
    @Test
    public void getPlayerTest(){
        Player player = new Player("John");
        PrivateResponse privateResponse = new PrivateResponse("test", player);
        assertEquals(player, privateResponse.getPlayer());
    }
}
