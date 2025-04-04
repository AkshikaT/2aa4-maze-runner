package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class RightHandRuleGeneratorTest {

    // Desription: testing RHR method with direct maze
    @Test
    public void rightHandRuleGeneratorTest () {
        String filepath = "examples/test.maz.txt";
        Maze maze = new Maze(filepath);

        PathGenerator pathGenerator = new RightHandRuleGenerator(filepath);
        ArrayList<String> path = pathGenerator.solveMaze(false);

        assertEquals("FF R F L F L FFFFFF R FFF R FFFFFF L F L FFFF R FFF R FFFF L F L FF R FFFF LL FFFF R FF R FFFFFFF R FFFF R FFFF LL FFFFFFF", maze.getCanonicalString(path));

    }
    
}
