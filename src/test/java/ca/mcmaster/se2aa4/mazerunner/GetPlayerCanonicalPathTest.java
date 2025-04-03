package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GetPlayerCanonicalPathTest {

    @Test
    public void getPlayerCannonicalPathTest() {
        String playerPath1 = "4f 2r l";
        Player player = new Player(playerPath1);

        assertEquals("FFFFRRL", player.getPlayerPath());            // purposefully not spaced out as its purpose will be for validation later
    }
    
}
