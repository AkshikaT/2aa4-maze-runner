package ca.mcmaster.se2aa4.mazerunner;

/*
 * Description: class that represents the player and it's attributes
 */

 import java.util.Scanner;
 import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;

public class Player {
    private static final Logger logger = LogManager.getLogger();
    private String playerPath;

    public Player() {
        receivePlayerPath();
    }

    // Description: returns the player's path
    public String getPlayerPath() {
        return playerPath;
    }

    // Collects the user's input for the path to go in one direction only
    public void receivePlayerPath() {
        Scanner input = new Scanner(System.in);
        logger.info("Retrieving player's path sequence.");
        System.out.println("Please enter the sequence for going through the maze from West to East: ");
        this.playerPath = input.nextLine().toUpperCase();
        String pathWithoutSpaces = "";
        for(int i = 0; i < playerPath.length(); i ++) {
            if(playerPath.charAt(i) != ' ') {
                pathWithoutSpaces += playerPath.charAt(i);
            }
        }
        logger.info("Player path without spaces: " + pathWithoutSpaces);
        System.out.println("");
    }
}
