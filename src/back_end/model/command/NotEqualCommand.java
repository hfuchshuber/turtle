package back_end.model.command;

import back_end.model.node.IReadableInput;

public class NotEqualCommand implements ICommand {
	
	public NotEqualCommand() {
	}

	@Override
	public double eval(IReadableInput... aList) {
		double returnVal = (aList[0].getValue() != aList[1].getValue()) ? 1 : 0;
		return returnVal;
	}

}