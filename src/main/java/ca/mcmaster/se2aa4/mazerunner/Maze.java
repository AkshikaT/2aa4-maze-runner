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

    public Maze (String filepath) {                             // identifying the maze being used
        this.filepath = filepath;
        readMaze();
    }

    // Description: Check if the maze exists and read it
    public void readMaze() {
        try {
            logger.info("**** Reading the maze from file " + filepath);
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;

            ArrayList <String> mazeTextFileLines = new ArrayList<>();                           // contains the row data from the text file        
            while ((line = reader.readLine()) != null) {
                mazeTextFileLines.add(line);
            }

            // initializing the maze w/ a 2d array
            rows = mazeTextFileLines.size();
            cols = mazeTextFileLines.get(0).length();
            maze = new char[rows][cols];
            logger.info("Maze read into 2d array successfully.");

        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
    }

    //Description: returns the path of the maze for the straight.maz.txt
    public String getStraightMazePath() {
        logger.info("**** Computing path");
        return "FFFFF";
    }

    // Description: to identify the entry point on the west side
    public int [][] getEntryExitPoints() {
        int entryAndExit [][] = new int[2][2];

        // find a the space in the first and last column
        for(int i = 0; i < rows; i ++) {
            if(maze[i][0] == ' ') {
                entryAndExit[0] = new int[]{i, 0};
            }
            else if(maze[i][rows - 1] == ' ') {
                entryAndExit[0] = new int[]{i, rows - 1};
            }
        }
        logger.info("Entry and Exit points" + java.util.Arrays.deepToString(entryAndExit));
        return entryAndExit;
    }


}
