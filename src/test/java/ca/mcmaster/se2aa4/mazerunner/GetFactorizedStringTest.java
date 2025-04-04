package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class GetFactorizedStringTest {

    // Description: testing the canonical string in straight maze
    @Test
    public void getFactorizedtringTest() {
        String filepath = "examples/straight.maz.txt";
        Maze maze = new Maze(filepath);

        PathGenerator pathGenerator = new RightHandRuleGenerator(filepath);
        ArrayList<String> path = pathGenerator.solveMaze(false);

        assertEquals("4F", maze.getFactorizedPath(path));
        
    }
    
}
