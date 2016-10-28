package back_end.model.states;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;


public class FunctionScope {
	
	private Stack<VariableState> myNestedVariableStates;

	
	public FunctionScope() {
		myNestedVariableStates = new Stack<VariableState>();
		myNestedVariableStates.push(new VariableState());
	}
	
	//XXX: Very hacky, would be better if we used an iterable, need to talk to front-end.
	public VariableState getVariableMap() {
		VariableState returnVal = new VariableState(); 
		for (VariableState variableState : myNestedVariableStates) {
			returnVal.combineVariableMap(variableState.getVariableMap());
		}
		return returnVal;
	}
	
	public boolean containsVariable(String name) {
		for (VariableState variableState : myNestedVariableStates) {
			if (variableState.containsVariable(name)) {
				return true;
			}
		}
		return false;
	}
	
	public double getVariableValue(String aVariable) {
		for (VariableState variableState : myNestedVariableStates) {
			if (variableState.containsVariable(aVariable)) {
				return variableState.getValue(aVariable);
			}
		}
		return 0;
	}
	
	public Collection<String> getVariablesInScope() {
		Set<String> keySet = new HashSet<String>(); 
		for (VariableState variableState : myNestedVariableStates) {
			keySet.addAll(variableState.getVariableKeySet()); 
		}
		return keySet;
	}

	public void assignVariable(String aName, double aValue) {
		for (VariableState variableState : myNestedVariableStates) {
			if (variableState.containsVariable(aName)) {
				variableState.assignVariable(aName, aValue);
				return;
			}
		}
		myNestedVariableStates.peek().assignVariable(aName, aValue);
	}
	
	public void addNestedScope() {
		myNestedVariableStates.push(new VariableState());
	}
	
	public void removeNestedScope() {
		myNestedVariableStates.pop();
	}


}
