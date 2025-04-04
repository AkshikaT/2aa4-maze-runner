package ca.mcmaster.se2aa4.mazerunner;

public class ComputerGame extends MazeTemplate {
    private PathGenerator pathGenerator;

    @Override
    protected boolean isValidArgs(String[] args) {
        return args.length >= 2 && args[0].equals("-i");
    }

    @Override
    protected void processPath(String[] args) {
        pathGenerator = new RightHandRuleGenerator(maze.filepath);
        System.out.println(maze.getFactorizedPath(pathGenerator.solveMaze(false)));
    }
}