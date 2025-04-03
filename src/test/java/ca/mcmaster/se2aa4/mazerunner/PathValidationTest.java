package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PathValidationTest {

    @Test
    public void pathValidationTest() {
        String filepath = "examples/straight.maz.txt";
        Maze maze = new Maze(filepath);

        String playerPath1 = "FFFF";
        String playerPath2 = "L";
        PathValidator pathValidator1 = new PathValidator(maze, playerPath1);
        PathValidator pathValidator2 = new PathValidator(maze, playerPath2);

        assertEquals(true, pathValidator1.pathValid);
        assertEquals(false, pathValidator2.pathValid);
    }
    
}
