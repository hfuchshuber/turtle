package model.robot;

import java.util.ArrayList;

import integration.IRobotObserver;

public class Turtle implements Robot, IViewRobot, Observable {
	
	private double myXpos;
	private double myYpos;
	private double myRotation;
	private boolean myPenDown;
	private boolean myVisibility;
	private ArrayList<IRobotObserver> myObservers;
	
	public Turtle() {
		//TODO
	}

	/** SETTERS **/
	
	@Override
	public void setX(double x) {
		notifyObservers();
		myXpos = x;
		
	}

	@Override
	public void setY(double y) {
		notifyObservers();
		myYpos = y;
		
	}
	
	@Override
	public void setRotation(double r) {
		notifyObservers();
		myRotation = r; 
	}

	@Override
	public void setPenDown(boolean t) {
		myPenDown = t;
		
	}

	@Override
	public void setVisible(boolean t) {
		notifyObservers();
		myVisibility = t;
	}
	
	
	
	/** GETTERS **/

	@Override
	public double getX() {
		return myXpos;
	}

	@Override
	public double getY() {
		return myYpos;
	}

	@Override
	public double getRotation() {
		return myRotation;
	}

	@Override
	public boolean isPenDown() {
		return myPenDown;
	}

	@Override
	public boolean isVisible() {
		return myVisibility;
	}

	@Override
	public void registerObserver(IRobotObserver o) {
		myObservers.add(o);	
	}

	@Override
	public void removeObserver(IRobotObserver o) {
		int i = myObservers.indexOf(o);
		if (i > 0) {
			myObservers.remove(i);
		}
		
	}

	@Override
	public void notifyObservers() {
		for (IRobotObserver observer : myObservers) {
			observer.update();
		}
		
	}
	
	/** OBSERVERABLE **/
	



}