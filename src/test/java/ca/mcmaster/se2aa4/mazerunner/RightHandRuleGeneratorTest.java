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
        ArrayList<String> path = pathGenerator.generatePath(false);

        assertEquals("F R F LL FFFFFF R FF R FFFFFF LL FFFF R FF R FFFF LL FF R FF LL FF R FF R FFFF R FFFF R F LL FFF", maze.getCanonicalString(path));

    }
    
}
