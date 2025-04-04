package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

// Description: a class that focuses on generating a path for the given maze.

public abstract class PathGenerator extends Maze {
    protected ArrayList<String> path = new ArrayList<>();
    protected int entryAndExit [][] = getEntryExitPoints();
    protected static int playerDirection = 1;
    protected int row;
    protected int exitRow;
    protected int col;
    protected int exitCol;

    public PathGenerator(String filepath) {
        super(filepath);
    }

    public final ArrayList<String> solveMaze(boolean swapped) {
        if (swapped) {
            swapExitEntryPoints();
            playerDirection = 3;
        }
        row = entryAndExit[0][0];
        col = entryAndExit[0][1];

        exitRow = entryAndExit[1][0];
        exitCol = entryAndExit[1][1];
        return generatePath();
    }

    public void swapExitEntryPoints() {
        int [] temp = entryAndExit[0];
        entryAndExit[0] = entryAndExit[1];
        entryAndExit[1] = temp;
    }

    // Description: Returns whether or not a spot is valid in a maze depending on the coordinates
    public boolean spotValid(int row, int col) {
        if (maze[row][col] == '#') {
            return false;
        } 
        return true;
    }

    public abstract ArrayList<String> generatePath();                // works for any algorithm
}
