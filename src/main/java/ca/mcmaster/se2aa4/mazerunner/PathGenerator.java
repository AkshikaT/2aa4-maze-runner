package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

// Description: a class that focuses on generating a path for the given maze.

public abstract class PathGenerator extends Maze {

    public PathGenerator(String filepath) {
        super(filepath);
    }

    // Description: Returns whether or not a spot is valid in a maze depending on the coordinates
    public boolean spotValid(int row, int col) {
        if (maze[row][col] == '#') {
            return false;
        } 
        return true;
    }

    public abstract ArrayList<String> generatePath(boolean swapped);                // works for any algorithm
}
