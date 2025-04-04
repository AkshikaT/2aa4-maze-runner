package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

// Description: Algorithm to generate a path using right hand rule

public class RightHandRuleGenerator extends PathGenerator{
    // index 0 - North  1 - East    2 - South   3 - West    (circular)
    protected int directionsRow [] = {-1, 0, 1, 0};
    protected int directionsCol [] = {0, 1, 0, -1};

    // all the movements possible
    private final MovementCommand turnRight = new TurnRightCommand();
    private final MovementCommand moveForward = new MoveForwardCommand();
    private final MovementCommand turnLeft = new TurnLeftCommand();

    public RightHandRuleGenerator(String filepath) {
        super(filepath);
    }

    @Override
    // Description: Generate path using right hand rule w/ west wall as the default entry point
    public ArrayList <String> generatePath() {

        while (!(row == exitRow && col == exitCol)) {
            // Try movements in priority order: right, forward, left
            boolean movement = turnRight.execute(this);
            movement = moveForward.execute(this);
            if (!movement) {
                turnLeft.execute(this);
            }
        }
        return path;
    }
    
}
