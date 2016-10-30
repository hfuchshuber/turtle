package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.IModifiableVariableState;


public class IsShowingCommand implements ICommand {

    private Robot myRobot;

    public IsShowingCommand(Robot aRobot, IModifiableVariableState aEnvironment, String aCommandName) {
        myRobot = aRobot;
    }

    @Override
    public double eval (IReadableInput ... aList) {
        double returnVal = (myRobot.isVisible()) ? 1 : 0;
        return returnVal;
    }

}
