package model.command.mathOperations;

import model.command.ICommand;
import model.exception.ArgumentException;

public class QuotientCommand implements ICommand {
	
	public QuotientCommand() {
	}

	@Override
	public double eval(String... aList) throws ArgumentException {
		if (aList.length != 2) {
			throw new ArgumentException("Method Evaluation needs two arguments");
		}
		return Double.parseDouble(aList[0]) / Double.parseDouble(aList[1]);
	}

}