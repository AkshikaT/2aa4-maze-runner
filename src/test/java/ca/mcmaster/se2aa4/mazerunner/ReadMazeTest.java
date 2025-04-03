package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ReadMazeTest {

    // Description: testing to see if the number of cols and rows is correctly read from straight maze
    @Test
    public void readMazeTest() {
        String filepath = "examples/straight.maz.txt";
        Maze maze = new Maze(filepath);

        maze.readMaze();

        // check the expected with the actual result
        assertEquals(5, maze.getRows());
        assertEquals(5, maze.getCols());

    }
}
