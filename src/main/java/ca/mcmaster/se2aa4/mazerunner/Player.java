package ca.mcmaster.se2aa4.mazerunner;

/*
 * Description: class that represents the player and it's attributes
 */

 import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;

public class Player {
    private static final Logger logger = LogManager.getLogger();
    private String playerPath;

    public Player(String playerPath) {
        receivePlayerPath(playerPath);
    }

    // Description: returns the player's path
    public String getPlayerPath() {
        return playerPath;
    }

    // Description: returns the canonical form of a path for a given string
    public void getCanonicalString() {
        String canPath = "";
        String strCount = "";
        int count = 1;
        for(int i = 0; i < playerPath.length(); i ++) {
            if(Character.isDigit(playerPath.charAt(i))) {                   // get the coefficient number if there is one and add to count (accounting for more than one digit)
                strCount += playerPath.charAt(i);
            }
            else if(Character.isLetter(playerPath.charAt(i))){              // bc it might be a space
                if (strCount.length() > 0) {
                    count = Integer.parseInt(strCount);
                }

                for (int j = 0; j < count; j++) {
                    canPath += playerPath.charAt(i);
                }
                strCount = "";
                count = 1;
            }
        }
        playerPath = canPath;
    }

    // Collects the user's input for the path to go in one direction only
    public void receivePlayerPath(String path) {
        this.playerPath = path.toUpperCase();
        
        // check if the path is inputted in factorized form
        getCanonicalString();
        // logger.info("Player path without spaces: " + playerPath);
    }
}
