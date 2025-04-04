package ca.mcmaster.se2aa4.mazerunner;

public class ValidatingMaze extends MazeTemplate {
    private PathValidator pathValidator;

    @Override
    protected boolean isValidArgs(String[] args) {
        return args.length > 2 && args[2].equals("-p");
    }

    @Override
    protected void processPath(String[] args) {
        StringBuilder pathBuilder = new StringBuilder();
        for (int i = 3; i < args.length; i++) {
            pathBuilder.append(args[i]);
        }
        playerPath = pathBuilder.toString().trim();
        player = new Player(playerPath);
        pathValidator = new PathValidator(maze, player.getPlayerPath());
    }
}