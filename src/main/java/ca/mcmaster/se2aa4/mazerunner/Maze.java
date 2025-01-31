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
        System.out.println(getRHRpath());
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
        logger.info("maze" + java.util.Arrays.deepToString(maze));
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
    public String getRHRpath() {
        int entryAndExit [][] = getEntryExitPoints();
        ArrayList<String> westEntrance = new ArrayList<>();
        ArrayList<String> eastEntrance = new ArrayList<>();                         // dont use for now
        generatedPath.add(westEntrance);
        generatedPath.add(eastEntrance);

        // assuming that the west wall has the entrance, start there
        int row = entryAndExit[0][0];
        int col = entryAndExit[0][1];

        int playerDirection = 1;                    // by default, player is facing east bc entry is at west wall
        // index 0 - North  1 - East    2 - South   3 - West    (circular)
        int directionsRow [] = {-1, 0, 1, 0};
        int directionsCol [] = {0, 1, 0, -1};

        while (!(row == entryAndExit[1][0] && col == entryAndExit[1][1])) {
            // retrieving the coordinate on the right of current position
            int directionRight = (playerDirection + 1) % 4;
            int rightRow = row + directionsRow[directionRight];
            int rightCol = col + directionsCol[directionRight];
            
            // if open turn right
            if (spotValid(rightRow, rightCol)) {
                playerDirection = directionRight;
                westEntrance.add("R");
            }
            // checking forward spot
            int forwardRow = row + directionsRow[playerDirection];
            int forwardCol = col + directionsCol[playerDirection];
            if (spotValid(forwardRow, forwardCol)){
                row = forwardRow;
                col = forwardCol;
                westEntrance.add("F");
            }
            else{                                                   // last option to take a left
                playerDirection = (playerDirection + 3) % 4;
                westEntrance.add("L");
            }

        }
        logger.info("Generated a path w/ west entrance using RHR.");
        System.out.println("Final coordinates for generated path: " + row + " " + col);
        return westEntrance.toString();
    }

    // Description: Returns a factorized path
    public String getFactorizedPath(String originalPath) {
        return "";
    }
}
