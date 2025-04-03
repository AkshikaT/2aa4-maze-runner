package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class GetCanonicalStringTest {

    // Description: testing the canonical string in straight maze
    @Test
    public void getCanonicalStringTest() {
        String filepath = "examples/straight.maz.txt";
        Maze maze = new Maze(filepath);

        PathGenerator pathGenerator = new RightHandRuleGenerator(filepath);
        ArrayList<String> path = pathGenerator.generatePath(false);

        assertEquals("FF", maze.getCanonicalString(path));
        
    }
    
}
