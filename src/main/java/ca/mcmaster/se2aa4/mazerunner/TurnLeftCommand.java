package ca.mcmaster.se2aa4.mazerunner;

public class TurnLeftCommand implements MovementCommand{
    @Override
    public boolean execute(RightHandRuleGenerator generator) {
        generator.playerDirection = (generator.playerDirection + 3) % 4;
        generator.path.add("L");
        return true;
    }
}
