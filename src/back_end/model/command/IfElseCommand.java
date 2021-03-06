package back_end.model.command;

import back_end.model.node.IReadableInput;
import back_end.model.robot.IRobot;
import back_end.model.states.IModifiableEnvironmentState;
import back_end.model.exception.InvalidInputNumberException;
import back_end.model.exception.InvalidNodeUsageException;
public class IfElseCommand extends IfCommand {

    private boolean myExecuteMethod;

    public IfElseCommand(IRobot aRobot, IModifiableEnvironmentState aEnvironment, String aCommandName) {
        super(aRobot, aEnvironment, aCommandName);
        myExecuteMethod = true;
    }
    
    @Override 
    protected int evalConditionInNode(IReadableInput... aList) throws InvalidNodeUsageException, InvalidInputNumberException {
		errorCheckForTooManyInputs(aList.length, 2);
		if (myExecuteMethod) {
			int returnVal = (super.evalCondition(aList) == 0) ? 0 : 1;
            myExecuteMethod = false;
            return returnVal;
        }
        return -1;
	}

    @Override
    public double eval (IReadableInput ... aList) throws InvalidNodeUsageException {
        return super.eval(aList);
    }

}
