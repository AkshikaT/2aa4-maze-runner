package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PathValidationTest {
    String filepath = "examples/test.maz.txt";
    PathGenerator pathGenerator = new RightHandRuleGenerator(filepath);
    private Maze maze = new Maze(filepath);

    @Test
    public void testComputerCorrectCannonicalPath() {
        ArrayList<String> path = pathGenerator.solveMaze(false);
        assertEquals("FF R F L F L FFFFFF R FFF R FFFFFF L F L FFFF R FFF R FFFF L F L FF R FFFF LL FFFF R FF R FFFFFFF R FFFF R FFFF LL FFFFFFF", maze.getCanonicalString(path));

    }                           

    @Test
    public void testComputerCorrectFactorizedPath() {
        ArrayList<String> path = pathGenerator.solveMaze(false);

        assertEquals("2F R F L F L 6F R 3F R 6F L F L 4F R 3F R 4F L F L 2F R 4F 2L 4F R 2F R 7F R 4F R 4F 2L 7F", maze.getFactorizedPath(path));
    }

    @Test
    public void canonicalPlayerHitWallTest() {
        String playerPath = "FFFFFFFFFF";
        PathValidator pathValidator = new PathValidator(maze, playerPath);
        assertEquals(false, pathValidator.pathValid);
    }

    @Test
    public void factorizedPlayerHitWallTest() {
        String playerPath = "10f";
        PathValidator pathValidator = new PathValidator(maze, playerPath);
        assertEquals(false, pathValidator.pathValid);
    }

    @Test
    public void canonicalPlayerDidNotReach() {
        String playerPath = "F";
        PathValidator pathValidator = new PathValidator(maze, playerPath);
        assertEquals(false, pathValidator.pathValid);
    }

    @Test
    public void factorizedPlayerDidNotReach() {
        String playerPath = "2f";
        PathValidator pathValidator = new PathValidator(maze, playerPath);
        assertEquals(false, pathValidator.pathValid);
    }

    @Test
    public void cannonicalOutOfBoundsPath() {
        String playerPath = "RR F";
        PathValidator pathValidator = new PathValidator(maze, playerPath);
        assertEquals(false, pathValidator.pathValid);
    }

    @Test
    public void factorizedOutOfBoundsPath() {
        String playerPath = "2r f";
        PathValidator pathValidator = new PathValidator(maze, playerPath);
        assertEquals(false, pathValidator.pathValid);
    }

    @Test
    public void testPlayerCorrectCanonicalPath() {
        String playerPath = "FF R F L F L FFFFFF R FFF R FFFFFF L F L FFFF R FFF R FFFF L F L FF R FFFF LL FFFF R FF R FFFFFFF R FFFF R FFFF LL FFFFFFF";
        PathValidator pathValidator = new PathValidator(maze, playerPath);
        assertEquals(true, pathValidator.pathValid);
    }

    @Test
    public void testCorrectFactorizedPathSwapped() {
        PathGenerator pathGenerator = new RightHandRuleGenerator("examples/test.maz.txt");
        Maze maze = new Maze(filepath);

        ArrayList<String> path = pathGenerator.solveMaze(true);

        assertEquals("FF R FFFF L FFFFFFFFFFFF R FF R FFFF LL FFFFFFFF R FF R FFFFFFFFFFF R FF L FFFFF LL FFFF R FF R FFFF LL FFFFFFFFFFFFFFFFF L FFFFFFF R FF", maze.getCanonicalString(path));
    }
}
