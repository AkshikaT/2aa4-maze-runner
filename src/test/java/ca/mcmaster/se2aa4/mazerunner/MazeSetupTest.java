package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MazeSetupTest {
    private Maze maze;

    @BeforeEach
    public void setup() {
        String filepath = "examples/straight.maz.txt";
        maze = new Maze(filepath);
    }

    @Test
    public void getRowsTest() {
        assertEquals(5, maze.getRows());
    }

    @Test
    public void getColsTest() {
        assertEquals(5, maze.getCols());
    }

    @Test
    public void getEntryExitPointsTest() {
        int[][] points = maze.getEntryExitPoints();
        assertArrayEquals(new int[]{2, 0}, points[0]);
        assertArrayEquals(new int[]{2, 4}, points[1]);
    }
}