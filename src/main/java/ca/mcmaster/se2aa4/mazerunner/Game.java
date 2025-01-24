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
    private Player player;
    private String playerPath, correctPath;

    public Game (String[] args) {
        startGame(args);
    }

    // Descrption: utilize the arguments to read the file and print the path and file
    public void startGame(String[] args) {
        this.maze = new Maze(args[1]);                  // read the maze & check if it exists
        player = new Player();                          // retrieve the path from the player
        playerPath = player.getPlayerPath();
        correctPath = maze.getStraightMazePath();
        checkSequence();
    }

    // Description: display whether the player's sequence is correct
    public void checkSequence() {
        if (correctPath.equals(playerPath)) {
            logger.info("PASS");
        }
        else {
            logger.info("FAIL");
            logger.info("Correct path: " + correctPath);
        }
    }
}
