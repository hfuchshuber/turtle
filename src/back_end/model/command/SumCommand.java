package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.Environment;


public class SumCommand implements ICommand {

    public SumCommand(Robot aRobot, Environment aEnvironment, String aCommandName) {
    }

    @Override
    public double eval (IReadableInput ... aList) {
        return aList[0].getValue() + aList[1].getValue();
    }

}
