package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.Environment;
import back_end.model.states.IModifiableVariableState;


public abstract class RotationCommand implements ICommand {

    public RotationCommand(Robot aRobot, IModifiableVariableState aEnvironment, String aCommandName) {
    }

    public double getRotation (IReadableInput ... aList) {
        double rotationValue = aList[0].getValue() % 360;
        if (rotationValue < 0) {
            rotationValue = 360 + rotationValue;
        }
        return rotationValue;

    }

}
