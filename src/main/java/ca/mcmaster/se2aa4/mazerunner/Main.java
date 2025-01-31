package ca.mcmaster.se2aa4.mazerunner;

/*
 * ASSIGNMENT 1: MAZE RUNNER
 * Name: Akshika Thiyagendran
 * Date: January 20, 2025
 * 
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        Game game = new Game(args);                             // starting a game object
        logger.info("** End of MazeRunner");
        
    }
}
