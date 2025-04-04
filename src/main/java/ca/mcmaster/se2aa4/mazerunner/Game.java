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
    private PathValidator pathValidator;
    private PathGenerator pathGenerate;
    private String playerPath = "";

    public Game (String[] args) {
        startGame(args);
    }

    // Descrption: utilize the arguments to read the file and print the path and file
    public void startGame(String[] args) {
        if(args.length < 2 || !args[0].equals("-i")) {
            System.out.println("PATH NOT COMPUTED");
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
            pathValidator = new PathValidator(maze, playerPath);
        } else {                                                            // generate the actual path in factorized form naturally
            pathGenerate = new RightHandRuleGenerator(maze.filepath);
            System.out.println(maze.getFactorizedPath(pathGenerate.solveMaze(false)));
        }



    }
}
