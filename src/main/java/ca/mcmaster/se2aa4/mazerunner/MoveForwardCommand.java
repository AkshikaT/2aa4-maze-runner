package ca.mcmaster.se2aa4.mazerunner;

public class MoveForwardCommand implements MovementCommand {
    @Override
    public boolean execute(RightHandRuleGenerator generator) {
        int forwardRow = generator.row + generator.directionsRow[generator.playerDirection];
        int forwardCol = generator.col + generator.directionsCol[generator.playerDirection];
        
        if (generator.spotValid(forwardRow, forwardCol)) {
            generator.row = forwardRow;
            generator.col = forwardCol;
            generator.path.add("F");
            return true;
        }
        return false;
    }
}
