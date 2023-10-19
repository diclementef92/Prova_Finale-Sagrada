package it.polimi.ingsw.controller.message.responses;

import it.polimi.ingsw.model.player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SetPlayerResponseTest {

    /**
     * Verify that the player to send the response is setting correctly
     */
    @Test
    public void getPlayerTest(){
        Player player1 = new Player("John");
        SetPlayerResponse setPlayerResponse = new SetPlayerResponse("test", player1);
        assertEquals(player1, setPlayerResponse.getPlayer());
    }
}
