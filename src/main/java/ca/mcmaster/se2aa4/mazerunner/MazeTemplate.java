package ca.mcmaster.se2aa4.mazerunner;

public abstract class MazeTemplate {
    protected Maze maze;
    protected Player player;
    protected String playerPath;
    
    public final void runGame(String[] args) {
        if (!isValidArgs(args)) {
            System.out.println("PATH NOT COMPUTED");
            return;
        }

        maze = new Maze(args[1]);
        processPath(args);
    }

    protected abstract boolean isValidArgs(String[] args);
    protected abstract void processPath(String[] args);
}