package ca.mcmaster.se2aa4.mazerunner;

/*
 * Description; In charge of bringing together the maze, game logic, and the player classes together to start and end the game.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public  class Game {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze;

    public Game (String[] args) {
        startGame(args);
    }

    // Descrption: utilize the arguments to read the file and print the path and file
    public void startGame(String[] args) {
        this.maze = new Maze(args[1]);                  // read the maze & check if it exists
    }
}
