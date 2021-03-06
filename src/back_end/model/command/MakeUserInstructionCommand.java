package back_end.model.command;

import java.util.Arrays;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;


import back_end.model.exception.InvalidNodeUsageException;
public class MakeUserInstructionCommand implements ICommand {
	
	private IModifiableEnvironmentState myEnvironment;
	
	public MakeUserInstructionCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
		myEnvironment = aEnvironment;
		//myEnvironment.assignMethod(aCommandName, null, null);
	}

	@Override
	public double eval(IReadableInput... aList) throws InvalidNodeUsageException {
		IReadableInput[] variableList = Arrays.copyOfRange(aList, 1, aList.length - 1);
		myEnvironment.assignMethod(aList[0].getName(), variableList, aList[aList.length - 1]);
		return 1;
	}

}
