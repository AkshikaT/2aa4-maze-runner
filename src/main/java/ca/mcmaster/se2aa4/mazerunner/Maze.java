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
    protected char [][] maze;
    protected int rows, cols;
    protected ArrayList<ArrayList<String>> generatedPath = new ArrayList<>();

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
            // logger.info("**** Reading the maze from file " + filepath);
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
            // logger.info("Maze read into 2d array successfully." + java.util.Arrays.deepToString(maze));

        } catch(Exception e) {
            System.out.print("error: failed to read the path");
            // logger.error("/!\\ An error has occured /!\\");
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
        // logger.info("Entry and Exit points" + java.util.Arrays.deepToString(entryAndExit));
        return entryAndExit;
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
