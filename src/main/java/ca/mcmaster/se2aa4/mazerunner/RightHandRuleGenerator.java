package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

// Description: Algorithm to generate a path using right hand rule

public class RightHandRuleGenerator extends PathGenerator{

    public RightHandRuleGenerator(String filepath) {
        super(filepath);
    }

    @Override
    // Description: Generate path using right hand rule w/ west wall as the default entry point
    public ArrayList <String> generatePath(boolean swapped) {
        int entryAndExit [][] = getEntryExitPoints();
        ArrayList<String> path = new ArrayList<>();

        // assuming that the west wall has the entrance, start there
        int row = entryAndExit[0][0];
        int col = entryAndExit[0][1];
        int exitRow = entryAndExit[1][0];
        int exitCol = entryAndExit[1][1];

        int playerDirection = 1;
        // index 0 - North  1 - East    2 - South   3 - West    (circular)
        int directionsRow [] = {-1, 0, 1, 0};
        int directionsCol [] = {0, 1, 0, -1};

        if (swapped) {
            // Assume East wall entry
            row = entryAndExit[1][0];
            col = entryAndExit[1][1];
            exitRow = entryAndExit[0][0];
            exitCol = entryAndExit[0][1];
            playerDirection = 3; // Facing west
        }

        while (!(row == exitRow && col == exitCol)) {
            // retrieving the coordinate on the right of current position
            int directionRight = (playerDirection + 1) % 4;
            int rightRow = row + directionsRow[directionRight];
            int rightCol = col + directionsCol[directionRight];
            
            // if open turn right
            if (spotValid(rightRow, rightCol)) {
                playerDirection = directionRight;
                path.add("R");
            }
            // checking forward spot
            int forwardRow = row + directionsRow[playerDirection];
            int forwardCol = col + directionsCol[playerDirection];
            if (spotValid(forwardRow, forwardCol)){
                row = forwardRow;
                col = forwardCol;
                path.add("F");
            }
            else{                                                   // last option to take a left
                playerDirection = (playerDirection + 3) % 4;
                path.add("L");
            }

        }
        // logger.info("Generated a path w/ west entrance using RHR. ");
        // logger.info("Canonical form: " + getCanonicalString(path));
        // logger.info("Factorized form: " + getFactorizedPath(path));
        return path;
    }

}
