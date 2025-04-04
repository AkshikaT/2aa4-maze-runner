package ca.mcmaster.se2aa4.mazerunner;

/*
 * Description; In charge of bringing together the maze, game logic, and the player classes together to start and end the game.
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public  class Game {

    public Game (String[] args) {
        MazeTemplate game;

        if (args.length > 2 && args[2].equals("-p")) {
            game = new ValidatingMaze();
        } else {
            game = new ComputerGame();
        }

        game.runGame(args);
        
    }
}
