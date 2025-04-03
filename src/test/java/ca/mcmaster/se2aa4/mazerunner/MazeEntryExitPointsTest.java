package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MazeEntryExitPointsTest {

    // Description: testing to see if the entry and exit point is correct in straight maze
    @Test
    public void testMazeEntryExitPoints() {
        String filepath = "examples/straight.maz.txt";
        Maze maze = new Maze(filepath);

        // Get the entry and exit points
        int[][] entryExit = maze.getEntryExitPoints();

        // check the expected with the actual result
        assertArrayEquals(entryExit[0], new int[]{2, 0});
        assertArrayEquals(entryExit[1], new int[]{2, 4});
    }

}
