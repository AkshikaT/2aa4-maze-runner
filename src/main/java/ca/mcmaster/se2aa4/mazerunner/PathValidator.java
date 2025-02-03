package ca.mcmaster.se2aa4.mazerunner;

// Description: given a path, this class validates the path the player inputted

public class PathValidator {
    private Maze maze;
    private PathGenerator pathGenerate;
    private Player player;
    private String playerPath = "";

    public PathValidator(Maze maze, String playerPath) {
        this.maze = maze;
        this.pathGenerate = new RightHandRuleGenerator(maze.filepath);
        this.playerPath = playerPath;
        checkSequence();
    }
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
                pathGenerate.generatePath(true); // Assume east entrance
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
