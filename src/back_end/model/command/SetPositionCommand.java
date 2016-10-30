package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.Robot;
import back_end.model.states.IModifiableVariableState;


public class SetPositionCommand implements ICommand {

    private Robot myRobot;

    public SetPositionCommand(Robot aRobot, IModifiableVariableState aEnvironment, String aCommandName) {
        myRobot = aRobot;
    }

	@Override
    public double eval (IReadableInput ... aList) {
        double posX = Math.abs(myRobot.getCoordinate().getX() - aList[0].getValue());
        double posY = Math.abs(myRobot.getCoordinate().getY() - aList[1].getValue());
        double returnVal = Math.sqrt(Math.pow(posX, 2) + Math.pow(posY, 2));
        myRobot.setX(aList[0].getValue());
        myRobot.setY(aList[1].getValue());
        return returnVal;
    }

}
