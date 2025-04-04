package ca.mcmaster.se2aa4.mazerunner;

public class TurnRightCommand implements MovementCommand{
    @Override
    public boolean execute(RightHandRuleGenerator generator) {
        int directionRight = (generator.playerDirection + 1) % 4;
        int rightRow = generator.row + generator.directionsRow[directionRight];
        int rightCol = generator.col + generator.directionsCol[directionRight];
        
        if (generator.spotValid(rightRow, rightCol)) {
            generator.playerDirection = directionRight;
            generator.path.add("R");
            return true;
        }
        return false;
    }
}
