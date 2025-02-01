package ca.mcmaster.se2aa4.mazerunner;

/*
 * Description: Reads the tect file for which board to use and prints it. Prints a message if board not found.
 */

 import java.util.ArrayList;
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;

public class Maze {
    private static final Logger logger = LogManager.getLogger();
    public String filepath;
    public char [][] maze;
    private int rows, cols;
    private ArrayList<ArrayList<String>> generatedPath = new ArrayList<>();

    public Maze (String filepath) {                             // identifying the maze being used
        this.filepath = filepath;
        readMaze();
    }

    // Description: to return the number of rows and columns in the text file
    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }

    // Description: Check if the maze exists and read it
    public void readMaze() {
        try {
            logger.info("**** Reading the maze from file " + filepath);
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            int maxCols = 0;                                                                    // tracking the max number of columns at it skips to the next line sometimes
            String line;

            ArrayList <String> mazeTextFileLines = new ArrayList<>();                           // contains the row data from the text file        
            while ((line = reader.readLine()) != null) {
                maxCols = Math.max(maxCols, line.length());
                mazeTextFileLines.add(line);
            }

            // initializing the maze w/ a 2d array
            rows = mazeTextFileLines.size();
            cols = maxCols;
            maze = new char[rows][cols];
            for (int i = 0; i < rows; i++) {
                String row = mazeTextFileLines.get(i);
                for(int j = 0; j < cols; j ++) {
                    if (j < row.length()) {
                        maze[i][j] = row.charAt(j);
                    } else {
                        maze[i][j] = ' ';                                                           // if space missing, add a space
                    }
                }
            }
            logger.info("Maze read into 2d array successfully." + java.util.Arrays.deepToString(maze));

        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
    }

    // Description: to identify the entry point on the west side
    public int [][] getEntryExitPoints() {
        int entryAndExit [][] = new int[2][2];

        // find a the space in the first and last column
        for(int i = 0; i < rows; i ++) {
            if(maze[i][0] == ' ') {
                entryAndExit[0] = new int[]{i, 0};
            }
            if(maze[i][cols - 1] == ' ') {
                entryAndExit[1] = new int[]{i, cols - 1};
            }
        }
        logger.info("Entry and Exit points" + java.util.Arrays.deepToString(entryAndExit));
        return entryAndExit;
    }

    // Description: Returns whether or not a spot is valid in a maze depending on the coordinates
    public boolean spotValid(int row, int col) {
        if (maze[row][col] == '#') {
            return false;
        }
        return true;
    }

    // Description: Generate path using right hand rule w/ west wall as the default entry point
    public ArrayList <String> getRHRpath(boolean swapped) {
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
        logger.info("Generated a path w/ west entrance using RHR. ");
        logger.info("Canonical form: " + getCanonicalString(path));
        logger.info("Factorized form: " + getFactorizedPath(path));
        return path;
    }

    // Description: returns the canonical form of a path if an arraylist is given (from generated path)
    public String getCanonicalString(ArrayList <String> path) {
        String canonicalPath = "";
        for(int i = 0; i < path.size(); i ++) {
            if(i < path.size() - 1 && path.get(i) == path.get(i + 1)) {
                canonicalPath += path.get(i);
            }
            else {
                canonicalPath += path.get(i) + " ";
            }
        }
        return canonicalPath;
    }

    // Description: Returns a factorized path
    public String getFactorizedPath(ArrayList <String> path) {
        int count = 1;
        String factorizedPath = "";
        for(int i = 0; i < path.size(); i ++) {
            if(i < path.size() - 1 && path.get(i) == path.get(i + 1)) {
                count ++;
            }
            else {
                if (count == 1) {
                    factorizedPath += path.get(i) + " ";
                }
                else {
                    factorizedPath += count + path.get(i) + " ";
                }
                count = 1;
            }
        }
        return factorizedPath;
    }
}
