package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PathValidationTest {

    @Test
    public void pathValidationTest() {
        String filepath = "examples/straight.maz.txt";
        Maze maze = new Maze(filepath);

        String playerPath = "FFFF";
        PathValidator pathValidator = new PathValidator(maze, playerPath);

        assertEquals(true, pathValidator.pathValid);
    }
    
}
