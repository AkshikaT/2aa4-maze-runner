package ca.mcmaster.se2aa4.mazerunner;

/*
 * Description; In charge of bringing together the maze, game logic, and the player classes together to start and end the game.
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public  class Game {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze;
    private Player player;
    private String playerPath = "";

    public Game (String[] args) {
        startGame(args);
    }

    // Descrption: utilize the arguments to read the file and print the path and file
    public void startGame(String[] args) {
        if(args.length < 2 || !args[0].equals("-i")) {
            System.out.println("error: wrong form of command line arguments. use -i <maze path>");
            return;
        }
        this.maze = new Maze(args[1]);
        if (args.length > 2 && args[2].equals("-p")) {              // validate the path the user entered
            int i = 3;
            while(i < args.length) {
                playerPath += args[i];
                i ++;
            }
            player = new Player(playerPath.trim());    
            playerPath = player.getPlayerPath(); 
            checkSequence();
        } else {                                                            // generate the actual path in factorized form naturally
            System.out.println(maze.getFactorizedPath(maze.getRHRpath(false)));
        }
    }

    // Description: following the player's movements and ensuring they don't hit walls and they end up at the end of the maze
    public void checkSequence() {
        boolean swapped = false;        // default entry position is west wall

        int entryAndExit [][] = maze.getEntryExitPoints();

        // retrieving the player's default position (the entry at the west wall)
        int playerX = entryAndExit[0][0];
        int playerY = entryAndExit[0][1];
        int exitX = entryAndExit[1][0];
        int exitY = entryAndExit[1][1];
        int playerDirection = 1;                    // by default, player is facing east bc entry is at west wall

        // directions that will add or subtract from the current coordinates
        int directionsRow [] = {-1, 0, 1, 0};
        int directionsCol [] = {0, 1, 0, -1};
        boolean pathValid = pathValidation(playerX, playerY, exitX, exitY, playerDirection, directionsRow, directionsCol);

        if (!pathValid) {                   // the player might have swapped entry and exit points
            swapped = true;
            int tempX = playerX;
            int tempY = playerY;
            playerX = exitX;
            playerY = exitY;
            exitX = tempX;
            exitY = tempY;
            playerDirection = 3;        // new default
            pathValid = pathValidation(playerX, playerY, exitX, exitY, playerDirection, directionsRow, directionsCol);
        }
    
        if (pathValid) {
            System.out.println("correct path");
            if (swapped) {
                // logger.info("Player swapped entry and exit. Regenerating path with east entrance.");
                maze.getRHRpath(true); // Assume east entrance
            }
        } else {
            System.out.println("incorrect path");
            // logger.info("Correct path for the tiny maze (assuming west entrance): " + maze.getFactorizedPath(maze.getRHRpath(swapped)));
        }
    }

    //Description: method to validate a path
    public boolean pathValidation(int entryX, int entryY, int exitX, int exitY, int playerDirection, int[] directionsRow, int[] directionsCol) {
        int playerX = entryX;
        int playerY = entryY;
        for (int i = 0; i < playerPath.length(); i ++) {
            if (playerPath.charAt(i) == 'R') {
                playerDirection = (playerDirection + 1) % 4;
            }
            else if(playerPath.charAt(i) == 'L') {
                playerDirection = (playerDirection + 3) % 4;
            }
            else if(playerPath.charAt(i) == 'F'){
                // update the coordinates
                int playerNewX = playerX + directionsRow[playerDirection];
                int playerNewY = playerY + directionsCol[playerDirection];

                playerX = playerNewX;
                playerY = playerNewY;
                if (playerNewX < 0 || playerNewX >= maze.getRows() || playerNewY < 0 || playerNewY >= maze.getCols()) {         // check if outside maze
                    // logger.error("Invalid move: Out of bounds");
                    return false;
                }

                if (maze.maze[playerNewX][playerNewY] == '#') {                                                                 // check if hit a wall
                    // logger.error("Invalid move: hit wall");
                    return false;
                }
            }
            
        }
        if (playerX == exitX && playerY == exitY)
            return true;
        else
            return false;
    }
}
