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
    private String playerPath;

    public Game (String[] args) {
        startGame(args);
    }

    // Descrption: utilize the arguments to read the file and print the path and file
    public void startGame(String[] args) {
        this.maze = new Maze(args[1]);                  // read the maze & check if it exists
        player = new Player();                          // retrieve the path from the player
        playerPath = player.getPlayerPath();
        checkSequence();                                // will also return the results
    }

    // Description: following the player's movements and ensuring they don't hit walls and they end up at the end of the maze
    public void checkSequence() {
        int entryAndExit [][] = maze.getEntryExitPoints();

        // retrieving the player's default position (the entry at the west wall)
        int playerX = entryAndExit[0][0];
        int playerY = entryAndExit[0][1];
        int playerNewX, playerNewY;
        int playerDirection = 1;                    // by default, player is facing east bc entry is at west wall

        // directions that will add or subtract from the current coordinates
        int directionsRow [] = {-1, 0, 1, 0};
        int directionsCol [] = {0, 1, 0, -1};

        for (int i = 0; i < playerPath.length(); i ++) {
            if (playerPath.charAt(i) == 'R') {
                playerDirection = (playerDirection + 1) % 4;
            }
            else if(playerPath.charAt(i) == 'L') {
                playerDirection = (playerDirection + 3) % 4;
            }
            else if(playerPath.charAt(i) == 'F'){
                // update the coordinates
                playerNewX = playerX + directionsRow[playerDirection];
                playerNewY = playerY + directionsCol[playerDirection];

                // checking whether this new coordinate is valid (is there a wall present here already)
                if(maze.maze[playerNewX][playerNewY] == '#' || playerX < maze.getRows() || playerY < maze.getCols()) {
                    logger.error("This is path is invalid.");
                    break;
                }
                playerX = playerNewX;
                playerY = playerNewY;
            }
        }

        // check whether the final coordinates of the player is the exit point
        if (playerX == entryAndExit[1][0] && playerY == entryAndExit[1][1]) {
            System.out.println("You passed :)");
        }
        else {
            System.out.println("You failed :(");
            logger.info("Correct path for tiny maze: " + maze.getFactorizedPath(maze.getRHRpath()));
        }
    }
}
